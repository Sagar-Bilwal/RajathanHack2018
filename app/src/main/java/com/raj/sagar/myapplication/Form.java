package com.raj.sagar.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Form extends AppCompatActivity {

    int RESULT_LOAD_IMAGE = 100;
    Bitmap bitmaps;
    ImageView img;

    ///Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        img = (ImageView)findViewById(R.id.img);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> categories = new ArrayList<String>();
        categories.add("Jaipur");
        categories.add("Kota");
        categories.add("Udaipur");
        Intent intent = getIntent();
        Bitmap camera_img_bitmap = (Bitmap) intent
                .getParcelableExtra("BitmapImage");
        if (camera_img_bitmap != null) {
            img.setImageBitmap(camera_img_bitmap);
        }




        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);
        spinner.setAdapter(dataAdapter);


        Button get_image = (Button) findViewById(R.id.get_image);
        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE && null!=data) {
            Uri imageUri = data.getData();

            ImageView imageView=findViewById(R.id.img);
            imageView.setImageURI(imageUri);


        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send:
                Toast.makeText(getApplicationContext(), "Sending data", Toast.LENGTH_LONG).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
