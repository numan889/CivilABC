package com.e.civilabc;


import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Homeshape extends AppCompatActivity {

    private EditText m_inh , n_inh ,t_inh,h_inh;
    private float m_value, n_value , t_value ,h_value , area , volume ;
    private String shape;
    private TextView area_view , volume_view ;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeshape);

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

        ImageView common = (ImageView) findViewById(R.id.image_common);
        common.setImageResource(R.drawable.houseshape2);

        m_inh = (EditText) findViewById(R.id.editText_mch);
        n_inh = (EditText) findViewById(R.id.editText_nch);
        t_inh = (EditText) findViewById(R.id.editText_tch);
        h_inh = (EditText) findViewById(R.id.editText_hch);

        area_view = (TextView) findViewById(R.id.textView_areah);
        volume_view = (TextView) findViewById(R.id.textView_volumeh);

        Button area_c = (Button) findViewById(R.id.button_areac);
        Button volume_c = (Button) findViewById(R.id.button_volumec);
        Button next_c = (Button) findViewById(R.id.button_nextc);

        area_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    m_value = Float.parseFloat(m_inh.getText().toString());
                    n_value = Float.parseFloat(n_inh.getText().toString());
                    h_value = Float.parseFloat(h_inh.getText().toString());
                    area = m_value * n_value + m_value * h_value;
                    area_view.setText(Float.toString(area));
                    volume_view.setText("");
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"m ,h or n values is not valid.",Toast.LENGTH_SHORT).show();
                }
            }
        });



        volume_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    m_value = Float.parseFloat(m_inh.getText().toString());
                    n_value = Float.parseFloat(n_inh.getText().toString());
                    t_value = Float.parseFloat(t_inh.getText().toString());
                    h_value = Float.parseFloat(h_inh.getText().toString());
                    area = m_value * n_value + m_value * h_value;
                    volume = area * t_value;
                    area_view.setText(Float.toString(area));
                    volume_view.setText(Float.toString(volume) );
                }catch(Exception e){
                    Toast.makeText(getApplicationContext()," m, h, thickness or n values is not valid.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        next_c.setOnClickListener(new View.OnClickListener() {
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
                mAdview = new AdView(Homeshape.this);
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
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });


        }
        else {
            super.onBackPressed();}


    }

}
