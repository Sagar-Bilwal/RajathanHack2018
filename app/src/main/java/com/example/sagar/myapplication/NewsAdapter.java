package com.example.sagar.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SAGAR on 15-03-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<String> titles;
    private Context context;
    private ArrayList<String> descriptions;


    private ArrayList<Integer> urlsToImage;

    private int[] androidColors;

    public NewsAdapter(int[] androidColors, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<Integer> urlsToImage, Context context) {
        this.titles = titles;
        this.descriptions = descriptions;

        this.context = context;
        this.urlsToImage = urlsToImage;
        this.androidColors = androidColors;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position) {
        holder.title.setText(titles.get(position));
        holder.description.setText((descriptions.get(position)));


        int randomColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.linearLayout.setBackgroundColor(randomColor);
        Picasso.with(context).load(urlsToImage.get(position)).into(holder.imageView);
//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(context, titles.get(position), Toast.LENGTH_LONG);
//
//            }
//        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///context.startActivity(new Intent(context, Main2Activity.class).putExtra("url", urls.get(position)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                Toast.makeText(context, titles.get(position), Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;

        public TextView title, description;
        public ImageView imageView;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.textView1);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            description = (TextView) itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){

            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());

        }
    }
}

