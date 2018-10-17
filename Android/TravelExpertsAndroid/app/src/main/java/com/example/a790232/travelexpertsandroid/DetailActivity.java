/*
    DetailActivity.java
    Corinne Mullan
    October 9, 2018

    Initial version created.
 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
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

public class DetailActivity extends Activity {

    // Declare the member variables for the GUI elements
    TextView tvPkgName, tvPkgDesc, tvPkgDates, tvPkgPrice;
    ImageView ivPkgDetail;
    Spinner spNumTravellers;
    Button btnBook;

    Packag packag;
    Customer customer;

    String URLCONSTANT="http://localhost:8080";

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
        if (imgFileName != null) {
            int idx = imgFileName.indexOf('.');
            String imgName = imgFileName.substring(0, idx);
            int resID = getResources().getIdentifier(imgName, "drawable", getPackageName());
            ivPkgDetail.setImageResource(resID);
        }

        // Display the total package cost
        String strPrice = String.format ("$%8.2f", packag.getPkgBasePrice().doubleValue() +
                                                   packag.getPkgAgencyCommission().doubleValue());
        tvPkgPrice.setText(strPrice);

        // ***** TO DO *****: figure out how to display image

        // ***** TO DO *****:  implement booking functionality
        // ==========================================================================
        // ====================Sunghyun Lee =========================================
        // Create an event listener on the "Book" button
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the number of travellers has been set using the spinner.
                // If not, generate an error message.
                // If so, proceed with the booking.
                Booking booking = new Booking(customer.getCustomerId(), "",packag.getPackageId(),
                        "",Integer.parseInt(spNumTravellers.getSelectedItem().toString()),"",
                        "","");


                // send json to web server
                Gson gson = new Gson();
                Type type = new TypeToken<Booking>() {}.getType();
                String json = gson.toJson(booking, type);



                String       postUrl       = URLCONSTANT +"/TravelExperts2/rs/db/postbooking";
                HttpClient httpClient    = HttpClientBuilder.create().build();
                HttpPost post          = new HttpPost(postUrl);
                StringEntity postingString;
                HttpResponse response;

                int success=0; // store status code from http response to see whether successful
                try
                {
                    postingString = new StringEntity(json);
                    post.setEntity(postingString);
                    post.setHeader("Content-type", "application/json");
                    response = httpClient.execute(post);
                    success=response.getStatusLine().getStatusCode();

                } catch ( IOException e)
                {
                    e.printStackTrace();
                }


            }
        });

    }
}
