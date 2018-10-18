/*
Author: Sunghyun Lee
Created: 2018-10-17

 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BookingsActivity extends Activity {

    ListView lvBookings;


    private Customer customer;
    //private String URLCONSTANT="http://10.0.2.2:8080";
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

        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");

        /*
        customer=new Customer();
        customer.setCustomerId(117);
*/
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

                        DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
                        b.setBookingDate(format.parse(jo.getString("bookingDate")));
                        Log.i("q", b.getBookingDate().toString());
                        //b.setBookingDate(new Date());
                        Packag tempPackage=new Packag();
                        tempPackage.setPackageId(jo.getJSONObject("packag").getInt("packageId"));
                        tempPackage.setPkgName(jo.getJSONObject("packag").getString("pkgName"));
                        b.setPackag(tempPackage);
                        bookingArrayList.add(b);
                    }

                }
                // find the customer's bookings
                for (Booking bo : bookingArrayList)
                {
                    if (bo.getCustomerId() == customer.getCustomerId())
                        customersBookingList.add(bo);
                }

                // Force the text color to be black, to avoid the problem of getting white text
                // on a white background for the list items
                ArrayAdapter<Booking> adapter = new ArrayAdapter<Booking>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, customersBookingList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        return view;
                    }
                };

                //ArrayAdapter<Customer> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, customersBookingList);
                lvBookings.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Create and inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Set up an event handler for when a menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.miHome:
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("customer", customer);
                startActivity(mainIntent);
                return true;

            case R.id.miMyBookings:
                Intent bookingsIntent = new Intent(getApplicationContext(), BookingsActivity.class);
                bookingsIntent.putExtra("customer", customer);
                startActivity(bookingsIntent);
                return true;

            case R.id.miMyAccount:
                Intent acctIntent = new Intent(getApplicationContext(), AccountActivity.class);
                acctIntent.putExtra("customer", customer);
                startActivity(acctIntent);
                return true;

            case R.id.miLogOut:
                SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                preferences.edit().putString("token",null).apply(); //set token to empty string
                preferences.edit().putString("custJson",null).apply();
                Intent activityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(activityIntent);
                return true;

            default:
                return false;
        }
    }
}
