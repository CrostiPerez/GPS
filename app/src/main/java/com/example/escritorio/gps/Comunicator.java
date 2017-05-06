package com.example.escritorio.gps;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class Comunicator {

    private static PolylineOptions polylineIda = null;
    private static PolylineOptions polylineVuelta = null;
    private static ArrayList<MarkerOptions> parada = null;
    private static String nombre = null;
    public static final int GRUESOi = 14;
    public static final int DELGADOi = 8;

    public static final int GRUESO = 10;
    public static final int DELGADO = 4;

    public static void setParada(ArrayList<MarkerOptions> koko) {
        parada = koko;
    }
    public static ArrayList<MarkerOptions> getParada() {
        return parada;
    }

    public static void setNombre(String string){nombre = string;}
    public  static String getNombre() {return nombre;}

    public static PolylineOptions getPolylineIda (){
        return polylineIda;
    }
    public static PolylineOptions getPolylineVuelta () {
        return polylineVuelta;
    }

    public static void setPolylineIda (PolylineOptions aPolyline) {
        polylineIda = aPolyline;
    }
    public static void setPolylineVuelta(PolylineOptions aPolyline) {
        polylineVuelta = aPolyline;
    }


    //Devuelve si se seleccionó alguna ruta.
    public static boolean polylinesAreNull () {
        if(polylineVuelta == null || polylineIda == null) {
            return true;
        } else {
            return false;
        }
    }

    //Devuelve los limites de cada ruta en un objeto latlngBounds, ida y regreso.
    public static LatLngBounds getBoundsIda () {
        return new LatLngBounds(
                polylineIda.getPoints().get(0),
                polylineIda.getPoints().get(polylineIda.getPoints().size() - 1));
    }

    public static LatLngBounds getBoundsVuelta () {
        return new LatLngBounds(
                polylineVuelta.getPoints().get(0),
                polylineVuelta.getPoints().get(polylineVuelta.getPoints().size() - 1));
    }

}
