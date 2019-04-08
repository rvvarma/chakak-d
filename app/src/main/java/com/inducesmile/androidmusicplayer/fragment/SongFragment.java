package com.inducesmile.androidmusicplayer.fragment;


import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.inducesmile.androidmusicplayer.R;
import com.inducesmile.androidmusicplayer.adapter.SongAdapter;
import com.inducesmile.androidmusicplayer.entities.MyExpandableAdapter;
import com.inducesmile.androidmusicplayer.entities.SongObject;
import com.inducesmile.androidmusicplayer.entities.items_sorter;
import com.inducesmile.androidmusicplayer.entities.pendingorders;
import com.inducesmile.androidmusicplayer.entities.status_sorter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SongFragment extends Fragment {
    List<SongObject> neworders ;
    List<pendingorders> pendingorders ;

    ArrayList<items_sorter> itemadapter = null;

    SongAdapter mAdapter;
    ExpandableListView expandableListView;
    MyExpandableAdapter adapter;

    SimpleDateFormat simpleDateFormat;
    public SongFragment() {
    }
    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        getActivity().setTitle("Slug");
         simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss:S");
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;


        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        itemadapter = new ArrayList<>();

        // adapter = new MyExpandableAdapter(this, continents);
        //expandableListView.setAdapter(adapter);
        expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        System.out.println("fragment : songlist");



AsyncTaskRunner ast=new AsyncTaskRunner();
ast.execute();
        neworders = new ArrayList<SongObject>();
        pendingorders = new ArrayList<pendingorders>();


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
                URL myUrl = new URL("https://3q4jnoy6zf.execute-api.ap-south-1.amazonaws.com/prod/orderdetails-delivery-boys?operation=get&&dbid=1&&status=open");
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
                jb = new JSONObject(result);
               /* JSONArray ordersiterate;
                ordersiterate=jb.getJSONObject("data");
                System.out.println("check "+ordersiterate);*/
                if(jb.getString("Message").equals("Success")) {

                    JSONObject categorizeitems=jb.getJSONObject("data");

                    Iterator it1 = categorizeitems.keys();
                    while(it1.hasNext()){
                        String s1 = (String) it1.next();
                        ArrayList<status_sorter> asiaCountries = new ArrayList<>();

                        String val = categorizeitems.optString(s1);

                        System.out.println( val +" fecki ");
                        items_sorter asiaContinent=null;

                        JSONArray ordersiterate=new JSONArray(val);
                        asiaContinent = new items_sorter(1,s1, null, R.drawable.bee);

                        for (int i = 0; i < ordersiterate.length(); i++) {

//
                            JSONObject jb1 = ordersiterate.getJSONObject(i);
                            //    Timestamp timestamp1 = new Timestamp(new Date(jb1.getString("time")));
                            int popp = new JSONArray(jb1.getString("itemid")).length();


                            asiaCountries.add(new status_sorter(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"), popp, jb1.getString("price"), "963564566", jb1.getString("payment"), jb1.getString("orderstatus")));



                            // neworders.add(new SongObject(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"),popp,jb1.getString("price"),"963564566",jb1.getString("payment"),jb1.getString("status")));
                            //  pendingorders.add(new pendingorders(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"),popp,jb1.getString("price"),"963564566",jb1.getString("payment"),jb1.getString("status")));

                        }
                        asiaContinent.setCountry(asiaCountries);

                        itemadapter.add(asiaContinent);


                    }

                    adapter = new MyExpandableAdapter(getActivity(), itemadapter);

                    expandableListView.setAdapter(adapter);
                 /*   for (int i = 0; i < ordersiterate.length(); i++) {

//
                        JSONObject jb1 = ordersiterate.getJSONObject(i);
                        //    Timestamp timestamp1 = new Timestamp(new Date(jb1.getString("time")));
                        int popp = new JSONArray(jb1.getString("itemid")).length();
                         asiaContinent = new items_sorter(i, jb1.getString("orderstatus"), null, R.drawable.bee);

                        asiaContinent1 = new items_sorter(i, jb1.getString("orderstatus"), null, R.drawable.bee);

                        asiaCountries.add(new status_sorter(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"), popp, jb1.getString("price"), "963564566", jb1.getString("payment"), jb1.getString("orderstatus")));
                        asiaCountries1.add(new status_sorter(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"), popp, jb1.getString("price"), "963564566", jb1.getString("payment"), jb1.getString("orderstatus")));

                        asiaContinent.setCountry(asiaCountries);
                        asiaContinent1.setCountry(asiaCountries1);


                        // neworders.add(new SongObject(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"),popp,jb1.getString("price"),"963564566",jb1.getString("payment"),jb1.getString("status")));
                        //  pendingorders.add(new pendingorders(jb1.getString("orderid"), jb1.getString("username"), jb1.getString("time"),popp,jb1.getString("price"),"963564566",jb1.getString("payment"),jb1.getString("status")));

                    }
                    itemadapter.add(asiaContinent);
                    itemadapter.add(asiaContinent1);


                    adapter = new MyExpandableAdapter(getActivity(), itemadapter);

                    expandableListView.setAdapter(adapter);
*/
                }


                    System.out.println("sdpop "+neworders.size());

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
