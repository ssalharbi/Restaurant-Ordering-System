package com.example.foodOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tableOrder extends AppCompatActivity {


    EditText EditText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_order);
        getSupportActionBar().hide();

        EditText = (EditText) findViewById(R.id.editTextNumber);
        button = (Button) findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                Toast.makeText(tableOrder.this, "Button clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(tableOrder.this, addPerson.class);

                i.putExtra("table_no", EditText.getText().toString());

                startActivity(i);


            }
        });



    }
}