package com.e.civilabc;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

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

public class Namajshikkha extends AppCompatActivity {
    VideoView videoView;
    Button BtnSana,BtnSura1,BtnSura2,BtnSura3,BtnSura4,BtnSura5,BtnSura6,BtnSura7,BtnSura8,BtnSura9,BtnSura10,
            BtnSura11,BtnSura12,BtnSura13,BtnSura14,BtnSura15,BtnSura16,BtnSura17,BtnSura18,BtnSura19,
            BtnSura20,BtnSura21,BtnSura22,BtnSura23,BtnSura24,BtnSura25;

    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namajshikkha);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();

        videoView=findViewById(R.id.videoview);
        BtnSura1=findViewById(R.id.Sura1);
        BtnSura2=findViewById(R.id.Sura2);
        BtnSura3=findViewById(R.id.Sura3);
        BtnSura4=findViewById(R.id.Sura4);
        BtnSura5=findViewById(R.id.Sura5);
        BtnSura6=findViewById(R.id.Sura6);
        BtnSura7=findViewById(R.id.Sura7);
        BtnSura8=findViewById(R.id.Sura8);
        BtnSura9=findViewById(R.id.Sura9);
        BtnSura10=findViewById(R.id.Sura10);
        BtnSura11=findViewById(R.id.Sura11);
        BtnSura12=findViewById(R.id.Sura12);
        BtnSura13=findViewById(R.id.Sura13);
        BtnSura14=findViewById(R.id.Sura14);
        BtnSura15=findViewById(R.id.Sura15);
        BtnSura16=findViewById(R.id.Sura16);
        BtnSura17=findViewById(R.id.Sura17);
        BtnSura18=findViewById(R.id.Sura18);
        BtnSura19=findViewById(R.id.Sura19);
        BtnSura20=findViewById(R.id.Sura20);
        BtnSura21=findViewById(R.id.Sura21);
        BtnSana=findViewById(R.id.Sana);

        BtnSana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sana));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });

        BtnSura1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.surahalfatihah1));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ikhlas112));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lahab111));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.quraysh106));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kafirun109));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fil105));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.falaq113));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asr103));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.qariah101));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kawthar108));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.adiyat100));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.humazah104));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mayon107));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nasr110));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nas114));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.takathur102));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.zalzalah99));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.qadr97));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tin95));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rahman55));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
            }

        });
        BtnSura21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yasin36));
                videoView.setMediaController(new MediaController(Namajshikkha.this));
                videoView.requestFocus();
                videoView.start();
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
                mAdview = new AdView(Namajshikkha.this);
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
