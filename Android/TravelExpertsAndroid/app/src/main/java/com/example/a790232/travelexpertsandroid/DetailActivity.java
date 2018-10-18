/*
    DetailActivity.java
    This file has many Authors
    Author: Corinne Mullan, Sunghyun Lee
    Created: October 9, 2018
    Last Modified: October 17, 2018
 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class DetailActivity extends Activity {
//=========================Corinne=================================================================
    // Declare the member variables for the GUI elements
    TextView tvPkgName, tvPkgDesc, tvPkgDates, tvPkgPrice;
    ImageView ivPkgDetail;
    Spinner spNumTravellers;
    Button btnBook;

    Packag packag;
    Customer customer;

    private PostBookingTask pb =null;

    //String URLCONSTANT="http://localhost:8080";
    //static final String URLCONSTANT = "http://10.0.2.2:8080";
    static final String URLCONSTANT = "http://10.187.212.89:8080";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtain the selected package and logged-in customer from the intent
        Intent intent = getIntent();
        packag = (Packag) intent.getSerializableExtra("packag");
        customer = (Customer) intent.getSerializableExtra("customer");

        // Obtain references to all of the GUI elements
        tvPkgName = findViewById(R.id.tvPkgName);
        tvPkgDesc = findViewById(R.id.tvPkgDesc);
        tvPkgDates = findViewById(R.id.tvPkgDates);
        tvPkgPrice = findViewById(R.id.tvPkgPrice);
        ivPkgDetail = findViewById(R.id.ivPkgDetail);
        spNumTravellers = findViewById(R.id.spNumTravellers);
        btnBook = findViewById(R.id.btnBook);

        // Display the details for the selected package using the activity_detail.xml layout
        tvPkgName.setText(packag.getPkgName());
        tvPkgDesc.setText(packag.getPkgDesc());
        tvPkgDates.setText(packag.getDates());

        // Display the image for the package
        String imgFileName = packag.getPkgImageFile();
        if (!imgFileName.equals("")) {
            int idx = imgFileName.indexOf('.');
            String imgName = imgFileName.substring(0, idx);
            int resID = getResources().getIdentifier(imgName, "drawable", getPackageName());
            ivPkgDetail.setImageResource(resID);
        }
        else {
            // Use a default image if none is specified
            int resID = getResources().getIdentifier("airplane", "drawable", getPackageName());
            ivPkgDetail.setImageResource(resID);
        }

        // Display the total package cost
        String strPrice = String.format ("$%8.2f", packag.getPkgBasePrice().doubleValue() +
                                                   packag.getPkgAgencyCommission().doubleValue());
        tvPkgPrice.setText(strPrice);

//===============================================================================================


//===============================Sunghyun Lee =====================================================
        // Create an event listener on the "Book" button
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the number of travellers has been set using the spinner.
                // If not, generate an error message.
                // If so, proceed with the booking.
                if (validateBooking())
                {

                    //Log.i("sung", customer.getCustomerId() + ", " + packag.getPackageId());

                    pb = new PostBookingTask(customer.getCustomerId(), "BSN", packag.getPackageId(),
                            "B", Integer.parseInt(spNumTravellers.getSelectedItem().toString()), "BK",
                            "I DONT KNOW WHERE IM GOING BABY", tvPkgDesc.getText().toString());
                    pb.execute((Void) null);
                }

            }
        });

    }
    // return true if all inputs are selected
    public boolean validateBooking()
    {
        boolean myBool = true;
        try
        {
            int numTI = Integer.parseInt(spNumTravellers.getSelectedItem().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(DetailActivity.this, "Select the number of travellers",
                    Toast.LENGTH_LONG).show();
            myBool=false;
        }
        return myBool;
    }

    // Async task that handles booking process
    public class PostBookingTask extends AsyncTask<Void, Void, Boolean>
    {

        private int customerId;

        private String classId;

        private int packageId;

        private String tripTypeId;

        private int travelerCount;

        private String feeId;

        private String destination;

        private String description;

        PostBookingTask(int cus, String cla, int pac, String trip, int trav, String fee, String dest, String desc)
        {
            customerId = cus;
            classId = cla;
            packageId = pac;
            tripTypeId = trip;
            travelerCount = trav;
            feeId = fee;
            destination = dest;
            description = desc;
        }

        // send post request to web server
        @Override
        protected Boolean doInBackground(Void... params)
        {


            JsonObject json = new JsonObject();
            json.addProperty("customerId", customerId);
            json.addProperty("classId", classId);
            json.addProperty("packageId", packageId);
            json.addProperty("tripTypeId", tripTypeId);
            json.addProperty("travelerCount", travelerCount);
            json.addProperty("feeId", feeId);
            json.addProperty("destination", destination);
            json.addProperty("description", description);


            String postUrl = URLCONSTANT + "/TravelExperts2/rs/db/postbooking";
            OkHttpClient client = new OkHttpClient();
            Gson gson = new Gson();

            String jsonStr = gson.toJson(json);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

            Request request = new Request.Builder()
                    .url(postUrl)
                    .post(body)
                    .build();


            Response response = null;
            try
            {
                response = client.newCall(request).execute();
                String resBody = response.body().string();
                Log.i("RESPONSE", resBody);
                return true;
            } catch (IOException e)
            {
                e.printStackTrace();
            }


            return false;
        }
        // display toast message based on whether post was successful
        @Override
        protected void onPostExecute(final Boolean success)
        {


            if (success)
            {
                Toast.makeText(DetailActivity.this, "Booking Successful",
                        Toast.LENGTH_LONG).show();

            } else
            {
                Toast.makeText(DetailActivity.this, "Error. Please Try Again",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

//==================================================================================================

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
