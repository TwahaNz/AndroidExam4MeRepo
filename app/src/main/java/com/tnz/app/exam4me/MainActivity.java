package com.tnz.app.exam4me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_items;
    private String[] items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btn_login(View view) {

        setContentView(R.layout.activity_main);

        lv_items = (ListView) findViewById(R.id.listView);

        items = new String[]{
                "APPLIED SCIENCES",
                "BUSINESS AND MANAGEMENT SCIENCES",
                "EDUCATION", "ENGINEERING",
                "HEALTH AND WELLNESS SCIENCES",
                "INFORMATICS AND DESIGN"
                };

        adapter = new MyAdapter(this, items);

        lv_items.setAdapter(adapter);

        lv_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
