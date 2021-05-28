package com.example.foodOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String[] maintitle ={
            "Title 1",
            "Title 2",
            "Title 3",
            "Title 4",
            "Title 5",
            "Title 1",
            "Title 2",
            "Title 3",
            "Title 4",
            "Title 5",
    };


    String[] subtitle ={
            "Sub Title 1","Sub Title 2",
            "Sub Title 3","Sub Title 4",
            "Sub Title 5",
            "Sub Title 1","Sub Title 2",
            "Sub Title 3","Sub Title 4",
            "Sub Title 5",
    };

    Integer[] imgid={
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
            R.drawable.download,
    };





    Button buttonSubmit;
    // Array of strings...
//    String[] mobileArray = {};

    //ArrayList<String> dataarr = new ArrayList<String>();

    ArrayList<String> tableno = new ArrayList<String>();
    ArrayList<String> totaLAmout = new ArrayList<String>();
    ArrayList<String> personarr = new ArrayList<String>();
    ArrayList<String> timearr = new ArrayList<String>();
    ArrayList<String> itemOne = new ArrayList<String>();
    ArrayList<String> itemTwo = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();


        buttonSubmit = (Button) findViewById(R.id.button3);




        ListView listView = (ListView) findViewById(R.id.activity_listview);




        FirebaseFirestore db = FirebaseFirestore.getInstance();



        mAuth = FirebaseAuth.getInstance();
        JSONObject obj=new JSONObject();
        Intent i = new Intent(Dashboard.this, viewOrder.class);
        db.collection("users")
                .get()
                .addOnCompleteListener(Dashboard.this,new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            int indexValue=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("result", document.getId() + " => " + document.getData());
                                Log.d("result", document.getId() + " => " + document.getData().get("Table No"));
                                maintitle[indexValue] ="Table No #"+String.valueOf(document.getData().get("Table No"));
                                    //subtitle



                               // dataarr.add(indexValue, document.getData().toString());

                                personarr.add(indexValue,document.getData().get("Person").toString());
                                tableno.add(indexValue,document.getData().get("Table No").toString());
                                totaLAmout.add(indexValue,document.getData().get("Total").toString());
                                timearr.add(indexValue,document.getData().get("Time").toString());

                             //   itemOne.add(indexValue,document.getData().get("Chips ").toString());
                             //   itemTwo.add(indexValue,document.getData().get("Onion Bhaji").toString());

                                subtitle[indexValue] =String.valueOf(document.getData().get("Time"))+" min";

                                indexValue++;
                            }
                        } else {
                           Log.w("result", "Error getting documents.", task.getException());
                        }

                        MyListAdapter adapter=new MyListAdapter(Dashboard.this, maintitle, subtitle,imgid);

                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                //   TODO Auto-generated method stub

                                String value=adapter.getItem(position);

                                //Toast.makeText(getApplicationContext(),dataarr.get(position) ,Toast.LENGTH_SHORT).show();


                                Bundle bundle = getIntent().getExtras();
                              //  String value = bundle.getString("table_no");
                                //  String value2 = bundle.getString("some_other_key");


                              //  Toast.makeText(addPerson.this, value, Toast.LENGTH_LONG).show();





                                //Intent i = new Intent(addPerson.this, orderDetail.class);





                                i.putExtra("person",personarr.get(position));
                                i.putExtra("total",totaLAmout.get(position));
                                //i.putExtra("item1",itemOne.get(position));
                                //i.putExtra("item2",itemTwo.get(position));
                                i.putExtra("table",tableno.get(position));
                                i.putExtra("time",timearr.get(position));
                               //i.putExtra("table_no", value);
                                startActivity(i);


                            }
                        });

                    }
                });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                Toast.makeText(Dashboard.this, "Button clicked", Toast.LENGTH_LONG).show();

                Intent i = new Intent(Dashboard.this, tableOrder.class);

                startActivity(i);


            }
        });


    }
}