package com.inducesmile.androidmusicplayer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.inducesmile.androidmusicplayer.R;
import com.inducesmile.androidmusicplayer.entities.order_details_object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class order_details_adapter extends RecyclerView.Adapter<order_details_viewholder> {
    SharedPreferences sharedpreferences;

    private Context context;
    private List<order_details_object> allSongs;
public static int counting=0;
    public order_details_adapter(Context context, List<order_details_object> allSongs) {
        this.context = context;
        this.allSongs = allSongs;
    }

    @Override
    public order_details_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_details_adapter, parent, false);


        return new order_details_viewholder(view);
    }

    @Override
    public void onBindViewHolder(final order_details_viewholder holder, final int position) {


        final order_details_object songs = allSongs.get(position);
        System.out.println("dakj "+songs.getname());
        holder.qty.setText("X "+songs.getqty());
        holder.name.setText(songs.getname());
        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //System.out.println(" psa "+songs.getname());
                if(holder.chk.isChecked()){
                    setcount();
                }
                else{
                 removecount();
                }
            }
        });
       /* holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"df "+songs.getorderid(),Toast.LENGTH_LONG).show();
                AsyncTaskRunner ast=new AsyncTaskRunner();
                ast.execute(songs.getorderid());
            }
        });
*/
    }



    @Override
    public int getItemCount() {
        return allSongs.size();
    }
    private void setPostRequestContent(HttpURLConnection conn,
                                       JSONObject jsonObject) throws IOException {

        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        os.writeBytes(jsonObject.toString());
        // Log.i(MainActivity.class.toString(), jsonObject.toString());
        os.flush();
        os.close();
        os.close();
    }
    public static int count(){


        return counting;

    }
    public void setcount(){
        counting++;
    }
    public void removecount(){
        counting--;
    }

}
