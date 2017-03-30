package com.example.escritorio.gps;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Armando on 28/03/2017.
 */

public class Routes {
    public static ArrayList<LatLng> getLines (int route) {
        ArrayList<LatLng> line = new ArrayList<>();
        switch (route) {
            case R.id.Ruta1:
                line.add(new LatLng(20.54401, -100.38983));
                line.add(new LatLng(20.56530, -100.40080));
                line.add(new LatLng(20.56556, -100.40077));
                line.add(new LatLng(20.56640, -100.40019));
                line.add(new LatLng(20.56732, -100.40045));
                line.add(new LatLng(20.56889, -100.40152));
                line.add(new LatLng(20.56903, -100.40227));
                line.add(new LatLng(20.57007, -100.40350));
                line.add(new LatLng(20.57316, -100.40516));
                line.add(new LatLng(20.57769, -100.40147));
                line.add(new LatLng(20.57738, -100.40077));
                line.add(new LatLng(20.57687, -100.40112));
                line.add(new LatLng(20.57681, -100.40183));
                line.add(new LatLng(20.58168, -100.40680));
                line.add(new LatLng(20.58356, -100.40531));
                line.add(new LatLng(20.58401, -100.40481));
                line.add(new LatLng(20.58396, -100.40339));
                line.add(new LatLng(20.58451, -100.40213));
                line.add(new LatLng(20.59530, -100.40693));
                break;
            case R.id.Ruta2:
                line.add(new LatLng(20.554435, -100.450643));
                line.add(new LatLng(20.552566, -100.450037));
                line.add(new LatLng(20.552365, -100.448980));
                line.add(new LatLng(20.552114, -100.447762));
                line.add(new LatLng(20.551195, -100.446694));
                line.add(new LatLng(20.550035, -100.445353));
                line.add(new LatLng(20.549327, -100.440289));
                line.add(new LatLng(20.549327, -100.439956));
                line.add(new LatLng(20.542410, -100.439876));
                line.add(new LatLng(20.542711, -100.436604));
                line.add(new LatLng(20.543721, -100.434501));
                line.add(new LatLng(20.544022, -100.434158));
                line.add(new LatLng(20.545524, -100.432978));
                line.add(new LatLng(20.546584, -100.431905));
                line.add(new LatLng(20.547950, -100.430382));
                line.add(new LatLng(20.549221, -100.428853));
                line.add(new LatLng(20.548814, -100.427748));
                line.add(new LatLng(20.548902, -100.426740));
                line.add(new LatLng(20.548759, -100.426179));
                line.add(new LatLng(20.548091, -100.425154));
                line.add(new LatLng(20.547674, -100.425098));
                line.add(new LatLng(20.547438, -100.425310));
                line.add(new LatLng(20.547478, -100.425812));
                line.add(new LatLng(20.547807, -100.425973));
                line.add(new LatLng(20.548154, -100.425946));
                line.add(new LatLng(20.554493, -100.420681));
                line.add(new LatLng(20.557406, -100.418288));
                line.add(new LatLng(20.560088, -100.416067));
                line.add(new LatLng(20.562529, -100.414007));
                line.add(new LatLng(20.562860, -100.414305));
                line.add(new LatLng(20.563518, -100.414613));
                line.add(new LatLng(20.566868, -100.414811));
                line.add(new LatLng(20.567923, -100.412708));
                line.add(new LatLng(20.568636, -100.411270));
                line.add(new LatLng(20.568932, -100.411587));
                line.add(new LatLng(20.571503, -100.415117));

                break;
            case R.id.Ruta3:
                line.add(new LatLng(20.56640, -100.40019));
                line.add(new LatLng(20.56732, -100.40045));
                line.add(new LatLng(20.56889, -100.40152));
                break;
            case R.id.Ruta4:
                line.add(new LatLng(20.56903, -100.40227));
                line.add(new LatLng(20.57007, -100.40350));
                line.add(new LatLng(20.57316, -100.40516));
                break;
            case R.id.Ruta5:
                line.add(new LatLng(20.57769, -100.40147));
                line.add(new LatLng(20.57738, -100.40077));
                line.add(new LatLng(20.57687, -100.40112));

                break;
            case R.id.Ruta6:
                line.add(new LatLng(20.57681, -100.40183));
                line.add(new LatLng(20.58168, -100.40680));
                line.add(new LatLng(20.58356, -100.40531));
                break;
            case R.id.Ruta7:
                line.add(new LatLng(20.58401, -100.40481));
                line.add(new LatLng(20.58396, -100.40339));
                line.add(new LatLng(20.58451, -100.40213));
                break;
            case R.id.Ruta8:
                line.add(new LatLng(20.59530, -100.40693));
                break;
            case R.id.Ruta9:
                break;
            default:break;

        }
        return line;
    }
}
