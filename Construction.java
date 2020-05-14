package com.e.civilabc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Construction extends AppCompatActivity {
    //private TextView ConstrucrionTXT,BrickTXT,PlasterTXT,PaintTXT;
    private Button ConstrucrionBTN, BrickBTN, PlasterBTN, PaintBTN;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();


        ConstrucrionBTN = (Button) findViewById(R.id.ConstructionBTN);
        BrickBTN = (Button) findViewById(R.id.BrickworkBTN);
        PlasterBTN = (Button) findViewById(R.id.PlasterBTN);
        PaintBTN = (Button) findViewById(R.id.PaintBTN);

        ConstrucrionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPshowAboutDialog();
            }
        });
        BrickBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BWshowAboutDialog();
            }


        });
        PlasterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PWshowAboutDialog();
            }


        });
        PaintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PIWshowAboutDialog();
            }


        });
    }
    // Alert Dialog with custom view
    private void CPshowAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Construction Process");
        builder.setView(R.layout.dialog);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    Toast.makeText(Construction.this, "Ad not loaded yet", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void BWshowAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Brick Work");
        builder.setView(R.layout.dialogbw);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    Toast.makeText(Construction.this, "Thanks You For Reading", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void PWshowAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Plaster Work");
        builder.setView(R.layout.dialogplaster);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    Toast.makeText(Construction.this, "Ad not loaded yet", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    private void PIWshowAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pinting Work");
        builder.setView(R.layout.dialogpaint);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                else
                    Toast.makeText(Construction.this, "Ad not loaded yet", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
                // dismiss dialog

                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void BannerAds(){
        DatabaseReference roofRef = FirebaseDatabase.getInstance().getReference().child("Adunits");
        roofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bannerid = String.valueOf(dataSnapshot.child("Banner").getValue().toString());
                InterstitialAd = String.valueOf(dataSnapshot.child("Interstitial").getValue().toString());
                prepaperAD();
                View view = findViewById(R.id.rlbanner);
                mAdview = new AdView(Construction.this);
                mAdview.setAdSize(AdSize.BANNER);
                ((RelativeLayout) view).addView(mAdview);
                mAdview.setAdUnitId(bannerid);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdview.loadAd(adRequest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void prepaperAD(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(InterstitialAd);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });


        } else {
            super.onBackPressed();
        }
    }

}

