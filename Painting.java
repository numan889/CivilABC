package com.e.civilabc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Painting extends AppCompatActivity {

    float area,sq_ltr,litres;
    EditText area_txt,sqltr,txt_costlt;
    Button calc,cost_calc;
    TextView txtvw_l , txtvw_c ;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                        }else{
                            Log.d("TAG","InterstitialAd Not Loaded" );
                        }
                        prepaperAD();

                    }
                });
            }
        },180,180, TimeUnit.SECONDS);


        Intent past = getIntent() ;
        area = past.getFloatExtra("area",0);
        area_txt=(EditText) findViewById(R.id.txt_area);
        area_txt.setText(Float.toString(area));
        txtvw_l = (TextView) findViewById(R.id.txtvw_litres);
        txtvw_c = (TextView) findViewById(R.id.txtvw_cost);

        cost_calc = (Button) findViewById(R.id.button_cost);
        txt_costlt = (EditText) findViewById(R.id.txt_costlt);
        sqltr = (EditText) findViewById(R.id.txt_sqlt);
        calc = (Button) findViewById(R.id.button_calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area = Float.parseFloat(area_txt.getText().toString()) ;
                sq_ltr = Float.parseFloat(sqltr.getText().toString()) ;
                txtvw_l.setText(Float.toString(litres = area/sq_ltr));
            }
        });

        cost_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtvw_c.setText(Float.toString(Float.parseFloat(txt_costlt.getText().toString())*litres)) ;
            }
        });
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
                mAdview = new AdView(Painting.this);
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
}
