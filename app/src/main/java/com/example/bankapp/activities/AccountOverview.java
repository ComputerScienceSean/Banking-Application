package com.example.bankapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.bankapp.entities.BankAccount;
import com.example.bankapp.R;
import com.example.bankapp.entities.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AccountOverview extends AppCompatActivity {

    private FirebaseDatabase database;
    private static ArrayList<BankAccount> accountsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        init();
        loadAccounts();
        Log.d("LLL", ""+accountsList);
    }


    public void loadAccounts() {
        Intent getIntent = getIntent();
        String userCPR = getIntent.getStringExtra("CPR");
        DatabaseReference dbref = database.getReference("usersbankaccounts/" + userCPR);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    DatabaseReference bankAccounts = database.getReference("bankaccounts/" + data.getKey());
                    Log.d("OVERVIEW", "key " + data.getKey());
                    Log.d("OVERVIEW", "value " + data.getValue());
                    bankAccounts.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("OVERVIEW", "asdasdas" + dataSnapshot.getValue());
                            BankAccount bankAccount = dataSnapshot.getValue(BankAccount.class);
                            accountsList.add(bankAccount);
                            Log.d("OVERVIEW", "this is the array" + accountsList);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void init() {
        //this.recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, accountsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.database = FirebaseDatabase.getInstance();
    }
}
