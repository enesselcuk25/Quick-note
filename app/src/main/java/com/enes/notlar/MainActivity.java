package com.enes.notlar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private FloatingActionButton flot;
    private Toolbar toolbarmain;
    private RecyclerView rc;
    private veri vt;
    private adaptır adaptır;
    private Animation animation;

    private ArrayList<defterler> defterlerArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        flot = findViewById(R.id.floatingActionButton);
        toolbarmain = findViewById(R.id.toolbarmain);
        rc = findViewById(R.id.rc);

        toolbarmain.setTitle("Not ekle");
        setSupportActionBar(toolbarmain);
        vt = new veri(MainActivity.this);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.floattingbutton);
        flot.startAnimation(animation);

        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));

        defterlerArrayList = new defterdao().tumdefterler(vt);

        adaptır = new adaptır(MainActivity.this,defterlerArrayList,vt);
        rc.setAdapter(adaptır);



        flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, detaysayfasii.class));

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.arama,menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        defterlerArrayList = new defterdao().kisiara(vt,newText);

        adaptır = new adaptır(this,defterlerArrayList,vt);
        rc.setAdapter(adaptır);

        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}