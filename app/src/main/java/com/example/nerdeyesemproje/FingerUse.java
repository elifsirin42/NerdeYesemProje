package com.example.nerdeyesemproje;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class FingerUse extends FingerprintManager.AuthenticationCallback {


    private Context context;
    private double lat;
    private double longi;


    // Constructor
    public FingerUse(Context mContext,double lat, double longi) {
        context = mContext;
        this.lat = lat;
        this.longi=longi;
    }

    //start Authentication
    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    // Authentication is not true
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update(context);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update(context );
    }


    @Override
    public void onAuthenticationFailed() {
        this.update(context);
    }


    @Override
    //If authentication succeded run list activity
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update(context);
        Intent i = new Intent(context,ListActivity.class);
        i.putExtra("lat",lat+"");
        i.putExtra("longi",longi+"");
        context.startActivity(i);
    }


    public void update(Context context) {
        Toast.makeText( context,"Parmak izi okuma başarılı", Toast.LENGTH_SHORT).show();


    }
}
