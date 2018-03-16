package com.raj.sagar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MomumentList extends AppCompatActivity {
    private ArrayList<String> titlesmonument;
    private ArrayList<String> descriptionsmonument;

    private ArrayList<Integer> urlsToImagemonument;
    MonumentAdapter adaptermonument;
    CardView cardViewmonument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momument_list);

        RecyclerView recyclerViewmonument = findViewById(R.id.recylerViewmonument);
        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        titlesmonument = new ArrayList<String>();
        descriptionsmonument = new ArrayList<String>();

        urlsToImagemonument = new ArrayList<Integer>();
        cardViewmonument = findViewById(R.id.cardViewmonument);
        adaptermonument = new MonumentAdapter(androidColors, titlesmonument, descriptionsmonument,urlsToImagemonument, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewmonument.setLayoutManager(layoutManager);
        recyclerViewmonument.setAdapter(adaptermonument);

        titlesmonument.add("harshit");
        titlesmonument.add("sagar");
        descriptionsmonument.add("very good");
        descriptionsmonument.add("loving it");
        urlsToImagemonument.add(R.drawable.udaipur);
        urlsToImagemonument.add(R.drawable.kota);
    }
}
