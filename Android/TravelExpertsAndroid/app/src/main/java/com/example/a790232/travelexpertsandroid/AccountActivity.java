/*
    AccountActivity.java
    Corinne Mullan
    October 17, 2018

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccountActivity extends Activity {

    // URLs for testing, depending on where the web service is being run
    //static final String URLCONSTANT = "http://10.0.2.2:8080";
    static final String URLCONSTANT = "http://10.187.212.89:8080";

    // Class variables for the GUI elements
    EditText etFirstName;
    EditText etLastName;
    EditText etAddress;
    EditText etCity;
    EditText etPostalCode;
    EditText etCountry;
    EditText etHomePhone;
    EditText etBusPhone;
    EditText etEmail;
    Spinner spProvince;
    Button btnSave, btnEdit;
    TextView tvStatusMsg;

    // Object for the currently logged in customer
    Customer customer;

    // String for displaying error and status messages
    String statusMsg = "";

    // Reference to the PostCustomerTask used for updating the customer information in the
    // database via the web service
    private PostCustomerTask pc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Obtain the currently logged in customer from the intent
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");

        // Obtain references to the GUI elements
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etPostalCode = findViewById(R.id.etPostalCode);
        etCountry = findViewById(R.id.etCountry);
        etHomePhone = findViewById(R.id.etHomePhone);
        etBusPhone = findViewById(R.id.etBusPhone);
        etEmail = findViewById(R.id.etEmail);
        spProvince = findViewById(R.id.spProvince);
        tvStatusMsg = findViewById(R.id.tvStatusMsg);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);

        // Initially, the text fields will just display the customer's information and
        // not be editable
        etFirstName.setEnabled(false);
        etLastName.setEnabled(false);
        etAddress.setEnabled(false);
        etCity.setEnabled(false);
        etPostalCode.setEnabled(false);
        etCountry.setEnabled(false);
        etHomePhone.setEnabled(false);
        etBusPhone.setEnabled(false);
        etEmail.setEnabled(false);
        spProvince.setEnabled(false);

        // Enable the Edit button and disable the Save button
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);

        // Set the text displayed in the GUI elements
        etFirstName.setText(customer.getCustFirstName());
        etLastName.setText(customer.getCustLastName());
        etAddress.setText(customer.getCustAddress());
        etCity.setText(customer.getCustCity());
        etPostalCode.setText(customer.getCustPostal());
        etCountry.setText(customer.getCustCountry());
        etHomePhone.setText(customer.getCustHomePhone());
        etBusPhone.setText(customer.getCustHomePhone());
        etEmail.setText(customer.getCustEmail());

        // Set the province in the spinner
        if (customer.getCustProv() == null) {
            spProvince.setSelection(0);
        }
        else {
            for (int i = 0; i < spProvince.getCount(); i++) {
                if (spProvince.getItemAtPosition(i).toString().equals(customer.getCustProv())) {
                    spProvince.setSelection(i);
                }
            }
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Enable the edit text fields and spinner
                etFirstName.setEnabled(true);
                etLastName.setEnabled(true);
                etAddress.setEnabled(true);
                etCity.setEnabled(true);
                etPostalCode.setEnabled(true);
                etCountry.setEnabled(true);
                etHomePhone.setEnabled(true);
                etBusPhone.setEnabled(true);
                etEmail.setEnabled(true);
                spProvince.setEnabled(true);

                // Set the focus to the first edit text
                etFirstName.requestFocus();

                // Disable the Edit button and enable the Save button
                btnEdit.setEnabled(false);
                btnSave.setEnabled(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //First validate the user-entered data
                if(validateCustomerData()) {

                    // Create a new customer object containing the updated data
                    Customer uCustomer = new Customer();
                    uCustomer.setCustomerId(customer.getCustomerId());
                    uCustomer.setCustFirstName(etFirstName.getText().toString());
                    uCustomer.setCustLastName(etLastName.getText().toString());
                    uCustomer.setCustAddress(etAddress.getText().toString());
                    uCustomer.setCustCity(etCity.getText().toString());
                    uCustomer.setCustProv(spProvince.getSelectedItem().toString());
                    uCustomer.setCustPostal(etPostalCode.getText().toString());
                    uCustomer.setCustCountry(etCountry.getText().toString());
                    uCustomer.setCustHomePhone(etHomePhone.getText().toString());
                    uCustomer.setCustBusPhone(etBusPhone.getText().toString());
                    uCustomer.setCustEmail(etEmail.getText().toString());
                    uCustomer.setUserid(customer.getUserid());
                    uCustomer.setPasswd(customer.getPasswd());

                    // Update the database via the web service
                    //Log.i("sung", customer.getCustomerId() + ", " + packag.getPackageId());

                    pc = new PostCustomerTask(uCustomer);
                    pc.execute((Void) null);

                    // Reset all the fields to disabled
                    etFirstName.setEnabled(false);
                    etLastName.setEnabled(false);
                    etAddress.setEnabled(false);
                    etCity.setEnabled(false);
                    etPostalCode.setEnabled(false);
                    etCountry.setEnabled(false);
                    etHomePhone.setEnabled(false);
                    etBusPhone.setEnabled(false);
                    etEmail.setEnabled(false);
                    spProvince.setEnabled(false);

                    // Enable the Edit button and disable the Save button
                    btnEdit.setEnabled(true);
                    btnSave.setEnabled(false);
                }

            }
        });
    }

    private boolean validateCustomerData() {

        statusMsg = "";

        // Validate that a first name has been entered
        if (etFirstName.getText().toString().equals("")) {
            statusMsg = "Please enter your first name";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        // Validate that a last name has been entered
        if (etLastName.getText().toString().equals("")) {
            statusMsg = "Please enter your last name";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        // Validate the postal code
        String regexPCStr = "^([a-zA-Z]\\d[a-zA-Z]\\s?\\d[a-zA-Z]\\d)$";
        if (!etPostalCode.getText().toString().equals("") && !etPostalCode.getText().toString().matches(regexPCStr)) {
            statusMsg = "Please enter your postal code in the form A1A 1A1";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        // Validate the home phone number, which must be a 10 digit number (digits only)
        String regexPhStr = "^[0-9]{10}$";
        if (!etHomePhone.getText().toString().equals("") && !etHomePhone.getText().toString().matches(regexPhStr)) {
            statusMsg = "Please enter your home phone number as 10 digits";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        // Validate the business phone number, which must be a 10 digit number (digits only)
        if (!etBusPhone.getText().toString().equals("") && !etBusPhone.getText().toString().matches(regexPhStr)) {
            statusMsg = "Please enter your business number as 10 digits";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        // Validate the email, in the form abc123@xx45.com
        // The email is a required field
        String regexEmailStr = "^([a-zA-Z0-9]+)@([a-zA-Z0-9]+).([a-zA-Z]+)$";
        String tmpEmail = etEmail.getText().toString().trim();      // trim the whitespace
        if (tmpEmail.equals("")) {
            statusMsg = "Please enter your email";
            tvStatusMsg.setText(statusMsg);
            return false;
        }
        else if (!tmpEmail.matches(regexEmailStr)){
            statusMsg = "Please enter a valid email in the form abc@xx.com";
            tvStatusMsg.setText(statusMsg);
            return false;
        }

        statusMsg = "";
        tvStatusMsg.setText(statusMsg);
        return true;
    }

    // Async task that handles the customer updates
    public class PostCustomerTask extends AsyncTask<Void, Void, Boolean>
    {

        private Customer updatedCustomer;
        private String updatedJsonString;

        PostCustomerTask(Customer updCust)
        {
            updatedCustomer = updCust;
        }

        // Send the post request to the web server
        @Override
        protected Boolean doInBackground(Void... params)
        {

            JsonObject json = new JsonObject();
            json.addProperty("customerId", updatedCustomer.getCustomerId());
            json.addProperty("custFirstName", updatedCustomer.getCustFirstName());
            json.addProperty("custLastName", updatedCustomer.getCustLastName());
            json.addProperty("custAddress", updatedCustomer.getCustAddress());
            json.addProperty("custCity", updatedCustomer.getCustCity());
            json.addProperty("custProv", updatedCustomer.getCustProv());
            json.addProperty("custPostal", updatedCustomer.getCustPostal());
            json.addProperty("custCountry", updatedCustomer.getCustCountry());
            json.addProperty("custHomePhone", updatedCustomer.getCustHomePhone());
            json.addProperty("custBusPhone", updatedCustomer.getCustBusPhone());
            json.addProperty("custEmail", updatedCustomer.getCustEmail());
            json.addProperty("userid", updatedCustomer.getUserid());
            json.addProperty("passwd", updatedCustomer.getPasswd());

            String postUrl = URLCONSTANT + "/TravelExperts2/rs/db/updatecustomer";
            OkHttpClient client = new OkHttpClient();
            Gson gson = new Gson();

            updatedJsonString = gson.toJson(json);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), updatedJsonString);

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
        // Display toast message indicating whether the post was successful
        @Override
        protected void onPostExecute(final Boolean success)
        {
            if (success)
            {
                Toast.makeText(AccountActivity.this, "Account information updated successfully",
                        Toast.LENGTH_LONG).show();

                // Update the customer object and the json string in shared preferences
                // Note that the customer Id, userid and password will be unchanged, so there is no
                // need to update these
                customer = updatedCustomer;
                SharedPreferences preferences = getSharedPreferences("MY_APP", getApplicationContext().MODE_PRIVATE);
                preferences.edit().putString("custJson",updatedJsonString).apply();


            } else
            {
                Toast.makeText(AccountActivity.this, "Error updating account information. Please try again",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    // Override onBackPressed() so that when the back button is pressed, the updated customer object
    // is passed back.  Take the user back to the main activity for simplicity, regardless of
    // where they came from (adequate for a prototype application)
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(AccountActivity.this,MainActivity.class);
        myIntent.putExtra("customer", customer);
        AccountActivity.this.startActivity(myIntent);
    }

    // Display the menu
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