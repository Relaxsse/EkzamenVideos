package com.example.ekzamenvideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public final static String EXTRA_MESSAGES = "com.example.ekzamenvideos.EXTRA_MESSAGES";

    String[] videosLikes = new String[5];

    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String like = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        videosLikes[0] = like;


        ListView listView = (ListView) findViewById(R.id.likesVideoView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, videosLikes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fal = videosLikes[i];
                Intent r = new Intent(MainActivity2.this, MainActivity.class);
                r.putExtra(EXTRA_MESSAGES, fal);
                startActivity(r);
            }
        });
    }
}