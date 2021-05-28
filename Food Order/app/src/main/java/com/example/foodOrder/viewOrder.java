package com.example.foodOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class viewOrder extends AppCompatActivity {

    TextView
            orderdetailshowdata
            ,perrsonshow ,tablenumbershow,totalamount,totalAmounttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        getSupportActionBar().hide();


        orderdetailshowdata = (TextView)findViewById(R.id.orderdetailshowdata);
        perrsonshow  = (TextView)findViewById(R.id.orderdetailshowperson);
        tablenumbershow=(TextView)findViewById(R.id.tablenumbershow);
        totalamount=(TextView)findViewById(R.id.totalAmount);
        totalAmounttime= (TextView)findViewById(R.id.totalAmounttime);

                //orderdetailshowperson
        Bundle bundle = getIntent().getExtras();
        String person = bundle.getString("person");
        String tableno = bundle.getString("table");
        String total = bundle.getString("total");
        String time = bundle.getString("time");
//        orderdetailshowdata.setText(resultOrder.toString().substring(1,resultOrder.length()-2));


     //   orderdetailshowdata.setText(person);
        perrsonshow.setText(person);
        tablenumbershow.setText(" #"+tableno);
        totalamount.setText(total +"$");
        totalAmounttime.setText(time +"Min");
    }
}