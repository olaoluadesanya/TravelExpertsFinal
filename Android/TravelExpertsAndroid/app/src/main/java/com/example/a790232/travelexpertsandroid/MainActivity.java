/*
    MainActivity.java
    Corinne Mullan
    October 9, 2018

    Initial version created.
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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


public class MainActivity extends Activity {

    // Class variables
    TextView tvCustName;
    ListView lvPackages;
    StringBuffer buffer = new StringBuffer();
    ArrayList<Packag> upcomingPackages = new ArrayList<Packag>();

    Customer customer;

    // Define a constant for the IP address of the web service
    // Use 10.0.2.2 when running an emulator, and the web service is running on the same machine
    // (this IP bridges from the emulated device to the machine it is running on)
    //static final String IP_ADDRESS = "10.0.2.2";
    static final String IP_ADDRESS = "10.187.212.89";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the currently logged in customer from the intent
        Intent intent = getIntent();

        customer = (Customer) intent.getSerializableExtra("customer");

        // Obtain a reference to the list view on the main activity that will list all
        // of the packages with a start date in the future, and to the text view used
        // for displaying the customer's name
        tvCustName = findViewById(R.id.tvCustName);
        lvPackages = findViewById(R.id.lvPackages);

        // Set the customer's name in the greeting
        tvCustName.setText(customer.getCustFirstName() + "!");

        // Retrieve a list of these packages from the web service and display them
        loadUpcomingPackages();

        // Set up an event listener to go to the DetailActivity if a package in the list view
        // is clicked.  Pass the selected package to the detail activity in the intent.
        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Packag packag = upcomingPackages.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packag", packag);
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        });
    }

    private void loadUpcomingPackages() {
        new GetCurrentPackages().execute();
    }

    class GetCurrentPackages extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("travelexperts", "doinbackground");

                // This URL is used to retrieve all packages with start dates in the future
                // from the web service.
                URL url = new URL("http://" + IP_ADDRESS + ":8080/TravelExperts2/rs/db/getcurrentpackages");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("accept", "application/json");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line;
                while ((line = br.readLine()) != null)
                {
                    buffer.append(line);
                }
                Log.d("travelexperts", "Buffer = " + buffer);

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            // For testing only, when web service unavailable
            //buffer.append("[{\"packageId\":1,\"pkgAgencyCommission\":400.0000,\"pkgBasePrice\":4800.0000,\"pkgDesc\":\"Cruise the Caribbean \\u0026 Celebrate the New Year.\",\"pkgEndDate\":\"Jan 4, 2017 12:00:00 AM\",\"pkgName\":\"Caribbean New Year\",\"pkgStartDate\":\"Dec 25, 2017 12:00:00 AM\"},{\"packageId\":2,\"pkgAgencyCommission\":310.0000,\"pkgBasePrice\":3000.0000,\"pkgDesc\":\"8 Day All Inclusive Hawaiian Vacation\",\"pkgEndDate\":\"Dec 20, 2016 12:00:00 AM\",\"pkgName\":\"Polynesian Paradise\",\"pkgStartDate\":\"Dec 12, 2016 12:00:00 AM\"},{\"packageId\":3,\"pkgAgencyCommission\":300.0000,\"pkgBasePrice\":2800.0000,\"pkgDesc\":\"Airfaire, Hotel and Eco Tour.\",\"pkgEndDate\":\"May 28, 2016 12:00:00 AM\",\"pkgName\":\"Asian Expedition\",\"pkgStartDate\":\"May 14, 2016 12:00:00 AM\"},{\"packageId\":4,\"pkgAgencyCommission\":280.0000,\"pkgBasePrice\":3000.0000,\"pkgDesc\":\"Euro Tour with Rail Pass and Travel Insurance\",\"pkgEndDate\":\"Nov 14, 2016 12:00:00 AM\",\"pkgName\":\"European Vacation\",\"pkgStartDate\":\"Nov 1, 2016 12:00:00 AM\"},{\"packageId\":5,\"pkgAgencyCommission\":400.0000,\"pkgBasePrice\":4800.0000,\"pkgDesc\":\"Cruise the Caribbean \\u0026 Celebrate the New Year.\",\"pkgEndDate\":\"Jan 4, 2017 12:00:00 AM\",\"pkgName\":\"Caribbean New Year\",\"pkgStartDate\":\"Dec 25, 2017 12:00:00 AM\"},{\"packageId\":6,\"pkgAgencyCommission\":400.0000,\"pkgBasePrice\":4800.0000,\"pkgDesc\":\"Cruise the Caribbean \\u0026 Celebrate the New Year.\",\"pkgEndDate\":\"Jan 4, 2017 12:00:00 AM\",\"pkgName\":\"Caribbean New Year\",\"pkgStartDate\":\"Dec 25, 2017 12:00:00 AM\"},{\"packageId\":7,\"pkgAgencyCommission\":400.0000,\"pkgBasePrice\":4800.0000,\"pkgDesc\":\"Cruise the Caribbean \\u0026 Celebrate the New Year.\",\"pkgEndDate\":\"Jan 5, 2017 12:00:00 AM\",\"pkgName\":\"Caribbean New Year\",\"pkgStartDate\":\"Dec 25, 2017 12:00:00 AM\"},{\"packageId\":8,\"pkgAgencyCommission\":400.0000,\"pkgBasePrice\":4800.0000,\"pkgDesc\":\"Cruise the Caribbean \\u0026 Celebrate the New Year.\",\"pkgEndDate\":\"Jan 5, 2017 12:00:00 AM\",\"pkgName\":\"Caribbean New Year\",\"pkgStartDate\":\"Dec 25, 2017 12:00:00 AM\"}]");

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("travelexperts", "Buffer = " + buffer);

            // Obtain the list of upcoming packages from the JSON data in "buffer" that was
            // returned by the RESTful service, using the fromJson() method that is part of
            // the Gson package.
            Gson gson = new Gson();
            Type category = new TypeToken<List<Packag>>(){}.getType();
            upcomingPackages = gson.fromJson(buffer.toString(), category);

            // Display all of these packages in the list view on the main activity.
            // Use the package_item.xml layout for each item in the list view.
            ArrayList<HashMap<String,String>> pkgMaps = new ArrayList<>();
            for (Packag up : upcomingPackages) {
                HashMap<String,String> map = new HashMap<>();
                map.put("pkgname", up.getPkgName() + "");
                map.put("pkgdates", up.getDates() + "");

                String imgFileName = up.getPkgImageFile();
                String imgName = "";
                if (!imgFileName.equals("")) {
                    int idx = imgFileName.indexOf('.');
                    imgName = imgFileName.substring(0, idx);
                }
                else {
                    // Use a default image if no image file name is specified
                    imgName = "airplane";
                }

                map.put("pkgimagefile", imgName);
                pkgMaps.add(map);
            }
            int resource = R.layout.package_item;
            String [] from = {"pkgname", "pkgdates", "pkgimagefile"};
            int [] to = {R.id.tvPkgName, R.id.tvPkgDates, R.id.ivPackage};
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), pkgMaps, resource, from, to) {

                // The following code allows the adapter to load the correct image in the image view
                // for each item in the list view
                @Override
                public void setViewImage(ImageView v, String value) {
                    int resID = getResources().getIdentifier(value, "drawable", getPackageName());
                    v.setImageResource(resID);
                }
            };

            lvPackages.setAdapter(adapter);
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

            case R.id.miLogOut: //logout flow Olaoluwa Adesanya
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
