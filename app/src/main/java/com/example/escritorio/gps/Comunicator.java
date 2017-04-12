package com.example.escritorio.gps;

import com.google.android.gms.maps.model.PolylineOptions;

public class Comunicator {
    private static PolylineOptions Polyline = null;
    private static PolylineOptions Polyline2 = null;

    public static PolylineOptions getPolyline2() {
        return Polyline2;
    }

    public static void setPolyline2(PolylineOptions aPolyline2) {
        Polyline2 = aPolyline2;
    }



    public static void setPolyline (PolylineOptions aPolyline)
    {

        Polyline= aPolyline;

    }
    public static PolylineOptions getPolyline (){
        return Polyline;
    }

}
