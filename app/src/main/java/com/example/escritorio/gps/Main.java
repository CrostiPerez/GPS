package com.example.escritorio.gps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMarkerDragListener,GoogleMap.OnMapClickListener {
    SupportMapFragment map;
    private static final int MI_PERMISO_ACCESS_FINE_LOCATION = 101;
    public GoogleMap mMap;
    private GoogleApiClient client;
    LatLng queretaro;
    Marker marker;
    Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        map = SupportMapFragment.newInstance();


        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        map.getMapAsync(this);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                return;
            }
            else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.rutas) {

            Intent i = new Intent(this,Ruta.class);

          onStop();
            startActivity(i);

        } else if (id == R.id.taxis) {


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        queretaro = new LatLng(20.59155, -100.400589);

        googleMap.setOnMarkerDragListener(this);
        mMap.setOnMapClickListener(this);

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                this, R.raw.day);
        mMap.setMapStyle(style);

        if (Comunicator.getPolyline() != null){
            mMap.clear();
            PolylineOptions polyline = Comunicator.getPolyline();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(polyline.getPoints().get(polyline.getPoints().size()/2), 12.5f));
            mMap.addPolyline(polyline);
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(queretaro));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(queretaro, 11.5f));
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MI_PERMISO_ACCESS_FINE_LOCATION );
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MI_PERMISO_ACCESS_FINE_LOCATION :
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    new SweetAlertDialog(Main.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Atención!")
                            .setContentText("Debes otorgar permisos para mejorar tu experiencia en Move Your App")
                            .setConfirmText("Solicitar Permiso")
                            .setCancelText("Cancelar")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                    ActivityCompat.requestPermissions(Main.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            MI_PERMISO_ACCESS_FINE_LOCATION);
                                }
                            })
                            .show();
                }
                break;
        }
    }





    @Override
    protected void onStart() {
        super.onStart();
        client.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Comunicator.getPolyline() != null) {


            SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.maps);
            map.getMapAsync(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        client.disconnect();
    }




    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(this.marker)) {
            Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        }}

    @Override
    public void onMarkerDrag(Marker marker){
        if (marker.equals(this.marker)) {
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }
    @Override
    public void onMarkerDragEnd(Marker marker){
        if (marker.equals(this.marker)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        marker = mMap.addMarker(new MarkerOptions()
                .position(queretaro)
                .title("Hola")
                .snippet("Has apuntado, aquí")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));


    }





}
