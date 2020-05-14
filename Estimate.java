package com.e.civilabc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


// Cft 1.54 Weat volume
//sfi X 4.02=Rod
// Cft 35.315
// brick = sft x 7.5
//plaster sft x 3
//paint sft x 3
//camical cement x 0.25



public class Estimate extends AppCompatActivity {
    EditText mLength ,mWidth,mThickness ;
    double volumef;
    double SBvolumef;
    double Tareasft;
    double cr;
    double sr;
    double mr;
    double rtotal;
    double ca;
    double sa;
    double ma ;
    double WaiteVolume,WithpineTaka,WithOutpineTaka,BrickNsft,CamicalUse,TotalPaint,TotalPlaster,mRod,mBeamvolum ;



    EditText c_in,s_in,m_in;
    Button calc;
    TextView RodxSft,wetvolum,BrickSft,plastersft,camical,withoutpile,withpile,Beamvolum;
    TextView mTvolumeh,mareasft,mWetVolum,mWithOutPileR,mWithPileR,mCementR,mPaintR,mPlasterR,mChemicalR,mBrickR,mSandR,mBrickStoneR,mReinforcementR;
    private InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();
        // Aditional Value
        RodxSft = (TextView) findViewById(R.id.sftxrod);
        Beamvolum = (TextView) findViewById(R.id.Beamvolum);
        wetvolum = (TextView) findViewById(R.id.witVolume);
        BrickSft = (TextView) findViewById(R.id.Bricksft);
        plastersft = (TextView) findViewById(R.id.plasterPaintSft);
        camical = (TextView) findViewById(R.id.Camical);
        withoutpile = (TextView) findViewById(R.id.woPileTaka);
        withpile = (TextView) findViewById(R.id.wPileTaka);
        // Show Value
        mTvolumeh = (TextView) findViewById(R.id.Tvolumeh);
        mareasft = (TextView) findViewById(R.id.areasft);
        mWetVolum = (TextView) findViewById(R.id.WetVolum);
        mWithPileR = (TextView) findViewById(R.id.WithPileR);
        mWithOutPileR = (TextView) findViewById(R.id.WithOutPileR);
        mCementR = (TextView) findViewById(R.id.CementR);
        mPaintR = (TextView) findViewById(R.id.PaintR);
        mPlasterR = (TextView) findViewById(R.id.PlasterR);
        mChemicalR = (TextView) findViewById(R.id.ChemicalR);
        mBrickR = (TextView) findViewById(R.id.BrickR);
        mSandR = (TextView) findViewById(R.id.SandR);
        mBrickStoneR = (TextView) findViewById(R.id.BrickStoneR);
        mReinforcementR = (TextView) findViewById(R.id.ReinforcementR);
        mLength = (EditText) findViewById(R.id.Length);
        mWidth = (EditText) findViewById(R.id.Width);
        mThickness = (EditText) findViewById(R.id.Thickness);
        //Ratio
        c_in = (EditText) findViewById(R.id.Ratio1);
        s_in = (EditText) findViewById(R.id.Ratio2);
        m_in = (EditText) findViewById(R.id.Ratio3);

        calc = (Button) findViewById(R.id.SubmitBTN);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double cr = Double.parseDouble(c_in.getText().toString());
                    double sr = Double.parseDouble(s_in.getText().toString());
                    double mr = Double.parseDouble(m_in.getText().toString());
                    double L = Double.parseDouble(mLength.getText().toString());
                    double W = Double.parseDouble(mWidth.getText().toString());
                    double T = Double.parseDouble(mThickness.getText().toString());
                    //RodxSft,wetvolum,BrickSft,plastersft,camical,withoutpile,withpile;
                    //double mwot,mwt,mbricksft,mCme,,mPin,mPlas,mRod ;
                    double Vwait = Double.parseDouble(wetvolum.getText().toString());
                    double TRod = Double.parseDouble(RodxSft.getText().toString());
                    double TBrickSft = Double.parseDouble(BrickSft.getText().toString());
                    double Mplas = Double.parseDouble(plastersft.getText().toString());
                    double Mcam = Double.parseDouble(camical.getText().toString());
                    double Mwp = Double.parseDouble(withoutpile.getText().toString());
                    double Mwop = Double.parseDouble(withpile.getText().toString());
                    double Mbemv = Double.parseDouble(Beamvolum.getText().toString());


                    Tareasft = L * W;
                    volumef = Tareasft * T;
                    WaiteVolume =volumef * Vwait;
                    mBeamvolum = volumef * Mbemv;
                    SBvolumef = volumef +mBeamvolum;

                    mRod = Tareasft * TRod;
                    BrickNsft = Tareasft * TBrickSft;
                    TotalPlaster = Tareasft * TBrickSft;
                    TotalPaint = Tareasft * TBrickSft;
                    WithpineTaka = Tareasft * Mwp;
                    WithOutpineTaka = Tareasft * Mwop;
                    rtotal = cr + sr + mr;
                    ca = cr * SBvolumef / rtotal;
                    sa = sr * SBvolumef/ rtotal;
                    ma = mr * SBvolumef/ rtotal;
                    CamicalUse = ca * Mcam;

                    mareasft.setText(new String().valueOf(Tareasft) );
                    mTvolumeh.setText(new String().valueOf(SBvolumef) );
                    mWetVolum.setText(new String().valueOf(WaiteVolume) );
                    mReinforcementR.setText(new String().valueOf(mRod) );
                    mBrickR.setText(new String().valueOf(BrickNsft) );
                    mPlasterR.setText(new String().valueOf(TotalPlaster) );
                    mPaintR.setText(new String().valueOf(TotalPaint) );
                    mChemicalR.setText(new String().valueOf(CamicalUse) );
                    mWithPileR.setText(new String().valueOf(WithpineTaka) );
                    mWithOutPileR.setText(new String().valueOf(WithOutpineTaka) );

                    mCementR.setText(new String().valueOf(ca) );
                    mSandR.setText(new String().valueOf(sa) );
                    mBrickStoneR.setText(new String().valueOf(ma) );



                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"All are not valid",Toast.LENGTH_SHORT).show();
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
                mAdview = new AdView(Estimate.this);
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