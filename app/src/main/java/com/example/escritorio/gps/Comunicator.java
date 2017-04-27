package com.example.escritorio.gps;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class Comunicator {

    private static PolylineOptions Polyline = null;
    private static PolylineOptions Polyline2 = null;
    private static ArrayList<MarkerOptions> parada = null;

    public static ArrayList<MarkerOptions> getParada() {
        return parada;
    }

    public static void setParada(ArrayList<MarkerOptions> koko) {
        parada = koko;
    }

    public static PolylineOptions getPolyline2() {
        return Polyline2;
    }

    public static void setPolyline2(PolylineOptions aPolyline2) {
        Polyline2 = aPolyline2;
    }


    public static void setPolyline (PolylineOptions aPolyline) {
        Polyline= aPolyline;
    }

    public static PolylineOptions getPolyline (){
        return Polyline;
    }


}
