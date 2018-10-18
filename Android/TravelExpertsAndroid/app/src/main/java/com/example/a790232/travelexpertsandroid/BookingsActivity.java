package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingsActivity extends Activity {

    private Customer customer;
    //private String URLCONSTANT="http://localhost:8080";
    private String URLCONSTANT="http://10.187.212.89:8080";
    private ArrayList<Booking> bookingArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        // Retrieve customer object from intent
        // Obtain the currently logged in customer from the intent
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");


        // Retrieve all bookings for that customer id from the web service
        retrieveAllBookings();

        // Display in list view (may want to use a custom layout for listview items
        // as for packages??)
    }

    private void retrieveAllBookings()
    {
        StringBuffer buffer = new StringBuffer();
        try {

            URL url = new URL(URLCONSTANT + "/TravelExperts2/rs/db/getallbookings");

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

        Gson gson = new Gson();
        Type category = new TypeToken<List<Booking>>(){}.getType();
        bookingArrayList = gson.fromJson(buffer.toString(), category);

        // Display all of these packages in the list view on the main activity.
        // Use the package_item.xml layout for each item in the list view.
        /*
        ArrayList<HashMap<String,String>> pkgMaps = new ArrayList<>();
        for (Packag up : upcomingPackages) {
            HashMap<String,String> map = new HashMap<>();
            map.put("pkgname", up.getPkgName() + "");
            map.put("pkgdates", up.getDates() + "");

            String imgFileName = up.getPkgImageFile();
            String imgName = "";
            if (imgFileName != null) {
                int idx = imgFileName.indexOf('.');
                imgName = imgFileName.substring(0, idx);
            }
            imgName = "R.drawable." + imgName;

            map.put("pkgimagefile", imgName);
            pkgMaps.add(map);
        }
        int resource = R.layout.package_item;
        String [] from = {"pkgname", "pkgdates", "pkgimagefile"};
        int [] to = {R.id.tvPkgName, R.id.tvPkgDates, R.id.ivPackage};
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), pkgMaps, resource, from, to);
        lvPackages.setAdapter(adapter);
        */
    }
}
