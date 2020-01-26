package com.example.nerdeyesemproje.LocationUse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class LocationManage implements LocationListener {
    Context context;


    public LocationManage(Context context) {
        super();
        this.context = context;
    }

    public Location getLocation(){
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Criteria myCriteria = new Criteria();
        myCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        try {
            LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            List<String> providers = lm.getProviders(true);

            boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (isGPSEnabled){

                myCriteria.setPowerRequirement(Criteria.POWER_LOW);
// let Android select the right location provider for you
                String myProvider = lm.getBestProvider(myCriteria, true);

// finally require updates at -at least- the desired rate
                long minTimeMillis = 500; //
                lm.requestLocationUpdates(myProvider,minTimeMillis,0,this);
                //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,30,this);

                Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                for (String provider : providers) {
                    Location l = lm.getLastKnownLocation(provider);

                    if (l == null) {
                        continue;
                    }
                    if (loc == null || l.getAccuracy() < loc.getAccuracy()) {
                        // Found best last known location: %s", l);
                        loc = l;
                    }
                }

                return loc;
            }else{
                onProviderDisabled(LocationManager.GPS_PROVIDER);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("Konum Ayarları");

        // Setting Dialog Message
        alertDialog.setMessage("Konum özelliği kapalı, Ayarlara gidip açmak ister misiniz?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Konum Ayarları", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                dialog.cancel();
            }
        });


        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        showSettingsAlert();
    }

}
