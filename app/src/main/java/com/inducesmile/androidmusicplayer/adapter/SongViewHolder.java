package com.inducesmile.androidmusicplayer.adapter;


import android.speech.tts.TextToSpeech;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.androidmusicplayer.R;


public class SongViewHolder extends RecyclerView.ViewHolder{

    public TextView orderid;
    public TextView payment;
    public TextView time;
    public TextView itemcount;
    public TextView price;
    public TextView status;
    public Button Accept;
    public CardView card;
    public Button submit;

    public SongViewHolder(View itemView, TextView orderid, TextView payment, TextView item, TextView time, TextView price,TextView status,CardView card,Button submit) {
        super(itemView);
        this.orderid = orderid;
        this.payment = payment;
        this.itemcount = item;
        this.time=time;
        this.price=price;
        this.status=status;
        this.card=card;
        this.submit=submit;
    }

    public SongViewHolder(View itemView) {
        super(itemView);

        orderid = (TextView)itemView.findViewById(R.id.orderid);
        payment = (TextView)itemView.findViewById(R.id.payment);
        time = (TextView) itemView.findViewById(R.id.time);
        itemcount=(TextView) itemView.findViewById(R.id.itemcount);
        price=(TextView) itemView.findViewById(R.id.totalprice);
        status=(TextView) itemView.findViewById(R.id.status);
        card=(CardView) itemView.findViewById(R.id.card);
        submit=(Button) itemView.findViewById(R.id.viewbutton);
    }
}
