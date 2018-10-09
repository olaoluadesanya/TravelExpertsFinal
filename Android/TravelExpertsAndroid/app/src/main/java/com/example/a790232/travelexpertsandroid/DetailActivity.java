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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailActivity extends Activity {

    // Declare the member variables for the GUI elements
    TextView tvPkgName, tvPkgDesc, tvPkgDates, tvPkgPrice;
    Spinner spNumTravellers;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtain the selected package from the intent
        Intent intent = getIntent();
        Packag packag = (Packag) intent.getSerializableExtra("packag");

        // Obtain references to all of the GUI elements
        tvPkgName = findViewById(R.id.tvPkgName);
        tvPkgDesc = findViewById(R.id.tvPkgDesc);
        tvPkgDates = findViewById(R.id.tvPkgDates);
        tvPkgPrice = findViewById(R.id.tvPkgPrice);
        spNumTravellers = findViewById(R.id.spNumTravellers);
        btnBook = findViewById(R.id.btnBook);

        // Display the details for the selected package using the activity_detail.xml layout
        tvPkgName.setText(packag.getPkgName());
        tvPkgDesc.setText(packag.getPkgDesc());
        tvPkgDates.setText(packag.getDates());

        String strPrice = String.format ("$7.2%f", packag.getPkgBasePrice() + packag.getPkgAgencyCommission());
        tvPkgPrice.setText(strPrice);

        // ***** TO DO *****: figure out how to display image

        // ***** TO DO *****:  implement booking functionality
    }
}
