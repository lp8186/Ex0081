package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * @author Liad Peretz
 * @version 1.0
 * @since 10/11/20
 * Short description- TheSeries use for presentation of the first 20 numbers in the series.
 * In addition A long press on any number in the series will display a menu where you can select:
 * to display the position of the number in the series or the sum of all the numbers from the beginning of the series till the choosing number.
 */
public class TheSeries extends AppCompatActivity implements View.OnCreateContextMenuListener{
    boolean type2;
    double num1, difference,sum1;
    int pos;
    TextView sop1,sop2;
    String[] numbers = new String[20];
    ListView list;


    /**
     * OnCreate
     * Short description- use for starting on the activity.
     *  <p>
     *      Bundle savedInstanceState
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_series);

        list = (ListView) findViewById(R.id.list);
        list.setOnCreateContextMenuListener(this);
        sop1 = (TextView) findViewById(R.id.sop1);
        sop2 = (TextView) findViewById(R.id.sop2);


        Intent getInformation = getIntent();
        type2 = getInformation.getBooleanExtra("type", false);
        num1 = getInformation.getDoubleExtra("firstNum", 0);
        difference = getInformation.getDoubleExtra("next", 0);

        numbers[0] = String.valueOf(num1);
        if (type2) {
            for (int i = 1; i < 20; i++)
                numbers[i] = String.valueOf(Double.parseDouble(numbers[i - 1]) * difference);
        } else {
            for (int i = 1; i < 20; i++)
                numbers[i] = String.valueOf(Double.parseDouble(numbers[i - 1]) + difference);
        }


        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, numbers);
        list.setAdapter(adp);


    }


    /**
     * Back
     * Short description- use for going back to Main Activity.
     * <p>
     *     View view
     * @param view the view
     */
    public void back(View view) {
        finish();
    }


    /**
     * OnCreateContextMenu
     * Short description- use for creating the contextMenu.
     * <p>
     *     ContextMenu menu
     *     View v
     *     ContextMenuInfo menuInfo
     * @param menu the object
     * @param v the selected item
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("options");
        menu.add("place");
        menu.add("sum");
    }


    /**
     * OnContextItemSelected
     * Short description- use for displaying the the position of the selected number or the sum of of all the numbers from the beginning of the series till the choosing number.
     * <p>
     *     item
     * @param item the selected item
     * @return true if it worked.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        pos = info.position+1;
        String str = item.getTitle().toString();
        if (str.equals("place")) {
            sop1.setText("place");
            sop2.setText(String.valueOf(pos));
        }
        else if (str.equals("sum")) {
            sum1=0;
            for (int l=0; l<=pos-1; l++)
                  sum1= sum1+Double.parseDouble(numbers[l]);
            sop1.setText("sum");
            sop2.setText(String.valueOf(sum1));
        }
        return true;
    }

}
