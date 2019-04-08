package com.inducesmile.androidmusicplayer.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inducesmile.androidmusicplayer.R;
import com.inducesmile.androidmusicplayer.adapter.PlaylistAdapter;
import com.inducesmile.androidmusicplayer.adapter.SongAdapter;
import com.inducesmile.androidmusicplayer.entities.PlaylistObject;
import com.inducesmile.androidmusicplayer.entities.SongObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlaylistFragment extends Fragment {
    PlaylistAdapter mAdapter;
    RecyclerView playlisRecyclerView;
    List<PlaylistObject> neworders ;

    public PlaylistFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

         playlisRecyclerView = (RecyclerView)view.findViewById(R.id.your_play_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        playlisRecyclerView.setLayoutManager(linearLayoutManager);
        playlisRecyclerView.setHasFixedSize(true);
        neworders = new ArrayList<PlaylistObject>();

        System.out.println("fragment : plalist");
        AsyncTaskRunner art=new AsyncTaskRunner();
        art.execute();
        return view;
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        String resp;
        ProgressDialog progressDialog;
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        String inputLine;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                URL myUrl = new URL("https://3q4jnoy6zf.execute-api.ap-south-1.amazonaws.com/prod/orderdetails-delivery-boys?operation=get&&dbid=1&&status=pending");
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
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
                System.out.println("modera "+result);
                jb = new JSONObject(result);
                JSONArray ordersiterate;
                ordersiterate=jb.getJSONArray("data");
                System.out.println("checklppp "+ordersiterate);

                if(jb.getString("Message").equals("Success")) {

                    for(int i=0;i<ordersiterate.length();i++) {

                        System.out.println("sd "+ordersiterate.length());

                        JSONObject jb1=ordersiterate.getJSONObject(i);
                        System.out.println("sd "+jb1.getString("orderid"));
                        //    Timestamp timestamp1 = new Timestamp(new Date(jb1.getString("time")));

                        neworders.add(new PlaylistObject(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time")));

                    }
                    mAdapter = new PlaylistAdapter(getActivity(), neworders);
                    mAdapter.notifyDataSetChanged();

                    playlisRecyclerView.setAdapter(mAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(),"Slug", "Please Wait");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //  finalResult.setText(text[0]);

        }
    }



}
