package com.example.escritorio.gps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.view.ClusterRenderer;

/**
 * Created by Sergio :3 on 29/04/2017.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    //String titulo;

    public MyItem(double lat, double lng/*, String titulo*/) {
        mPosition = new LatLng(lat, lng);
        //this.titulo = titulo;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
