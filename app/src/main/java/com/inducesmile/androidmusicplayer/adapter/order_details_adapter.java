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
