package com.raj.sagar.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    ArrayList<String> titles;
    NewsAdapter adapter;
    ArrayList<Integer> urlsToImage;
    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMAGE = 343;
    ArrayList<String> descriptions;
    CardView cardView;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        FirebaseUser user = mAuth.getCurrentUser();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }
            }
        };
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Form.class);

                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View hView =  navigationView.getHeaderView(0);
        TextView email = (TextView)hView.findViewById(R.id.email);
        TextView displayName = (TextView)hView.findViewById(R.id.display_name);
        ImageView dp = (ImageView)hView.findViewById(R.id.display_picture);
        email.setText(user.getDisplayName());
        displayName.setText(user.getEmail());
        Picasso.with(getApplicationContext()).load(user.getPhotoUrl()).into(dp);




        RecyclerView recyclerView = findViewById(R.id.recylerView);
        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        titles = new ArrayList<String>();
        descriptions = new ArrayList<String>();

        urlsToImage = new ArrayList<Integer>();

        cardView = findViewById(R.id.cardView);

        adapter = new NewsAdapter(androidColors, titles, descriptions,urlsToImage, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);





        titles.add("Jaipur");
        titles.add("Udaipur");
        titles.add("Kota");
        descriptions.add("Jaipur");
        descriptions.add("Udaipur");
        descriptions.add("Kota");
        urlsToImage.add(R.drawable.japur);
        urlsToImage.add(R.drawable.udaipur);
        urlsToImage.add(R.drawable.kota);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } else if (id == R.id.nav_gallery) {



        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");

            Intent form = new Intent(this, Form.class);

            form.putExtra("BitmapImage", mphoto);
            startActivity(form);
        }
    }
}
