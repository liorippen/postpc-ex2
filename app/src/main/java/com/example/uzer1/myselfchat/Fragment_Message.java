package com.example.uzer1.myselfchat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class Fragment_Message extends Fragment {

    private boolean delete_flag;


    public int to_delete()
    {
        return getArguments().getInt("pos");
    }

    public boolean is_delete()
    {
        return delete_flag;
    }

    public static Fragment_Message newInstance(String s, ArrayList<String> messages, int pos)
    {
        Fragment_Message fm = new Fragment_Message();
        Bundle args = new Bundle();
        fm.delete_flag = false;
        args.putString("date_str", s);
        args.putStringArrayList("messages", messages);
        args.putInt("pos", pos);
        fm.setArguments(args);
        return fm;
    }



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message,
                container, false);

        View layout = view.findViewById(R.id.fragment_message_layout);
        TextView textView1 = (TextView) view.findViewById(R.id.fragment_date);
        TextView textView2 = (TextView) view.findViewById(R.id.fragment_name);

        layout.setBackgroundColor(Color.WHITE);
        textView1.setText("date: " + getArguments().getString("date_str"));
        textView2.setText(" sender: Lior ippen");

        Button b = (Button) view.findViewById(R.id.deleteBtn);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                delete_flag = true;
                getActivity().onBackPressed();
            }
        });

        Button c = (Button) view.findViewById(R.id.closeBtn);
        c.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

}
