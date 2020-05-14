package com.e.civilabc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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

public class Column extends AppCompatActivity {
    public float Ans1stDia,Ans2ndDia,Ans3rdDia,Ans4thDia,AnsTotal;
    public TextView Dia1Ans,Dia2Ans,Dia3Ans,Dia4Ans,LmTXT,Rod162,
            ShowAndDia1,ShowAndDia2,ShowAndDia3,ShowAndDia4,TopTXT,TotalAns;
    public float Dia1L = 0,Dia1N = 0,Dia2L = 0,Dia2N = 0;
    public float Dia3L = 0;
    public float Dia3N = 0;
    public float Dia4L = 0;
    public float Dia4N = 0;
    public float Diafast162;
    private Button EnterBtn;
    String InterstitialAd;
    public float Lm;
    public float Mdia1 = 0;
    public float Mdia2 = 0;
    public float Mdia3 = 0;
    public float Mdia4 = 0;
    public float NumberColum = 0;
    private float RM;
    String bannerid;
    private Boolean hasFuture = false;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private InterstitialAd mInterstitialAd;
    public EditText mLenth1,mLenth2,mLenth3,mLenth4;
    public EditText mNumber1,mNumber2,mNumber3,mNumber4;
    public EditText mainDia1,mainDia2,mainDia3,mainDia4;
    public EditText mNumberColum;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_column);
        TextView textView = (TextView) findViewById(R.id.txt_unit);
        this.TopTXT = textView;
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();
        Dia1Ans = (TextView) findViewById(R.id.Dia1AnsF);
        Dia2Ans = (TextView) findViewById(R.id.Dia2AnsF);
        Dia3Ans = (TextView) findViewById(R.id.Dia3AnsF);
        Dia4Ans = (TextView) findViewById(R.id.Dia4AnsF);
        ShowAndDia1 = (TextView) findViewById(R.id.DiamainNO1);
        ShowAndDia2 = (TextView) findViewById(R.id.DiamainNO2);
        ShowAndDia3 = (TextView) findViewById(R.id.DiamainNO3);
        ShowAndDia4 = (TextView) findViewById(R.id.DiamainNO4);
        TotalAns = (TextView) findViewById(R.id.TotalRod);
        LmTXT = (TextView) findViewById(R.id.Lenthmeter);
        Rod162 = (TextView) findViewById(R.id.RodFurmulaunit);
        EnterBtn = (Button) findViewById(R.id.button_area1);
        mNumberColum = (EditText) findViewById(R.id.NumberOfculam);
        mainDia1 = (EditText) findViewById(R.id.MineDia1);
        mLenth1 = (EditText) findViewById(R.id.Lenth25);
        mNumber1 = (EditText) findViewById(R.id.Number25);
        mainDia2 = (EditText) findViewById(R.id.MineDia2);
        mLenth2 = (EditText) findViewById(R.id.Lenth22);
        mNumber2 = (EditText) findViewById(R.id.Number22);
        mainDia3 = (EditText) findViewById(R.id.MineDia3);
        mLenth3 = (EditText) findViewById(R.id.Lenth20);
        mNumber3 = (EditText) findViewById(R.id.Number20);
        mainDia4 = (EditText) findViewById(R.id.MineDia4);
        mLenth4 = (EditText) findViewById(R.id.Lenth10);
        mNumber4 = (EditText) findViewById(R.id.Number10);
        EnterBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    NumberColum = Float.parseFloat(Column.this.mNumberColum.getText().toString());
                    Mdia1 = Float.parseFloat(Column.this.mainDia1.getText().toString());
                    Dia1L = Float.parseFloat(Column.this.mLenth1.getText().toString());
                    Dia1N = Float.parseFloat(Column.this.mNumber1.getText().toString());
                    Mdia2 = Float.parseFloat(Column.this.mainDia2.getText().toString());
                    Dia2L = Float.parseFloat(Column.this.mLenth2.getText().toString());
                    Dia2N = Float.parseFloat(Column.this.mNumber2.getText().toString());
                    Mdia3 = Float.parseFloat(Column.this.mainDia3.getText().toString());
                    Dia3L = Float.parseFloat(Column.this.mLenth3.getText().toString());
                    Dia3N = Float.parseFloat(Column.this.mNumber3.getText().toString());
                    Mdia4 = Float.parseFloat(Column.this.mainDia4.getText().toString());
                    Dia4L = Float.parseFloat(Column.this.mLenth4.getText().toString());
                    Dia4N = Float.parseFloat(Column.this.mNumber4.getText().toString());

                    Ans1stDia = ((Mdia1 * Mdia1) / Diafast162) * (((Dia1L * Dia1N) * NumberColum) / Lm);
                    Dia1Ans.setText(Float.toString(Ans1stDia));
                    ShowAndDia1.setText(Float.toString(Mdia1));

                    Ans2ndDia = ((Mdia2 * Mdia2) / Diafast162) * (((Dia2L * Dia2N) * NumberColum) / Lm);
                    Dia2Ans.setText(Float.toString(Ans2ndDia));
                    ShowAndDia2.setText(Float.toString(Mdia2));

                    Ans3rdDia = ((Mdia3 * Mdia3) / Diafast162) * (((Dia3L * Dia3N) * NumberColum) / Lm);
                    Dia3Ans.setText(Float.toString(Ans3rdDia));
                    ShowAndDia3.setText(Float.toString(Mdia3));

                    Ans4thDia = ((Mdia4 * Mdia4) / Diafast162) * (((Dia4L * Dia4N) * NumberColum) / Lm);
                    Dia4Ans.setText(Float.toString(Ans4thDia));
                    ShowAndDia4.setText(Float.toString(Mdia4));
                    AnsTotal = Ans1stDia + Ans2ndDia + Ans3rdDia + Ans4thDia;
                    TotalAns.setText(Float.toString(AnsTotal));
                } catch (Exception e) {
                    Toast.makeText(Column.this.getApplicationContext(), "All value is not valid", Toast.LENGTH_SHORT).show();
                    Column.this.Dia1Ans.setText("");
                    Column.this.Dia2Ans.setText("");
                    Column.this.Dia3Ans.setText("");
                    Column.this.Dia4Ans.setText("");
                    Column.this.TotalAns.setText("");
                }
            }
        });
    }

    public void BannerAds() {
        FirebaseDatabase.getInstance().getReference().child("Adunits").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                bannerid = String.valueOf(dataSnapshot.child("Banner").getValue().toString());
                InterstitialAd = String.valueOf(dataSnapshot.child("Interstitial").getValue().toString());
                prepaperAD();
                View view = findViewById(R.id.rlbanner);
                mAdview = new AdView(Column.this);
                mAdview.setAdSize(AdSize.BANNER);
                ((RelativeLayout) view).addView(mAdview);
                mAdview.setAdUnitId(bannerid);
                mAdview.loadAd(new AdRequest.Builder().build());
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void prepaperAD() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(this.InterstitialAd);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                   finish();
                }
            });
            return;
        }
        super.onBackPressed();
    }
}
    