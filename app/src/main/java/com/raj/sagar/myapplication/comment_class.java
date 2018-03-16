package com.raj.sagar.myapplication;

/**
 * Created by SAGAR on 17-03-2018.
 */

public class comment_class
{
    private String comment;
    private long id;
    public comment_class(String comment)
    {
        this.comment = comment;
        this.id=-1;
    }
    public comment_class(String comment,long id)
    {
        this.comment = comment;
        this.id=id;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String time) {
        this.comment = time;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
