package com.inducesmile.androidmusicplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    static public final int REQUEST_LOCATION = 1;

    private static final String TAG = SplashActivity.class.getSimpleName();

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            //startBeermay(); // <-- Start Beemray here
            ActionBar actionBar = getSupportActionBar();
            if(null != actionBar){
                actionBar.hide();
            }

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    Intent startActivityIntent = new Intent(SplashActivity.this, MusicActivity.class);
                    startActivity(startActivityIntent);
                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
 String[] permissions,
 int[] grantResults) {
 if (requestCode == REQUEST_LOCATION) {
 if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // startBeermay() // <-- Start Beemray here
     ActionBar actionBar = getSupportActionBar();
     if(null != actionBar){
         actionBar.hide();
     }

     new Handler().postDelayed(new Runnable(){
         @Override
         public void run(){
             Intent startActivityIntent = new Intent(SplashActivity.this, MusicActivity.class);
             startActivity(startActivityIntent);
             SplashActivity.this.finish();
         }
     }, SPLASH_DISPLAY_LENGTH);
 } else {
// Permission was denied or request was cancelled
}
 }
    }
}
