/*
    MainActivity.java
    Corinne Mullan
    October 9, 2018

    Initial version created.
 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends Activity {

    ListView lvPackages;
    StringBuffer buffer = new StringBuffer();
    ArrayList<Packag> upcomingPackages = new ArrayList<Packag>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPackages = findViewById(R.id.lvPackages);

        loadUpcomingPackages();

        // Set up an event listener to go to the DetailActivity if a package in the list view
        // is clicked.  Pass the selected package to the detail activity in the intent.
        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Packag packag = upcomingPackages.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packag", packag);
                startActivity(intent);
            }
        });
    }

    private void loadUpcomingPackages() {
        new GetPackages().execute();
    }

    class GetPackages extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("travelexperts", "doinbackground");

                // ***** TO DO:  Add correct URL *****
                // ***** TO DO:  The REST service should return only packages with future start
                // dates, i.e., those packages that can be booked by the customer.  Suggestion:
                // do not just return/display a few "featured" packages (e.g., 3 upcoming ones);
                // rather, return all packages that can be booked by the user on the mobile device.

                URL url = new URL("http://10.163.101.82:8080/JSPDay7REST/rs/restcustomers/getall");

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
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("harv", "Buffer = " + buffer);

            // Obtain all of the upcoming packages from the database using the REST web service
            try {
                JSONArray jsonArray = new JSONArray(buffer.toString());
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject pkg = (JSONObject) jsonArray.get(i);
                    // Create a Packag object from the JSON object
                    Packag p = new Packag();
                    p.setPackageId(pkg.getInt("packageId"));
                    p.setPkgAgencyCommission(pkg.getDouble("pkgAgencyCommission"));
                    p.setPkgBasePrice(pkg.getDouble("pkgBasePrice"));
                    p.setPkgDesc(pkg.getString("pkgDesc"));

                    String strEndDate = pkg.getString("pkgEndDate");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDate = df.parse(strEndDate);
                    p.setPkgEndDate(endDate);

                    p.setPkgName(pkg.getString("pkgName"));

                    String strStartDate = pkg.getString("pkgStartDate");
                    Date startDate = df.parse(strStartDate);
                    p.setPkgEndDate(startDate);

                    upcomingPackages.add(p);

                    // ***** TO DO *****:  figure out how to display images

                    // Display all of these packages in the list view on the main activity.
                    // Use the package_item.xml layout for each item in the list view.
                    ArrayList<HashMap<String,String>> pkgMaps = new ArrayList<>();
                    for (Packag up : upcomingPackages) {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("pkgname", up.getPkgName() + "");
                        map.put("pkgdates", up.getDates() + "");
                        pkgMaps.add(map);
                    }
                    int resource = R.layout.package_item;
                    String [] from = {"pkgname", "pkgdates"};
                    int [] to = {R.id.tvPkgName, R.id.tvPkgDates};
                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), pkgMaps, resource, from, to);
                    lvPackages.setAdapter(adapter);
                }
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        String option;

        // **** TO DO *****
        switch(item.getItemId()) {
            case R.id.miMyAccount:
//                intent = new Intent(this, OptionsActivity.class);
//                option = "About";
//                intent.putExtra("option", option);
//                startActivity(intent);
                return true;
            case R.id.miLogInOut:
//                intent = new Intent(this, OptionsActivity.class);
//                option = "Settings";
//                intent.putExtra("option", option);
//                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
