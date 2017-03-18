package com.example.escritorio.gps;

import com.google.android.gms.maps.model.PolylineOptions;



public class Comunicator {
    private static PolylineOptions polyline = null;
    public static void setPolyline (PolylineOptions aPolyline)
    {

        polyline = aPolyline;
    }
    public static PolylineOptions getPolyline (){
        return polyline;
    }
}
