/*
    AccountActivity.java
    Corinne Mullan
    October 17, 2018

    Initial version created.
 */

package com.example.a790232.travelexpertsandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AccountActivity extends Activity {

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
}
