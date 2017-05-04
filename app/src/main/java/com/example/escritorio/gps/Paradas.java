package com.example.escritorio.gps;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;



public class Paradas {


    public static ArrayList<MarkerOptions> getPosition (int route) {
        ArrayList<MarkerOptions> parada = new ArrayList<>();

        switch (route) {
            case R.id.Ruta1:
                parada.add(new MarkerOptions().position(new LatLng(20.553050000,-100.394490000)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.550670000,-100.39325000)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.550744000,-100.39523000)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.550800000,-100.39423000)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.550520000,-100.39512000)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.550660000,-100.39560000)).title("Parada"));



                break;
            case R.id.Ruta2:
                break;
            case R.id.Ruta3:
                break;
            case R.id.Ruta4:
                break;
            case R.id.Ruta5:
                break;
            case R.id.Ruta6:
                break;
            case R.id.Ruta7:
                break;
            case R.id.Ruta8:
                break;
            case R.id.Ruta9:
                break;
            default:break;

        }
        return parada;
        }
}
