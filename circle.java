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

public class circle extends AppCompatActivity {

    private TextView areaAnswer, volAnswer;
    private float radius, thick, area_answer, volume;
    private EditText r, t;
    private Boolean hasFuture = false;// to indicate whether there are future events

    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
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

        r = (EditText) findViewById(R.id.txt_radius);
        t = (EditText) findViewById(R.id.txt_thick);
        Button area = (Button) findViewById(R.id.button_area1);
        Button vol = (Button) findViewById(R.id.button_volume);
        Button next = (Button) findViewById(R.id.button_next);
        areaAnswer = (TextView) findViewById(R.id.area_ans);
        volAnswer = (TextView) findViewById(R.id.volume_ans);

        //when area button is pressed
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    radius = Float.parseFloat(r.getText().toString().trim());
                    System.out.println(radius);
                    area_answer = (float) Math.PI * radius * radius;
                    hasFuture = true;
                    areaAnswer.setText(Float.toString(area_answer));
                    volAnswer.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "R value is not acceptable", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //when volume button is pressed
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thick_s = t.getText().toString();
                if (thick_s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Thickness is needed.", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        thick = Float.parseFloat(thick_s);
                        radius = Float.parseFloat(r.getText().toString().trim());
                        area_answer = (float) Math.PI * radius * radius;
                        volume = thick * area_answer;
                        volAnswer.setText(Float.toString(volume));
                        areaAnswer.setText(Float.toString(area_answer));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Thickness or R is not acceptable.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //when next button is pressed
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category = new Intent(getApplicationContext(), Category.class);
                category.putExtra("area", area_answer);
                category.putExtra("volume", volume);
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
                mAdview = new AdView(circle.this);
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

    public void prepaperAD() {
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