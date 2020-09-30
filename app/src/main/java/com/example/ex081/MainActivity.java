package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    boolean type= false;
    EditText first,nextEd;
    String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (EditText) findViewById(R.id.first);
        nextEd = (EditText) findViewById(R.id.nextEd);
    }

    public void change(View view) {
        if (type)
            type= false;
        else
            type= true;
    }

    public void nextActivity(View view) {
        str1 = first.getText().toString();
        str2 = nextEd.getText().toString();
        if ((str1.equals("")) || (str2.equals(""))) {
            if ((str1.equals("")) && (str2.equals(""))) {
                first.setHint("missing");
                nextEd.setHint("missing");
            }
            else if (str1.equals(""))
                first.setHint("missing");
            else
                nextEd.setHint("missing");
        }
        else {
            Intent check = new Intent(this, TheSeries.class);
            check.putExtra("type", type);
            check.putExtra("firstNum", Double.parseDouble(str1));
            check.putExtra("next", Double.parseDouble(str2));
            startActivity(check);
        }
    }
}