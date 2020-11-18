package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.EditText;


/**
 * @author Liad Peretz
 * @version 1.0
 * @since 10/11/20
 * Short description- MainActivity use for absorption of the series type, the first number in the series and the difference or multiplier of the series.
 */
public class MainActivity extends AppCompatActivity {

    boolean type= false;
    EditText first, nextEd;
    String str1, str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (EditText) findViewById(R.id.first);
        nextEd = (EditText) findViewById(R.id.nextEd);
    }


    /**
     * Change
     * Short description- use for changing the series type.
     * <p>
     *     View view
     * @param view the view
     */
    public void change(View view) {
        if (type)
            type= false;
        else
            type= true;
    }


    /**
     * Next activity.
     * Short description- use for moving to TheSeries Activity.
     * <p>
     *     View view
     * @param view the view
     */
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