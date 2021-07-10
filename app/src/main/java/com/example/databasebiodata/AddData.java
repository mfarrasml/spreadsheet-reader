package com.example.databasebiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddData extends AppCompatActivity {

    private Button saveDataButton;

    private PersonalIdentity newIdentity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data2);

        newIdentity = new PersonalIdentity();

        EditText fullName = findViewById(R.id.full_name);
        EditText address = findViewById(R.id.address);
        EditText dateOfBirth = findViewById(R.id.date_of_birth);
        EditText phoneNumber = findViewById(R.id.phone_number);

        saveDataButton = findViewById(R.id.save_data_button);

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIdentity.setPersonalIdentity(fullName.getText().toString(), address.getText().toString(), dateOfBirth.getText().toString(), phoneNumber.getText().toString());
                MainActivity.identityList.add(newIdentity);
                finish();
            }
        });

    }
}