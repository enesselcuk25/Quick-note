package com.enes.notlar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainGecis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gecis);

        Thread TimeThread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(MainGecis.this, MainActivity.class));
                }
            }

        };
        TimeThread.start();
    }
    public  void onPause(){
        super.onPause();
        finish();
    }
}