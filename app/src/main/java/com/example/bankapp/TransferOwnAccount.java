package com.example.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class TransferOwnAccount extends AppCompatActivity {

    private Spinner accountFrom, accountTo;
    private EditText transferAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_own_account);
        init();
    }

    public void transferMoney(View view){
        ArrayList<String> accounts = new ArrayList<>();

        //accounts.add();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accounts);
        accountFrom.setAdapter(adapter);
        accountTo.setAdapter(adapter);

    }

    public void init(){
        this.accountFrom = findViewById(R.id.transferFromSpinner);
        this.accountTo = findViewById(R.id.transferToSpinner);
    }
}
