package com.e.civilabc;
//BrickCal

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
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

public class BrickCal extends AppCompatActivity {

    TextView vol_forward ,c_out ,s_out,m_out ;
    double volumef;
    double cr;
    double sr;
    double mr;
    double rtotal;
    double ca;
    double sa;
    double ma ;
    EditText c_in,s_in,m_in;
    Button calc;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brickcal);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();

     /*   ScheduledExecutorService scheduledExecutorService =
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
        },60,60, TimeUnit.SECONDS);

*/

        Intent past = getIntent();
        volumef  = past.getFloatExtra("volume",0);
        vol_forward = (TextView) findViewById(R.id.textView_vol);
        vol_forward.setText("Answer = " + new String().valueOf(volumef));
        //vol_forward.setText(Float.toString(volumef));

        c_in = (EditText) findViewById(R.id.txt_cr);
        s_in = (EditText) findViewById(R.id.txt_sr);
        m_in = (EditText) findViewById(R.id.txt_mr);

        calc = (Button) findViewById(R.id.button_calc);

        c_out = (TextView) findViewById(R.id.view_cement);
        s_out = (TextView) findViewById(R.id.view_sand);
        m_out = (TextView) findViewById(R.id.view_metal);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double cr = Double.parseDouble(c_in.getText().toString());
                    double sr = Double.parseDouble(s_in.getText().toString());
                    double mr = Double.parseDouble(m_in.getText().toString());

                    rtotal = cr + sr + mr;
                    ca = cr * volumef / rtotal;
                    sa = sr * volumef / rtotal;
                    ma = mr * volumef / rtotal;

                    c_out.setText(new String().valueOf(ca) );
                    m_out.setText(new String().valueOf(ma) );
                    s_out.setText(new String().valueOf(sa) );


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"ratios are not valid",Toast.LENGTH_SHORT).show();
                }
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
                mAdview = new AdView(BrickCal.this);
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

