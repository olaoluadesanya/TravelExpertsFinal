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

public class DetailActivity extends Activity {

    // Declare the member variables for the GUI elements
    TextView tvPkgName, tvPkgDesc, tvPkgDates, tvPkgPrice;
    ImageView ivPkgDetail;
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
        // Create an event listener on the "Book" button
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the number of travellers has been set using the spinner.
                // If not, generate an error message.
                // If so, proceed with the booking.
            }
        });

    }
}
