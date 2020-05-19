package com.e.civilabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class Plastering extends AppCompatActivity {

    TextView Area_forward ,c_out ,s_out,c_out6 ,s_out6,RajMBrick,HelMBrick,RajMRcc,HelRcc;
    double Areaf,volumDry,volumDry6;
    double rtotal,rtotal6, ca, sa,ca6, sa6;
    EditText c_in,s_in,c_in6,s_in6;
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
        setContentView(R.layout.activity_plastering);
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
        Areaf  = past.getFloatExtra("area",0);
        Area_forward = (TextView) findViewById(R.id.textView_vol);
        Area_forward.setText("Answer = " + new String().valueOf(Areaf));
        //vol_forward.setText(Float.toString(volumef));

        c_in = (EditText) findViewById(R.id.txt_cr);
        s_in = (EditText) findViewById(R.id.txt_sr);
        c_in6 = (EditText) findViewById(R.id.txt_cr6);
        s_in6 = (EditText) findViewById(R.id.txt_sr6);

        calc = (Button) findViewById(R.id.button_calc);

        c_out = (TextView) findViewById(R.id.view_cement);
        s_out = (TextView) findViewById(R.id.view_sand);
        RajMBrick = (TextView) findViewById(R.id.txtRajmisteryBrick);
        HelMBrick = (TextView) findViewById(R.id.txtRajmisteryhelperBrick);
        c_out6 = (TextView) findViewById(R.id.view_cement6);
        s_out6 = (TextView) findViewById(R.id.view_sand6);
        RajMRcc = (TextView) findViewById(R.id.txtRajmisteryRcc);
        HelRcc = (TextView) findViewById(R.id.txtRajmisteryhelperRcc);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double cr = Double.parseDouble(c_in.getText().toString());
                    double sr = Double.parseDouble(s_in.getText().toString());
                    double cr6 = Double.parseDouble(c_in6.getText().toString());
                    double sr6 = Double.parseDouble(s_in6.getText().toString());
                    double Sand12,Sand6,Cement = 1.25;
                    double DryVolum154 = 1.5,Thik12mm = 0.04, Thik6mm = 0.02;
                    double Cement12mm,Cement6mm;
                    double Sand12mm,Sandt6mm;
                    double mestery = 0.01,helper = 0.02,mesB,HelB,mesR,HelR;

                    rtotal = cr + sr ;
                    volumDry = Areaf * Thik12mm * DryVolum154;
                    ca =  volumDry / rtotal;
                    sa = volumDry / rtotal;
                    Sand12mm = sa * sr;
                    Cement12mm = ca / Cement;
                    mesB = Areaf * mestery;
                    HelB = Areaf * helper;


                    c_out.setText(new String().valueOf(Cement12mm) );
                    s_out.setText(new String().valueOf(Sand12mm) );
                    RajMBrick.setText(new String().valueOf(mesB) );
                    HelMBrick.setText(new String().valueOf(HelB) );

                    rtotal6 = cr6 + sr6 ;
                    volumDry6 = Areaf * Thik6mm * DryVolum154;
                    ca6 =  volumDry6 / rtotal6;
                    sa6 = volumDry6 / rtotal6;
                    Sandt6mm = sa6 * sr6;
                    Cement6mm = ca6 / Cement;
                    mesR = Areaf * mestery;
                    HelR = Areaf * helper;

                    c_out6.setText(new String().valueOf(Cement6mm) );
                    s_out6.setText(new String().valueOf(Sandt6mm) );
                    RajMRcc.setText(new String().valueOf(mesR) );
                    HelRcc.setText(new String().valueOf(HelR) );


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Ratios are not valid",Toast.LENGTH_SHORT).show();
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
                mAdview = new AdView(Plastering.this);
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
