package com.e.civilabc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Choose_the_Shape extends AppCompatActivity {

    private Button circle;
    private Button rectangle;
    private Button parallelogram;
    private Button bulletshape;
    private Button oval;
    private Button houseshape;
    private Button CProcess;
    private Button mSquare;
    private Button TriangleBtn;
    private Button mEstimate;
    private Button mColumn;
    private Button mSlab;


    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_the__shape);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();

 /* ScheduledExecutorService scheduledExecutorService =
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
                      BannerAds();

                    }
                });
            }
        },10,10, TimeUnit.SECONDS);

  */


        circle = (Button) findViewById(R.id.circle);
        rectangle = (Button) findViewById(R.id.rectangle);
        parallelogram = (Button) findViewById(R.id.parallelogram);
        bulletshape = (Button) findViewById(R.id.bulletshape);
        oval = (Button) findViewById(R.id.oval);
        houseshape = (Button) findViewById(R.id.houseshape);
        CProcess = (Button) findViewById(R.id.ConstructionBTN);
        mSquare = (Button) findViewById(R.id.Square);
        TriangleBtn = (Button) findViewById(R.id.TriangleB);
        mEstimate = (Button) findViewById(R.id.Estimate);
        mColumn = (Button) findViewById(R.id.Column);
        mSlab = (Button) findViewById(R.id.Slab);

        mSlab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Slab = new Intent(getApplicationContext(),Slab.class);
                startActivity(Slab);
            }
        });

        mColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Column = new Intent(getApplicationContext(),Column.class);
                startActivity(Column);
            }
        });

        CProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Construction = new Intent(getApplicationContext(),Construction.class);
                startActivity(Construction);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent circle_next = new Intent(getApplicationContext(),circle.class);
                    startActivity(circle_next);
            }
        });

        rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rect_next = new Intent(getApplicationContext(),rect.class);
                startActivity(rect_next);
            }
        });

        parallelogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent para_next = new Intent(getApplicationContext(),CommonActivity.class);
                para_next.putExtra("shape","parallelogram");
                startActivity(para_next);
            }
        });

        bulletshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bullet_next = new Intent(getApplicationContext(),CommonActivity.class);
                bullet_next.putExtra("shape","bulletshape");
                startActivity(bullet_next);
            }
        });

        oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oval_next = new Intent(getApplicationContext(),CommonActivity.class);
                oval_next.putExtra("shape","oval");
                startActivity(oval_next);
            }
        });

        houseshape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_next = new Intent(getApplicationContext(),Homeshape.class);
                startActivity(home_next);
            }
        });
        TriangleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Triangle_next = new Intent(getApplicationContext(),Triangle.class);
                startActivity(Triangle_next);

            }
        });
        mEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Estimate_next = new Intent(getApplicationContext(),Estimate.class);
                startActivity(Estimate_next);

            }
        });
        mSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Square_next = new Intent(getApplicationContext(),Square.class);
                startActivity(Square_next);

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
                mAdview = new AdView(Choose_the_Shape.this);
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



        public void  prepaperAD() {
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



