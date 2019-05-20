package com.example.bankapp;

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

public class ResetPassword extends AppCompatActivity {

    private EditText password, passwordConfirm;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();
    }

    public void updatePassword(View view){
        if(password.getText().toString().equals(passwordConfirm.getText().toString()) && password.getText().toString().length() >= 6){
            final DatabaseReference dbref = database.getReference("users/");
            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        User user = new User(/*userdata*/);
                        dbref.setValue(user);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "The password is either too short or doesn't match. It must be atleast 6 characters!", Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        this.password = findViewById(R.id.newPass);
        this.passwordConfirm = findViewById(R.id.newPassConfirm);
        this.database = FirebaseDatabase.getInstance();

    }
}
