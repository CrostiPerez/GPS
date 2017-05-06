package com.example.escritorio.gps;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;



public class Paradas {


    public static ArrayList<MarkerOptions> getPosition (int route) {
        ArrayList<MarkerOptions> parada = new ArrayList<>();

        switch (route) {
            case R.id.Ruta92:
                parada.add(new MarkerOptions().position(new LatLng(20.545155, -100.390222)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.547688, -100.391589)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.548570, -100.392074)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.551317, -100.393515)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.553909, -100.394835)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.555298, -100.395625)).title("Parada"));
                parada.add(new MarkerOptions().position(new LatLng(20.558651, -100.397325)));
                parada.add(new MarkerOptions().position(new LatLng(	20.558651, -100.397325	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.561541, -100.398801	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.564146, -100.400145	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.572984, -100.405093	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.581465, -100.406420	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.584043, -100.404262	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.586457, -100.402888	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.590778, -100.404906	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.592665, -100.405720	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.595724, -100.404676	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.596251, -100.400351	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.597888, -100.394726	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.600250, -100.393732	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.602857, -100.392661	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.605129, -100.389667	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.606087, -100.388097	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.608823, -100.386051	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.615195, -100.389820	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.618003, -100.389932	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.621640, -100.390798	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.623649, -100.395335	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.626993, -100.399372	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.629165, -100.399026	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.631879, -100.399905	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.632044, -100.401594	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.634048, -100.401193	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.634387, -100.401131	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.636234, -100.400818	)));
                parada.add(new MarkerOptions().position(new LatLng(	20.638544, -100.400584	)));




                break;
            case R.id.Ruta137:
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
