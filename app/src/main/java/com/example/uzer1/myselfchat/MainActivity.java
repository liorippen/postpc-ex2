package com.example.uzer1.myselfchat;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayAdapter<String> listAdapter;
    Button b;
    EditText et;
    ListView myListView;
    Calendar cur_time;
    Fragment_Message fm = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(listAdapter);
        b = (Button) findViewById(R.id.sendBtn);
        et = (EditText) findViewById(R.id.msgText);


        b.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        cur_time= Calendar.getInstance();
                        String t = cur_time.get(Calendar.HOUR_OF_DAY) + ":" + cur_time.get(Calendar.MINUTE);
                        String d = cur_time.get(Calendar.DAY_OF_MONTH) + " / " + cur_time.get(Calendar.MONTH) + " / " + cur_time.get(Calendar.YEAR);
                        messages.add("lior: " + et.getText().toString() + " " + t);
                        dates.add(d);
                        listAdapter.notifyDataSetChanged();
                    }
                });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                fm = Fragment_Message.newInstance(dates.get(pos), messages, pos);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_layout, fm);
                fragmentTransaction.commit();


                return true;
            }
        });
    }

    @Override
    public void onBackPressed()
    {  
        super.onBackPressed();
        int p;
        if (fm != null && fm.is_delete()) {
            p = fm.to_delete();
            messages.remove(p);
            dates.remove(p);
            listAdapter.notifyDataSetChanged();
        }
    }
}
