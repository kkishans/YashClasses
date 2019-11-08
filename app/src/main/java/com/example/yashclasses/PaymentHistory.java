package com.example.yashclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentHistory extends AppCompatActivity {

    TextView Sname;
    RecyclerView recyclerView;
    DatabaseHelper mydb;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        recyclerView = findViewById(R.id.Payment_recyclerview);
        Sname = findViewById(R.id.txtStudentName);

        mydb  = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        sid = intent.getStringExtra("Sid");
        Sname.setText(intent.getStringExtra("Sname"));

        populatePaymentHistory();

    }

    private void populatePaymentHistory() {
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        Cursor c = mydb.getPaymentHistoryForStudent(sid);
        c.moveToFirst();
        int l = c.getCount();
        if (l == 0)
            Toast.makeText(this, "No fees recieved yet", Toast.LENGTH_SHORT).show();
        PaymentAdapter paymentAdapter = new PaymentAdapter(getApplicationContext(),c,l);
        recyclerView.setAdapter(paymentAdapter);
    }
}
