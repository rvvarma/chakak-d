package com.inducesmile.androidmusicplayer.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.androidmusicplayer.R;

public class PlaylistViewHolder extends RecyclerView.ViewHolder{

    public TextView orderid;
    public TextView name;
    public TextView time;
    public Button Accept;


    public PlaylistViewHolder(View itemView, TextView songTitle, TextView songAuthor, TextView time) {
        super(itemView);
        this.orderid = songTitle;
        this.name = songAuthor;
        this.time = time;
    }

    public PlaylistViewHolder(View itemView) {
        super(itemView);

        orderid = (TextView)itemView.findViewById(R.id.orderid);
        name = (TextView)itemView.findViewById(R.id.name);
        time = (TextView) itemView.findViewById(R.id.time);
        Accept=(Button) itemView.findViewById(R.id.submit);
    }

}
