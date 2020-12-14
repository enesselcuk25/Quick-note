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
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class detaysayfasii extends AppCompatActivity {
    private Toolbar toolbar2;

    private Button btntakvim;
    private Button btnfotocek,btnfotoarsiv,btnfotoac;
    private EditText yaziedit;
    private EditText  edittexttarih;
    private EditText editTextbaslik;
    private EditText editTextsaat;
    private veri vt;
    private Animation ileri,geri,kapali,acik;
    private Boolean fabdurum = false;
    private defterler defter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaysayfasii);

        toolbar2 = findViewById(R.id.toolbar2);




        yaziedit = findViewById(R.id.editextyazi);
        edittexttarih = findViewById(R.id.edittexttarih);
        editTextbaslik = findViewById(R.id.edittextbaslik);
        editTextsaat = findViewById(R.id.edittextsaat);


        btntakvim = findViewById(R.id.takvim);
        btnfotoac = (Button) findViewById(R.id.buttontıkla);
        btnfotocek = findViewById(R.id.btnfotocek);
        btnfotoarsiv = findViewById(R.id.btnarsiv);


        vt = new veri(detaysayfasii.this);


        Calendar mcurrentTime = Calendar.getInstance();//
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
        int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
        TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk
        editTextsaat.setText(hour+":"+minute);



        toolbar2.setTitle("Not ekle");
        setSupportActionBar(toolbar2);

        takvim();
        animasyon();



    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_kaydet:
                                String defter_ad = yaziedit.getText().toString().trim();
                                String tarih = edittexttarih.getText().toString().trim();
                                String baslik = editTextbaslik.getText().toString().trim();
                                String saat = editTextsaat.getText().toString().trim();

                                   new defterdao().ekle(vt,defter_ad,tarih,baslik,saat);
                Toast.makeText(detaysayfasii.this,"Kayıt Edildi",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(detaysayfasii.this,MainActivity.class));

                               finish();
                return true;



            default:
                return false;
        }


    }

    public void takvim(){

        btntakvim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();

                int yil = calender.get(Calendar.YEAR);
                int ay = calender.get(Calendar.MONTH);
                int gun = calender.get(calender.DAY_OF_MONTH);

                edittexttarih.setText(gun+"/"+ay+"/"+yil);


                DatePickerDialog datePicker;

                datePicker = new DatePickerDialog(detaysayfasii.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        edittexttarih.setText(i2+"/"+i1+"/"+i);


                    }
                },yil,ay,gun);


                datePicker.setTitle("tarih seçiniz");

                datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"ayarla",datePicker);
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"iptal",datePicker);

                datePicker.show();




            }
        });


    }

    public void animasyon(){

        ileri = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ileridon);
        geri = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.geridon);
        acik = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabacik);
        kapali = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fabkapali);


        btnfotoac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fabdurum) {
                    btnfotoac.startAnimation(geri);
                    btnfotocek.startAnimation(kapali);
                    btnfotoarsiv.startAnimation(kapali);

                    btnfotocek.setClickable(false);
                    btnfotoarsiv.setClickable(false);
                    fabdurum = false;

                }
                else{
                    btnfotoac.startAnimation(ileri);
                    btnfotocek.startAnimation(acik);
                    btnfotoarsiv.startAnimation(acik);

                    btnfotocek.setClickable(true);
                    btnfotoarsiv.setClickable(true);
                    fabdurum = true;
                }


            }
        });

    }


}