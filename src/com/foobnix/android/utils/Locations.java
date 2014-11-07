package com.foobnix.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

@SuppressLint("NewApi")
public class Locations {

    public static Location getMyLocation(Context c) {
        LocationManager locationManager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if (lastKnownLocation == null) {
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (lastKnownLocation == null) {
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (lastKnownLocation == null) {
            LOG.d("lastKnownLocation null");
            findMyLocation(c);
        }
        LOG.d("lastKnownLocation", lastKnownLocation);
        return lastKnownLocation;
    }

    public static void findMyLocation(Context c) {
        LOG.d("findMyLocation");
        final LocationManager locationManager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);

        Criteria cr = new Criteria();
        cr.setAccuracy(Criteria.ACCURACY_COARSE);
        cr.setPowerRequirement(Criteria.POWER_HIGH);

        locationManager.requestSingleUpdate(cr, new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            @Override
            public void onLocationChanged(Location location) {

            }
        }, Looper.getMainLooper());

    }
}
