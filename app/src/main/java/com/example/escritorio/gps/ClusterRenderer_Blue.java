package com.example.escritorio.gps;

import android.content.Context;
import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by Moises on 04-May-17.
 */

/*public class ClusterRenderer_Blue extends DefaultClusterRenderer<MyItem> {


    private final IconGenerator mClusterIconGenerator;
    Resources resources;
    Context context;

    public ClusterRenderer_Blue(Context context, GoogleMap map,
                             ClusterManager<MyItem> clusterManager, Resources resources) {
        super(context, map, clusterManager);
        this.resources = resources;
        this.context = context;
        mClusterIconGenerator = new IconGenerator(context);

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item,
                                               MarkerOptions markerOptions) {

        BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.camion)).title("Parada");
    }

    @Override
    protected void onClusterItemRendered(MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions){

        mClusterIconGenerator.setColor(resources.getColor(android.R.color.holo_orange_light));

        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(40, 20, 0, 0);
        }
        else {
            mClusterIconGenerator.setContentPadding(30, 20, 0, 0);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        mClusterIconGenerator.setTextAppearance(context, 0x200);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

}*/

public class ClusterRenderer_Blue extends DefaultClusterRenderer<MyItem> {

    boolean blue;

    public ClusterRenderer_Blue(Context context, GoogleMap map,
                                ClusterManager<MyItem> clusterManager, boolean blue) {
        super(context, map, clusterManager);
        this.blue = blue;

    }

    @Override
    public void setMinClusterSize(int minClusterSize) {
        super.setMinClusterSize(minClusterSize);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item,
                                               MarkerOptions markerOptions) {

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.camion)).title("Parada").snippet("prueba");
    }

    @Override
    protected int getColor(int clusterSize) {

        if(!blue){

            return Color.parseColor("#2E9AFE");
        } else{

            return Color.parseColor("#DF0101");

        }
    }
}
