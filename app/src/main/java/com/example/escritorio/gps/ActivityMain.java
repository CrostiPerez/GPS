package com.example.escritorio.gps;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
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
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback, GoogleMap.OnMarkerDragListener,GoogleMap.OnMapClickListener {

    SupportMapFragment map;
    private static final int MI_PERMISO_ACCESS_FINE_LOCATION = 101;  //Constante para solicitar permiso de localización precisa
    public GoogleMap mMap;                                           //Objeto GoogleMap
    private GoogleApiClient client;                                  //Objeto del cliente de las APIS de Google
    LatLng queretaro;                                                //Objeto latitud y longitud para la ubicación general de Querétaro
    Marker marker;                                                   //Objeto para un marcador
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FloatingActionButton fab1, fab2, fab3;
    Animation FabOpen, FabClose, FabGirar, FabAntiGirar;
    boolean isOpen = false;
    boolean cluster = false;
    private ClusterManager<MyItem> mClusterManager;
    PolylineOptions polylineIda, polylineVuelta;
    TextView nombreRuta;
    String NombredeRuta;
    FloatingActionButton mSearchView;
    View mDecorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        map = SupportMapFragment.newInstance();

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Línea de código para mantener la aplicación en forma vertical
        setContentView(R.layout.activity_main);                            //Llamada a la layout principal de la aplicación
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        fab1 = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabGirar = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        FabAntiGirar = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anti_rotate);
        nombreRuta = (TextView)findViewById(R.id.nombre);
        fab1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                if (isOpen) {
                    fab2.startAnimation(FabClose);
                    fab3.startAnimation(FabClose);
                    fab1.startAnimation(FabAntiGirar);
                    fab2.setClickable(false);
                    fab3.setClickable(false);
                    isOpen = false;
                    if (!Comunicator.polylinesAreNull()) {                               //Cuando el mapa no esté vacío, hacer..
                        mMap.clear();                                                    //Limpia el mapa


                        polylineIda = Comunicator.getPolylineIda();                      //Crea un objeto polyline que llama a las coordenadas guardadas en el comunicador

                        mMap.addPolyline(setStyle(                                      //Agrega la polyline ya con todas sus características en el mapa
                                polylineIda,
                                getResources().getColor(R.color.polylineIdaGruesa),
                                Comunicator.GRUESOi));
                        mMap.addPolyline(setStyle(                                      //Se repite otra polyline menos gruesa para aplicar un estilo a la polyline
                                polylineIda,
                                getResources().getColor(R.color.polylineIdaDelgada),
                                Comunicator.DELGADOi));

                        polylineVuelta = Comunicator.getPolylineVuelta();  //Polyline para la ruta de regreso
                        mMap.addPolyline(setStyle(
                                polylineVuelta,
                                getResources().getColor(R.color.polylineVueltaGruesa),
                                Comunicator.GRUESO));
                        mMap.addPolyline(setStyle(
                                polylineVuelta,
                                getResources().getColor(R.color.polylineVueltaDelgada),
                                Comunicator.DELGADO));

                        //Ajusta el mapa al tamaño de la polyline
                        if(polylineIda.getPoints().size() > polylineVuelta.getPoints().size()) {
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsVuelta().getCenter(), 12));
                        } else {
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsIda().getCenter(), 12));
                        }}
                } else {
                    fab2.startAnimation(FabOpen);
                    fab3.startAnimation(FabOpen);
                    fab1.startAnimation(FabGirar);
                    fab2.setClickable(true);
                    fab3.setClickable(true);
                    isOpen = true;
                }

            }});
        fab2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v) {
                    if (!Comunicator.polylinesAreNull()) {
                        mMap.clear();
                        polylineIda = Comunicator.getPolylineIda();                      //Crea un objeto polyline que llama a las coordenadas guardadas en el comunicador

                        mMap.addPolyline(setStyle(                                      //Agrega la polyline ya con todas sus características en el mapa
                                polylineIda,
                                getResources().getColor(R.color.polylineIdaGruesa),
                                Comunicator.GRUESOi));
                        mMap.addPolyline(setStyle(                                      //Se repite otra polyline menos gruesa para aplicar un estilo a la polyline
                                polylineIda,
                                getResources().getColor(R.color.polylineIdaDelgada),
                                Comunicator.DELGADOi));

                        //Ajusta el mapa al tamaño de la polyline
                        if(polylineIda.getPoints().size() > polylineVuelta.getPoints().size()) {
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsVuelta().getCenter(), 12));
                        } else {
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsIda().getCenter(), 12));
                        }
                    }
                }});
        fab3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                if (!Comunicator.polylinesAreNull()) {
                    mMap.clear();
                polylineVuelta = Comunicator.getPolylineVuelta();  //Polyline para la ruta de regreso
                mMap.addPolyline(setStyle(
                        polylineVuelta,
                        getResources().getColor(R.color.polylineVueltaGruesa),
                        Comunicator.GRUESO));
                mMap.addPolyline(setStyle(
                        polylineVuelta,
                        getResources().getColor(R.color.polylineVueltaDelgada),
                        Comunicator.DELGADO));
                    //Ajusta el mapa al tamaño de la polyline
                    if(polylineIda.getPoints().size() > polylineVuelta.getPoints().size()) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsVuelta().getCenter(), 12));
                    } else {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsIda().getCenter(), 12));
                    }
                }
            }});
        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= 19 ){
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);}

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager()     //Se agrega un soporte de fragmentos en la activity
            .findFragmentById(R.id.maps);                                         //llamada al layout del mapa para agregarlo en la aplicación
        map.getMapAsync(this);                                                        //Guarda cambios en el mapa

    client =new GoogleApiClient.Builder(this).

    addApi(AppIndex.API).

    build();      //Se usa el objeto client para mandar llamar la API KEY
    // displayView(0);

        if(Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21){
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height = 0;
        }
       /* mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
                mSearchView.swapSuggestions(newSuggestions);
            }
        });*/
}

    @Override
    public void onWindowFocusChanged(boolean hasfocus){
        super.onWindowFocusChanged(hasfocus);
        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= 19 ){
        if(hasfocus){
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);}

    }
    else  if (hasfocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
     //   getMenuInflater().inflate(R.menu.main, menu);
     //   MenuInflater menuInflater = getMenuInflater();
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
        int id = item.getItemId();                      //Menú para la barra desplazable
        if (id == R.id.rutas) {
            Intent i = new Intent(this, ActivityRuta.class);     //Al dar click en "Rutas", se crea un intent que contiene la activity con todas las rutas

            onStop();                                   //Se para la activity principal para desocupar espacio en memoria para lanzar la activity "Ruta"

            startActivity(i);                           //Inicializa la activity "Ruta"

        } else if (id == R.id.taxis) {                  //Para programar los demás botones ...


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {                //...
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        queretaro = new LatLng(20.59155, -100.400589);                      //Se le asignan al objeto queretaro, coordenadas
        mMap.setPadding(0,150,0,85);
        googleMap.setOnMarkerDragListener(this);                            //Para agregar un evento al arrastrar un marcador
        mMap.setOnMapClickListener(this);                                   //Para agreagar un evento al dar click en un mapa

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(       //Creación de un objeto del estilo del mapa
                this, R.raw.day);                                           //Se puede modificar el estilo en la carpeta raw
        mMap.setMapStyle(style);                                            //Se aplica el estilo al mapa

        //Añade polylines al mapa
        if (!Comunicator.polylinesAreNull()) {                               //Cuando el mapa no esté vacío, hacer..
            mMap.clear();                                                    //Limpia el mapa

            polylineIda = Comunicator.getPolylineIda();                      //Crea un objeto polyline que llama a las coordenadas guardadas en el comunicador
            NombredeRuta = RutaIda.getNombre();
            nombreRuta.setText(NombredeRuta);
            nombreRuta.setVisibility(View.VISIBLE);

            mMap.addPolyline(setStyle(                                      //Agrega la polyline ya con todas sus características en el mapa
                    polylineIda,
                    getResources().getColor(R.color.polylineIdaGruesa),
                    Comunicator.GRUESOi));
            mMap.addPolyline(setStyle(                                      //Se repite otra polyline menos gruesa para aplicar un estilo a la polyline
                    polylineIda,
                    getResources().getColor(R.color.polylineIdaDelgada),
                    Comunicator.DELGADOi));

            polylineVuelta = Comunicator.getPolylineVuelta();  //Polyline para la ruta de regreso
            mMap.addPolyline(setStyle(
                    polylineVuelta,
                    getResources().getColor(R.color.polylineVueltaGruesa),
                    Comunicator.GRUESO));
            mMap.addPolyline(setStyle(
                    polylineVuelta,
                    getResources().getColor(R.color.polylineVueltaDelgada),
                    Comunicator.DELGADO));

            //Ajusta el mapa al tamaño de la polyline
            if(polylineIda.getPoints().size() > polylineVuelta.getPoints().size()) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsVuelta().getCenter(), 12));
            } else {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Comunicator.getBoundsIda().getCenter(), 12));
            }

            // ArrayList<MarkerOptions> markerOptions = Comunicator.getParada();                //Crea un objeto de un arreglo de lista que llama a las posiciones guardadas en el comunicador para crear marcadores(paradas)
            setUpClusterer(Comunicator.getParada());
            /*for (MarkerOptions koko: Comunicator.getParada()) {                              //for para recorrer el arreglo de lista que va dando posiciones (koko) para agregarlas a un marcador(parada) diferente
                mMap.addMarker(koko.title("Parada")                                          //Agrega un marcador con su posición (koko) y el título "Parada"
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.circl))        //.icon es para agregar una imagen personalizada a los marcadores y se deben poner en la carpeta drawable
                        .flat(true));                                                        //.flat es para aplanar el marcador, es importante porque queremos que sean paradas, no ubicaciones
            }*/

        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(queretaro, 11f));           //Si el mapa está vacío, se coloca el mapa sobre las coordenadas de Querétaro con un zoom
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);                                         //Se establece un tipo de mapa, ejemplos, satelital, normal, híbrido, etc
        mMap.getUiSettings().setZoomControlsEnabled(true);                                  //Se activan los controles de zoom sobre el mapa (+ y -)
        mMap.getUiSettings().setMapToolbarEnabled(false);                                   //Se desactivan las herramientas de GoogleMaps en el mapa


        //Comparar versiones para hacer compatible en versiones inferiores a la 6 en el aspecto del permiso de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MI_PERMISO_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MI_PERMISO_ACCESS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    new SweetAlertDialog(ActivityMain.this, SweetAlertDialog.WARNING_TYPE)
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
                                    ActivityCompat.requestPermissions(ActivityMain.this,
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
        if (!Comunicator.polylinesAreNull()) {
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
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(this.marker)) {
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(this.marker)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        marker = mMap.addMarker(new MarkerOptions().position(queretaro)
                .title("Hola")
                .snippet("Has apuntado, aquí")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));


    }

    // Declare a variable for the cluster manager.


    private void setUpClusterer(ArrayList<MarkerOptions> Paradas) {
        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems(Paradas);
    }

    //private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());

    private void addItems(ArrayList<MarkerOptions> Paradas) {

        mClusterManager.setRenderer(new ClusterRenderer_Blue(this, mMap,
                mClusterManager, false));

        for (MarkerOptions element : Paradas) {  //Pasa las ubicaciones de los elementos del array paradas al objeto mClusterManager

            LatLng ubicacion = element.getPosition();
            MyItem offsetItem = new MyItem(ubicacion.latitude, ubicacion.longitude);
           mClusterManager.addItem(offsetItem);
        }
    }

    //Devuelve la polyline con el estilo aplicado
    private PolylineOptions setStyle(PolylineOptions polylineOptions, int color, int width) {
        polylineOptions.color(color);                                      //Le agrega color a la polyline
        polylineOptions.width(width);                                      //Le pone un grosor a la polyline
        return polylineOptions;
    }

    private void displayView(int position) {
        fragment = null;
        String fragmentTags = "";
        switch (position) {
            case 0:
                fragment = new SearchFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, fragmentTags).commit();
        }
}

}

