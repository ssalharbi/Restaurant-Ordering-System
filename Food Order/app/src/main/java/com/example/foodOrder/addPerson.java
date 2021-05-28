package com.example.foodOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addPerson extends AppCompatActivity {

    EditText EditText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        getSupportActionBar().hide();

        EditText = (EditText) findViewById(R.id.editTextNumber);
        button = (Button) findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {




                Bundle bundle = getIntent().getExtras();
                String value = bundle.getString("table_no");
              //  String value2 = bundle.getString("some_other_key");


                Toast.makeText(addPerson.this, value, Toast.LENGTH_LONG).show();





                Intent i = new Intent(addPerson.this, orderDetail.class);
                i.putExtra("person_no", EditText.getText().toString());
                i.putExtra("table_no", value);

                startActivity(i);


            }
        });



    }
}