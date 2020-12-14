package com.enes.notlar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class detaylariGoster extends AppCompatActivity {
    private Toolbar toolbardetay;
    private Button btntakvim2;
    private EditText yaziedit2;
    private EditText  edittexttarih2;
    private EditText editTextbaslik2;
    private EditText editTextsaat2;
    private veri vt;
    private Animation ileri,geri,kapali,acik;
    private Boolean fabdurum = false;
    private defterler defter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaylari_goster);


        toolbardetay = findViewById(R.id.toolbardetay);



        yaziedit2 = findViewById(R.id.editextyazi2);
        edittexttarih2 = findViewById(R.id.edittexttarih2);
        editTextbaslik2 = findViewById(R.id.edittextbaslik2);
        editTextsaat2 = findViewById(R.id.edittextsaat2);


        btntakvim2 = findViewById(R.id.takvim2);

        toolbardetay.setTitle("Not ekle");
        setSupportActionBar(toolbardetay);
        takvim();


        vt = new veri(detaylariGoster.this);

        defter = (defterler) getIntent().getSerializableExtra("nesne") ;


        edittexttarih2.setText(defter.getTarih());
        yaziedit2.setText(defter.getDefter_ad());
        editTextbaslik2.setText(defter.getBaslik());


        Calendar mcurrentTime = Calendar.getInstance();//
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
        int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
        TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk
        editTextsaat2.setText(hour+":"+minute);




    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                new AlertDialog.Builder(this).setTitle("UYARI").setMessage("Silmek İstediğinize Emin misiniz")
                        .setPositiveButton("evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new defterdao().sil(vt,defter.getDefter_id());
                                startActivity(new Intent(detaylariGoster.this,MainActivity.class));
                                finish();
                            }
                        }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(detaylariGoster.this,MainActivity.class));
                        finish();
                        dialog.cancel();
                    }
                }).show();
                return true;

            case R.id.action_duzenle:
                String defter_ad = yaziedit2.getText().toString().trim();
                String tarih = edittexttarih2.getText().toString().trim();
                String baslik = editTextbaslik2.getText().toString().trim();
                String saat = editTextsaat2.getText().toString().trim();




                new defterdao().duzenle(vt,defter.getDefter_id(),defter_ad,tarih,baslik,saat);
                Snackbar.make(toolbardetay,"Güncelleme İşlemi Tamamlandı.",Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(detaylariGoster.this,MainActivity.class));
                return true;



            default:
                return false;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detaylarigostermenu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void takvim(){

        btntakvim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();

                int yil = calender.get(Calendar.YEAR);
                int ay = calender.get(Calendar.MONTH);
                int gun = calender.get(calender.DAY_OF_MONTH);

                DatePickerDialog datePicker;

                datePicker = new DatePickerDialog(detaylariGoster.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        edittexttarih2.setText(i2+"/"+i1+"/"+i);


                    }
                },yil,ay,gun);


                datePicker.setTitle("tarih seçiniz");

                datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"ayarla",datePicker);
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"iptal",datePicker);

                datePicker.show();


            }
        });


    }




}