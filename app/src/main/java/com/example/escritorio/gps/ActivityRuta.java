package com.example.escritorio.gps;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class ActivityRuta extends AppCompatActivity {
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void DrawRoute(View v){
        Comunicator.setParada(Paradas.getPosition(v.getId()));
        PolylineOptions polylineIda = new PolylineOptions();
        PolylineOptions polylineVuelta = new PolylineOptions();

        for (LatLng latLng: RutaIda.getLines(v.getId())) {
            polylineIda.add(latLng);}
        Comunicator.setPolylineIda(polylineIda);

        for (LatLng latLng: RutaVuelta.getLines(v.getId())) {
            polylineVuelta.add(latLng);}
        Comunicator.setPolylineVuelta(polylineVuelta);
        finish();

        String nombre = new String();
        RutaIda.getNombre();
        Comunicator.setNombre(nombre);
    }

}
