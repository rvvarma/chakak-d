package com.inducesmile.androidmusicplayer.entities;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inducesmile.androidmusicplayer.R;
import java.util.ArrayList;

/**
 * Created by Angel on 7/31/2016.
 */
public class MyExpandableAdapter implements ExpandableListAdapter {
    Context context = null;
    ArrayList<items_sorter> originalContinentList;
    ArrayList<items_sorter> continentList;

    public MyExpandableAdapter(Context context, ArrayList<items_sorter> data) {
        this.context = context;
        this.originalContinentList = new ArrayList<items_sorter>();
        this.originalContinentList.addAll(data);

        this.continentList = new ArrayList<items_sorter>();
        this.continentList.addAll(data);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public boolean isEmpty() {
        if (continentList.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return continentList.get(i).getCountry().size();
    }

    @Override
    public items_sorter getGroup(int i) {

        return continentList.get(i);
    }

    @Override
    public status_sorter getChild(int groupPosition, int childPosition) {
        return continentList.get(groupPosition).getCountry().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean b, View contentView, ViewGroup parent) {

        items_sorter continent = continentList.get(position);
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.groupexpandable, parent, false);
        }
        TextView tvContinentName = (TextView) contentView.findViewById(R.id.tvContinentName);

        tvContinentName.setText(continent.getName());


        return contentView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View contentView, ViewGroup parent) {
        status_sorter country = continentList.get(groupPosition).getCountry().get(childPosition);
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.song_list_layout, parent, false);
        }     TextView orderid;
         TextView payment;
         TextView time;
         TextView itemcount;
         TextView price;
         TextView status;
         Button Accept;
         CardView card;
         Button submit;
        orderid = (TextView)contentView.findViewById(R.id.orderid);
        payment = (TextView)contentView.findViewById(R.id.payment);
        time = (TextView) contentView.findViewById(R.id.time);
        itemcount=(TextView) contentView.findViewById(R.id.itemcount);
        price=(TextView) contentView.findViewById(R.id.totalprice);
        status=(TextView) contentView.findViewById(R.id.status);
        card=(CardView) contentView.findViewById(R.id.card);
        submit=(Button) contentView.findViewById(R.id.viewbutton);

        orderid.setText(country.getorderid());
        payment.setText(country.getpaymentmode());
        time.setText(country.getime());
       // itemcount.setText(country.getitemcounts());
        price.setText(country.gettotalprice());
        status.setText(country.getStatus());


        return contentView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return l1;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return l;
    }
}
