package com.example.escritorio.gps;

public class Comunicator {
    private static StrokedPolylineOptions Polyline = null;
    public static void setPolyline (StrokedPolylineOptions aPolyline)
    {

        Polyline= aPolyline;
    }
    public static StrokedPolylineOptions getPolyline (){
        return Polyline;
    }
}
