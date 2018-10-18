/*
* This checks if a user has been logged in
* and redirects as necessary
* Olaoluwa Adesanya SAIT 2018
*/
package com.example.a790232.travelexpertsandroid;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainEmptyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent activityIntent;

        //Get token
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String token = preferences.getString("token",null);
        // go straight to main if a token is stored
        if (token != null) {
            //Get customer from shared prefernces and pass to intent
            String custJSon = preferences.getString("custJson",null);
            Gson gson = new Gson();
            Type category = new TypeToken<Customer>(){}.getType();
            Customer customer = gson.fromJson(custJSon, category);

            activityIntent = new Intent(this, MainActivity.class);
            activityIntent.putExtra("customer", customer);
        } else {

            //start login intent
            activityIntent = new Intent(this, LoginActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }
}
