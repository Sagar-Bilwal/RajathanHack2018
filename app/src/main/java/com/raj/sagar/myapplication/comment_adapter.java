package com.raj.sagar.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SAGAR on 17-03-2018.
 */

public class comment_adapter extends BaseAdapter
{
    private Context context;
    private ArrayList<comment_class> comment_list;

    public comment_adapter(Context context, ArrayList<comment_class> comment_list)
    {

        this.context = context;
        this.comment_list = comment_list;
    }
    @Override
    public int getCount() {
        return comment_list.size();
    }

    @Override
    public comment_class getItem(int position) {
        return comment_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View current_view=convertView;
        if(current_view==null)
        {
            current_view = layoutInflater.inflate(R.layout.comment_item,parent,false);
            CommentViewHolder viewHolder=new CommentViewHolder(current_view);
            current_view.setTag(viewHolder);
        }
        CommentViewHolder viewHolder=(CommentViewHolder) current_view.getTag();
        comment_class comment_detail=getItem(position);
        viewHolder.comment.setText(comment_detail.getComment());

        return current_view;
    }
    class CommentViewHolder
    {
        TextView comment;
        CommentViewHolder(View current_view)
        {
            comment=current_view.findViewById(R.id.comment_body);
        }
    }

}

