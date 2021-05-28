package com.example.foodOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class orderDetail extends AppCompatActivity {


    CheckBox chipx,saledx,Onion_Bhajix,Coconut_Shrimpx;
    Button buttonSubmit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().hide();


        addListenerOnButtonClick();


    }


    public void addListenerOnButtonClick(){

        chipx=(CheckBox)findViewById(R.id.chipsx);
        Onion_Bhajix=(CheckBox)findViewById(R.id.Onion_Bhajix12);
        Coconut_Shrimpx=(CheckBox)findViewById(R.id.Coconut_Shrimpx11);
        saledx=(CheckBox)findViewById(R.id.saledx);
        buttonSubmit = (Button)findViewById(R.id.button21);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int totalamount=0;
                StringBuilder result=new StringBuilder();
                Map<String, Object> user = new HashMap<>();
                Toast.makeText(orderDetail.this, "placed order", Toast.LENGTH_LONG).show();

                if(chipx.isChecked()){
                    result.append("\nChips 1$");
                    totalamount+=1;
                    user.put("Chips ", "Chips 1$");
                }
                if(Onion_Bhajix.isChecked()){
                    result.append("\nOnion Bhaji 1$");
                    totalamount+=1;
                    user.put("Onion  Bhaji ", "Onion Bhaji 1$");
                }
                if(Coconut_Shrimpx.isChecked()){
                    result.append("\nCoconut Shrimp 2$");
                    totalamount+=2;
                    user.put("Coconut Shrimp", "Coconut Shrimp 2$");
                }

                if(Onion_Bhajix.isChecked()){
                    result.append("\nOnion Bhaji 3$");
                    totalamount+=3;
                    user.put("Onion Bhaji", "Onion Bhaji 3$");
                }
                result.append("\nTotal: "+totalamount+"$");


                Random r = new Random();
                int low = 5;
                int high = 15;
                int resulttime = r.nextInt(high-low) + low;

                Bundle bundle = getIntent().getExtras();
                String value = bundle.getString("table_no");
                String person_no = bundle.getString("person_no");

                Intent i = new Intent(orderDetail.this, Dashboard.class);
                i.putExtra("person_no",person_no);
                i.putExtra("table_no", value);

                user.put("Person", person_no);
                user.put("Table No", value);

                user.put("Time", resulttime);
                user.put("Total",totalamount);

                Toast.makeText(orderDetail.this, result.toString(), Toast.LENGTH_LONG).show();




                FirebaseFirestore db = FirebaseFirestore.getInstance();


                //user.put("result", result.toString());



                mAuth = FirebaseAuth.getInstance();




        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                            // Log.d('asd', "DocumentSnapshot added with ID: " + documentReference.getId());
//                        result.setText("Name:\t" + name + "\nPassword:\t" + password );
                        Context context = getApplicationContext();
                        CharSequence text = "Data Saved";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent i = new Intent(orderDetail.this, Dashboard.class);
                        startActivity(i);
             // Initialize Firebase Auth


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    //Log.w('erro', "Error adding document", e);
//                        result.setText("Name:\t" + "not added" + "\nPassword:\t" + "not added password");
                    }
                });







            }
        });

    }
    }