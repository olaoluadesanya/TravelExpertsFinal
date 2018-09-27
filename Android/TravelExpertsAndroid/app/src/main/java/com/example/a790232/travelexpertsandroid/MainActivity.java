package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
