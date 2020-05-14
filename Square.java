package com.e.civilabc;

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

public class Square extends AppCompatActivity {

    private TextView area_ans,volume_ans;
    private float m=0,n=0,t=0,area,volume;
    private EditText m_edt,t_edt;
    private Button areabtn,volbtn,nextbtn ;
    private Boolean hasFuture = false;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();
         /*
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
        },60,60, TimeUnit.SECONDS);

       */

        m_edt = (EditText) findViewById(R.id.editText_mc);
        t_edt = (EditText) findViewById(R.id.editText_t);

        area_ans = (TextView) findViewById(R.id.textView_area);
        volume_ans = (TextView) findViewById(R.id.textView_volumeh);

        areabtn = (Button) findViewById(R.id.button_area1);
        volbtn = (Button) findViewById(R.id.button_vol1);
        nextbtn = (Button) findViewById(R.id.button_next1);

        areabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    m = Float.parseFloat(m_edt.getText().toString());
                    area = m * m;
                    area_ans.setText(Float.toString(area));
                    volume_ans.setText("");
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"m or n value is not valid",Toast.LENGTH_SHORT).show();
                    area_ans.setText("");
                }
            }
        });

        volbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    m = Float.parseFloat(m_edt.getText().toString());
                    t = Float.parseFloat(t_edt.getText().toString());
                    area = m * m;
                    volume = t*area ;
                    hasFuture = true ;
                    area_ans.setText(Float.toString(area ));
                    volume_ans.setText(Float.toString(volume));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"m , n or t value is not valid",Toast.LENGTH_SHORT).show();
                    area_ans.setText("");
                    volume_ans.setText("");
                }
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category = new Intent(getApplicationContext(),Category.class);
                category.putExtra("area",area);
                category.putExtra("volume",volume);
                startActivity(category);
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
                mAdview = new AdView(Square.this);
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

