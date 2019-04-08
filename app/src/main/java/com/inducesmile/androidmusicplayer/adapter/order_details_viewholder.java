package com.inducesmile.androidmusicplayer.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.androidmusicplayer.R;


public class order_details_viewholder extends RecyclerView.ViewHolder{

    public TextView orderid;
    public TextView name;
    public CheckBox chk;
    public TextView qty;
    public Button Accept;


    public order_details_viewholder(View itemView, TextView songTitle, TextView songAuthor) {
        super(itemView);
        this.name = songTitle;
        this.qty = songAuthor;
    }

    public order_details_viewholder(View itemView) {
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.name);
        qty = (TextView) itemView.findViewById(R.id.qty);
        chk = (CheckBox) itemView.findViewById(R.id.setclick);
    }
}
