/*
    AccountActivity.java
    Corinne Mullan
    October 17, 2018

    Initial version created.
 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    StringBuffer buffer = new StringBuffer();

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
                if(validateCustomer()) {

                    // Update the database via the web service
                    //Log.i("sung", customer.getCustomerId() + ", " + packag.getPackageId());

                    pc = new PostCustomerTask(customer.getCustomerId(), etFirstName.getText().toString(),
                            etLastName.getText().toString(), etAddress.getText().toString(), etCity.getText().toString(),
                            spProvince.getSelectedItem().toString(), etPostalCode.getText().toString(), etCountry.getText().toString(),
                            etHomePhone.getText().toString(), etBusPhone.getText().toString(), etEmail.getText().toString(),
                            customer.getUserid().toString(), customer.getPasswd().toString());
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

    private boolean validateCustomer() {

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

        private int cId;
        private String cFirstName;
        private String cLastName;
        private String cAddress;
        private String cCity;
        private String cProv;
        private String cPostal;
        private String cCountry;
        private String cHomePhone;
        private String cBusPhone;
        private String cEmail;
        private String cUserId;
        private String cPasswd;

        PostCustomerTask(int id, String first, String last, String address, String city,
                         String prov, String postal, String country, String hph, String bph, String email,
                         String uid, String pwd)
        {
            cId = id;
            cFirstName = first;
            cLastName = last;
            cAddress = address;
            cCity = city;
            cProv = prov;
            cPostal = postal;
            cCountry = country;
            cHomePhone = hph;
            cBusPhone = bph;
            cEmail = email;
            cUserId = uid;
            cPasswd = pwd;
        }

        // Send the post request to the web server
        @Override
        protected Boolean doInBackground(Void... params)
        {
            // Create a json object containing the updated customer information
            // The web service will give an error if the userid and password are not
            // provided as these fields cannot be null.
            JsonObject json = new JsonObject();
            json.addProperty("customerId", cId);
            json.addProperty("custFirstName", cFirstName);
            json.addProperty("custLastName", cLastName);
            json.addProperty("custAddress", cAddress);
            json.addProperty("custCity", cCity);
            json.addProperty("custProv", cProv);
            json.addProperty("custPostal", cPostal);
            json.addProperty("custCountry", cCountry);
            json.addProperty("custHomePhone", cHomePhone);
            json.addProperty("custBusPhone", cBusPhone);
            json.addProperty("custEmail", cEmail);
            json.addProperty("userid", cUserId);
            json.addProperty("passwd", cPasswd);

            String postUrl = URLCONSTANT + "/TravelExperts2/rs/db/updatecustomer";
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
        // Display toast message indicating whether the post was successful
        @Override
        protected void onPostExecute(final Boolean success)
        {
            if (success)
            {
                Toast.makeText(AccountActivity.this, "Account information updated successfully",
                        Toast.LENGTH_LONG).show();

            } else
            {
                Toast.makeText(AccountActivity.this, "Error updating account information. Please try again",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}