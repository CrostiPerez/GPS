package com.example.escritorio.gps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class Ruta extends AppCompatActivity {



    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void Ruta1(View v){
        Intent intent = new Intent(this, Main.class);

        PolylineOptions line = new PolylineOptions()
                .add(new LatLng(20.54401, -100.38983))
                .add(new LatLng(20.56530, -100.40080))
                .add(new LatLng(20.56556, -100.40077))
                .add(new LatLng(20.56640, -100.40019))
                .add(new LatLng(20.56732, -100.40045))
                .add(new LatLng(20.56889, -100.40152))
                .add(new LatLng(20.56903, -100.40227))
                .add(new LatLng(20.57007, -100.40350))
                .add(new LatLng(20.57316, -100.40516))
                .add(new LatLng(20.57769, -100.40147))
                .add(new LatLng(20.57738, -100.40077))
                .add(new LatLng(20.57687, -100.40112))
                .add(new LatLng(20.57681, -100.40183))
                .add(new LatLng(20.58168, -100.40680))
                .add(new LatLng(20.58356, -100.40531))
                .add(new LatLng(20.58401, -100.40481))
                .add(new LatLng(20.58396, -100.40339))
                .add(new LatLng(20.58451, -100.40213))
                .add(new LatLng(20.59530, -100.40693))
                .color(Color.parseColor("#f44336"));



        Comunicator.setPolyline(line);
        startActivity(intent);
        finish();

    }
    public void salir(View v) {
        finish();
    }
}
