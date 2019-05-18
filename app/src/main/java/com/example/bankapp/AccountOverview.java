package com.example.bankapp;

import android.accounts.Account;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AccountOverview extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        init();

    }

    public void init(){
        this.recyclerView = findViewById(R.id.recyclerView);
        Set<BankAccount> accountsSet = new HashSet<>();
        Adapter adapter = new Adapter(this, new ArrayList<BankAccount>(accountsSet));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
