/*
Author: Sunghyun Lee
Created: 2018-10-17

 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BookingsActivity extends Activity {

    ListView lvBookings;


    private Customer customer;
    //private String URLCONSTANT="http://localhost:8080";
    private String URLCONSTANT="http://10.187.212.89:8080";
    private ArrayList<Booking> bookingArrayList = new ArrayList<>();
    private ArrayList<Booking> customersBookingList = new ArrayList<>();
    private StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        // Retrieve customer object from intent
        // Obtain the currently logged in customer from the intent
        /*
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");
        */
        customer=new Customer();
        customer.setCustomerId(117);

        lvBookings = findViewById(R.id.lvBookings);

        // Retrieve all bookings for that customer id from the web service
        retrieveAllBookings();


        // Display in list view (may want to use a custom layout for listview items
        // as for packages??)
    }
    private void retrieveAllBookings()
    {
        new GetBookings().execute();
    }

    class GetBookings extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {

            URL url = null;
            try
            {
                url = new URL(URLCONSTANT+"/TravelExperts2/rs/db/getallbookings");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("accept", "application/json");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null)
                {
                    buffer.append(line);
                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try
            {
                JSONArray jsonArray = new JSONArray(buffer.toString());
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jo = (JSONObject) jsonArray.get(i);
                    //create booking object from json object
                    Booking b = new Booking(jo.getJSONObject("customer").getInt("customerId"),
                            "Dunno",
                            jo.getString("tripTypeId"),jo.getInt("travelerCount"),
                            "Dunno","Dunno",
                            "Dunno");
                    if (jo.has("packag") && !jo.isNull("packag"))
                    {
                        b.setPackageId(jo.getJSONObject("packag").getInt("packageId"));
                        b.setBookingDate(new Date());
                        bookingArrayList.add(b);
                    }

                }
                // find the customer's bookings
                for (Booking bo : bookingArrayList)
                {
                    if (bo.getCustomerId() == customer.getCustomerId())
                        customersBookingList.add(bo);
                }
                ArrayAdapter<Customer> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, customersBookingList);
                lvBookings.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
