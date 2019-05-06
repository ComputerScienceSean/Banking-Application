package com.example.bankapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterUser extends AppCompatActivity {

    private EditText cpr, firstname, lastname, phonenumber, email, address, password;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        init();
    }

    public void init(){
        this.cpr = findViewById(R.id.cpr);
        this.firstname = findViewById(R.id.firstname);
        this.lastname = findViewById(R.id.lastname);
        this.phonenumber = findViewById(R.id.phonenumber);
        this.email = findViewById(R.id.email);
        this.address = findViewById(R.id.address);
        this.password = findViewById(R.id.password);
        this.database = FirebaseDatabase.getInstance();
    }

    public void register(View view){
        if (address.getText().toString().length() != 0 && email.getText().toString().length() != 0 && firstname.getText().toString().length() != 0 && lastname.getText().toString().length() != 0 && phonenumber.getText().toString().length() != 0) {
            if (password.getText().toString().length() >= 6) {
                if (cpr.getText().toString().length() == 10) {
                    final DatabaseReference dbRef = database.getReference("users/" + cpr.getText().toString());
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {
                                User user = new User(
                                        firstname.getText().toString(),
                                        lastname.getText().toString(),
                                        cpr.getText().toString(),
                                        phonenumber.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        address.getText().toString());

                                dbRef.setValue(user);

                                Intent accountOverviewIntent = new Intent(getApplicationContext(), AccountOverview.class);
                                startActivity(accountOverviewIntent);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "The CPR number has to be exactly 10 characters", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "The password needs to be atleast 6 characters long", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_LONG).show();
        }
    }
}