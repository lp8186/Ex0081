package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

import static android.view.View.*;

public class TheSeries extends AppCompatActivity implements AdapterView.OnItemClickListener {
    boolean type2;
    double num1,difference,sum1;
    TextView n1,d1,place,sum;
    String [] numbers= new String [20];
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_series);

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);
        n1 = (TextView) findViewById(R.id.n1);
        d1 = (TextView) findViewById(R.id.d1);
        place = (TextView) findViewById(R.id.place);
        sum = (TextView) findViewById(R.id.sum);

        Intent getInformation = getIntent();
        type2 = getInformation.getBooleanExtra("type",false);
        num1 = getInformation.getDoubleExtra("firstNum",0);
        difference= getInformation.getDoubleExtra("next",0);


        n1.setText(String.valueOf(num1));
        d1.setText(String.valueOf(difference));


        numbers[0]= String.valueOf(num1);
        if (type2) {
            for (int i=1; i<20; i++)
                numbers[i] = String.valueOf(Double.parseDouble(numbers[i - 1]) * difference);
        }

        else{
            for (int i=1; i<20; i++)
                numbers[i] = String.valueOf(Double.parseDouble(numbers[i - 1]) + difference);
        }


        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,numbers);
        list.setAdapter(adp);




    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sum1=0;
        place.setText(String.valueOf(position+1));
        for (int l=0; l<=position; l++)
            sum1= sum1+Double.parseDouble(numbers[l]);
        sum.setText(String.valueOf(sum1));

    }
}