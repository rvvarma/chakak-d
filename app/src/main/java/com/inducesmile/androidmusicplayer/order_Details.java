package com.inducesmile.androidmusicplayer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inducesmile.androidmusicplayer.adapter.order_details_adapter;

import com.inducesmile.androidmusicplayer.entities.order_details_object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class order_Details extends AppCompatActivity {


    List<order_details_object> neworders ;
    order_details_adapter mAdapter;
    RecyclerView ordercycleview;
    JSONArray ordersiterate;
    TextView status,phone,itemcount,payment,price,username,address,time;
Button phonecall,maps;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__details);

        ordercycleview = (RecyclerView)findViewById(R.id.song_list);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(order_Details.this);
        ordercycleview.setLayoutManager(linearLayoutManager);
        ordercycleview.setHasFixedSize(true);
        Intent ip=getIntent();
        status=(TextView) findViewById(R.id.status);
        phone=(TextView) findViewById(R.id.phone);
        itemcount=(TextView) findViewById(R.id.itemcount);
        payment=(TextView) findViewById(R.id.payment);
        price=(TextView) findViewById(R.id.totalprice);
        username=(TextView) findViewById(R.id.name);
        address=(TextView) findViewById(R.id.address);
        time=(TextView) findViewById(R.id.time);

        phonecall=(Button)findViewById(R.id.phonecall);
        maps=(Button) findViewById(R.id.maps);


        phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=37.423156,-122.084917 ( Raghava )"));
                startActivity(intent);
            }
        });






        // ordercycleview.setLayoutManager(new RelativeLayout(order_Details.this));
        AsyncTaskRunner ast=new AsyncTaskRunner();
        ast.execute(ip.getStringExtra("id"));
        neworders = new ArrayList<order_details_object>();
        submit=(Button) findViewById(R.id.picked);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("sdv" + order_details_adapter.count());

                if (order_details_adapter.count() == ordersiterate.length()) {
                    System.out.println("sdv" + order_details_adapter.count());



                    final String[] fonts = {"Picked", "Delivered", "Other"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(order_Details.this);
                    builder.setTitle("Select The Status");
                    builder.setItems(fonts, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if ("Picked".equals(fonts[which])){
                                Toast.makeText(order_Details.this,"you Choosed Picked", Toast.LENGTH_SHORT).show();
                            }
                            else if ("Delivered".equals(fonts[which])){
                                Toast.makeText(order_Details.this,"you Choosed Delivered", Toast.LENGTH_SHORT).show();
                            }
                            else if ("Other".equals(fonts[which])){
                                Toast.makeText(order_Details.this,"you Choosed others", Toast.LENGTH_SHORT).show();
                            }

                            // the user clicked on colors[which]

                        }
                    });
                    builder.show();


                } else {
                    AlertDialog dialog;
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(order_Details.this);
                    alertDialog.setTitle("Slug");
                    alertDialog.setMessage("Please Check All Items To Continue " + ordersiterate.length());
                    alertDialog.setCancelable(false);


                    alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    dialog = alertDialog.create();
                    dialog.show();
                }
            }
        });
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
                URL myUrl = new URL("https://3q4jnoy6zf.execute-api.ap-south-1.amazonaws.com/prod/orderdetails-delivery-boys?operation=Gety&&orderid="+params[0]);
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
                JSONObject order;
              //  System.out.println("sd "+);

                order=jb.getJSONObject("data");
                JSONObject userdata=order.getJSONObject("user");
                ordersiterate=order.getJSONArray("items");
                System.out.println("check "+ordersiterate);

                if(jb.getString("Message").equals("Success")) {


                    for(int i=0;i<ordersiterate.length();i++) {


                        JSONObject jb1=ordersiterate.getJSONObject(i);
                        System.out.println("sd "+jb1.getString("itemname"));
                        //    Timestamp timestamp1 = new Timestamp(new Date(jb1.getString("time")));

                        neworders.add(new order_details_object(jb1.getString("itemname"), jb1.getString("qty")));

                    }
                    System.out.println("pop pp"+neworders.size());
//                    status.setText("Status : "+ userdata.getString("status"));
                    username.setText("Name : "+userdata.getString("username"));
                    itemcount.setText("Item Count : "+ordersiterate.length());
                    payment.setText("Payment : "+userdata.getString("payment"));
                    price.setText("Total Price : "+userdata.getString("price"));
                    phone.setText("919000000034");
                    address.setText(userdata.getString("address"));
                    Date date = new java.util.Date(Integer.parseInt(userdata.getString("time"))*1000L);

                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy hh:mm:ss ");
// give a timezone reference for formatting (see comment at the bottom)
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+5:30"));
                    String formattedDate = sdf.format(date);
                    time.setText(formattedDate);
                    mAdapter = new order_details_adapter(order_Details.this, neworders);
                    mAdapter.notifyDataSetChanged();

                    ordercycleview.setAdapter(mAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(order_Details.this,"Slug", "Please Wait");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //  finalResult.setText(text[0]);

        }
    }






}
