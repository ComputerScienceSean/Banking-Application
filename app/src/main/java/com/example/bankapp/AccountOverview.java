package com.example.bankapp;

import android.accounts.Account;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AccountOverview extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private static ArrayList<BankAccount> accountsList = new ArrayList<>();
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        init();
        loadAccounts();


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
                            adapter.notifyDataSetChanged();

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
        this.recyclerView = findViewById(R.id.recyclerView);
        this.adapter = new Adapter(getApplicationContext(), accountsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        this.database = FirebaseDatabase.getInstance();
    }
}
