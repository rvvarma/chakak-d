package com.inducesmile.androidmusicplayer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inducesmile.androidmusicplayer.R;
import com.inducesmile.androidmusicplayer.entities.SongObject;
import com.inducesmile.androidmusicplayer.entities.pendingorders;
import com.inducesmile.androidmusicplayer.order_Details;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder> {

    private Context context;
    private List<SongObject> allSongs;


    public SongAdapter(Context context, List<SongObject> allSongs) {
        this.context = context;
        this.allSongs = allSongs;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_layout, parent, false);


        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, final int position) {
        final SongObject songs = allSongs.get(position);
        holder.orderid.setText(songs.getime());
        System.out.println(songs.getorderid());
        holder.itemcount.setText("Items : "+songs.getitemcounts());
        holder.payment.setText("Payment Mode : "+songs.getpaymentmode());
        holder.price.setText("TotalAmount : "+songs.gettotalprice());
        holder.status.setText("Status : "+songs.getStatus()  );
      /*  if(songs.getStatus().equals("open"))
        holder.card.setCardBackgroundColor(Color.YELLOW);
        else
            holder.card.setCardBackgroundColor(Color.GREEN);*/


/*        Date date = new java.util.Date(Integer.parseInt(songs.getime())*1000L);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss ");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+5:30"));
        String formattedDate = sdf.format(date);
*/
       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss:S");
      //  Timestamp ts= Timestamp.valueOf(formattedDate);
        holder.time.setText(songs.getime());
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ip=new Intent(context, order_Details.class);
                ip.putExtra("id",songs.getorderid());
                context.startActivity(ip);
            }
        });


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        String resp;
        ProgressDialog progressDialog;
        public static final String REQUEST_METHOD = "PUT";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        String inputLine;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                URL myUrl = new URL("https://3q4jnoy6zf.execute-api.ap-south-1.amazonaws.com/prod/orderdetails-delivery-boys");
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setRequestProperty("Content-Type","application/json");
JSONObject data=new JSONObject();
data.put("operation","put");
data.put("status","pending");
                data.put("orderid",params[0]);
//String pop='{operation:post}';

                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                os.writeBytes(data.toString());
                // Log.i(MainActivity.class.toString(), jsonObject.toString());
                os.flush();
                os.close();
                os.close();                //Connect to our url
             //   connection.connect();
System.out.println(" code :"+connection.getResponseCode());
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                resp = stringBuilder.toString();
            }
            catch(Exception e){
                e.printStackTrace();
                resp = null;
            }
            return resp;

        }



        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            // finalResult.setText(result);

            JSONObject jb= null;
            try {
               // jb = new JSONObject(result);
                System.out.println("check2 "+result);

                }
             catch (Exception e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context,"Slug", "Please Wait");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //  finalResult.setText(text[0]);

        }
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

}
