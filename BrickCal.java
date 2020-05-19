package com.e.civilabc;
//BrickCal

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
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

    TextView vol_forward,area_forward ,B_out5,c_out5 ,s_out5,RajM5_out,RajHel5_out,
            B_out10,c_out10 ,s_out10,RajM10_out,RajHel10_out;
    double volumef;
    double Areaf;
    double Brick10 = 11, CementBrick10=0.00363636364,  SandBrick10 = 0.02727273, RajMistry10 = 0.0037, Helper10 = 0.0055;
    double Brick5 = 5, CementBrick5 = 0.006 , SandBrick5 = 0.026, RajMistry5 = 0.005, Helper5 = 0.008;
    double BAns5,SAns5,CAns5,RajMAns5,RajhelAns5;
    double BAns10,SAns10,CAns10,RajMAns10,RajhelAns10;



    EditText c_in,s_in;
    Button calc;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;
    String NativeAdd;
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
        MobileAds.initialize(this);

        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        //the native ad will be available inside this method  (unifiedNativeAd)
                        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.native_ad_layout, null);
                        mapUnifiedNativeAdToLayout(unifiedNativeAd, unifiedNativeAdView);

                        FrameLayout nativeAdLayout = findViewById(R.id.id_native_ad);
                        nativeAdLayout.removeAllViews();
                        nativeAdLayout.addView(unifiedNativeAdView);
                    }
                })
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());

        Intent past = getIntent();
        volumef  = past.getFloatExtra("volume",0);
        Areaf  = past.getFloatExtra("area",0);
        vol_forward = (TextView) findViewById(R.id.textView_vol);
        area_forward = (TextView) findViewById(R.id.BrickAreaAns);

        vol_forward.setText("Answer = " + new String().valueOf(volumef));
        area_forward.setText("Answer = " + new String().valueOf(Areaf));

        //vol_forward.setText(Float.toString(volumef));

        c_in = (EditText) findViewById(R.id.txt_cr);
        s_in = (EditText) findViewById(R.id.txt_sr);
        calc = (Button) findViewById(R.id.button_calc);

        B_out5 = (TextView) findViewById(R.id.BwNeed5);
        c_out5 = (TextView) findViewById(R.id.view_cement5);
        s_out5 = (TextView) findViewById(R.id.view_sand5);
        RajM5_out = (TextView) findViewById(R.id.txtRajmistery5);
        RajHel5_out = (TextView) findViewById(R.id.txtRajmisteryhelper5);

        B_out10 = (TextView) findViewById(R.id.BwNeed10);
        c_out10 = (TextView) findViewById(R.id.view_cement10);
        s_out10 = (TextView) findViewById(R.id.view_sand10);
        RajM10_out = (TextView) findViewById(R.id.txtRajmistery10);
        RajHel10_out = (TextView) findViewById(R.id.txtRajmisteryhelper10);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double cr = Double.parseDouble(c_in.getText().toString());
                    double sr = Double.parseDouble(s_in.getText().toString());


                    BAns5 = Areaf * Brick5;
                    CAns5 = BAns5 * CementBrick5;
                    SAns5 = BAns5 * SandBrick5;
                    RajMAns5 = BAns5 * RajMistry5;
                    RajhelAns5 = BAns5 * Helper5;

                    B_out5.setText(new String().valueOf(BAns5) );
                    c_out5.setText(new String().valueOf(CAns5) );
                    s_out5.setText(new String().valueOf(SAns5) );
                    RajM5_out.setText(new String().valueOf(RajMAns5) );
                    RajHel5_out.setText(new String().valueOf(RajhelAns5) );

                    BAns10 = volumef * Brick10;
                    CAns10 = BAns10 * CementBrick10;
                    SAns10 = BAns10 * SandBrick10;
                    RajMAns10 = BAns10 * RajMistry10;
                    RajhelAns10 = BAns10 * Helper10;

                    B_out10.setText(new String().valueOf(BAns10) );
                    c_out10.setText(new String().valueOf(CAns10) );
                    s_out10.setText(new String().valueOf(SAns10) );
                    RajM10_out.setText(new String().valueOf(RajMAns10) );
                    RajHel10_out.setText(new String().valueOf(RajhelAns10) );


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"ratios are not valid",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void mapUnifiedNativeAdToLayout(UnifiedNativeAd adFromGoogle, UnifiedNativeAdView myAdView) {
        MediaView mediaView = myAdView.findViewById(R.id.ad_media);
        myAdView.setMediaView(mediaView);

        myAdView.setHeadlineView(myAdView.findViewById(R.id.ad_headline));
        myAdView.setBodyView(myAdView.findViewById(R.id.ad_body));
        myAdView.setCallToActionView(myAdView.findViewById(R.id.ad_call_to_action));
        myAdView.setIconView(myAdView.findViewById(R.id.ad_icon));
        myAdView.setPriceView(myAdView.findViewById(R.id.ad_price));
        myAdView.setStarRatingView(myAdView.findViewById(R.id.ad_rating));
        myAdView.setStoreView(myAdView.findViewById(R.id.ad_store));
        myAdView.setAdvertiserView(myAdView.findViewById(R.id.ad_advertiser));

        ((TextView) myAdView.getHeadlineView()).setText(adFromGoogle.getHeadline());

        if (adFromGoogle.getBody() == null) {
            myAdView.getBodyView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getBodyView()).setText(adFromGoogle.getBody());
        }

        if (adFromGoogle.getCallToAction() == null) {
            myAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            ((Button) myAdView.getCallToActionView()).setText(adFromGoogle.getCallToAction());
        }

        if (adFromGoogle.getIcon() == null) {
            myAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) myAdView.getIconView()).setImageDrawable(adFromGoogle.getIcon().getDrawable());
        }

        if (adFromGoogle.getPrice() == null) {
            myAdView.getPriceView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getPriceView()).setText(adFromGoogle.getPrice());
        }

        if (adFromGoogle.getStarRating() == null) {
            myAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) myAdView.getStarRatingView()).setRating(adFromGoogle.getStarRating().floatValue());
        }

        if (adFromGoogle.getStore() == null) {
            myAdView.getStoreView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getStoreView()).setText(adFromGoogle.getStore());
        }

        if (adFromGoogle.getAdvertiser() == null) {
            myAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getAdvertiserView()).setText(adFromGoogle.getAdvertiser());
        }

        myAdView.setNativeAd(adFromGoogle);
    }

    public void BannerAds(){
        DatabaseReference roofRef = FirebaseDatabase.getInstance().getReference().child("Adunits");
        roofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bannerid = String.valueOf(dataSnapshot.child("Banner").getValue().toString());
                InterstitialAd = String.valueOf(dataSnapshot.child("Interstitial").getValue().toString());
                NativeAdd = String.valueOf(dataSnapshot.child("Native").getValue().toString());
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

