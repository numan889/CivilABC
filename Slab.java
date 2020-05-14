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

public class Slab extends AppCompatActivity {
    public TextView C7omtxt150;
    public float Cement5;
    public float Cement7;
    public float ComCement5 = 0.0f;
    public float ComCement7 = 0.0f;
    public float ComSand5 = 0.0f;
    public float ComSand7 = 0.0f;
    public float ComStome5 = 0.0f;
    public float ComStome7 = 0.0f;
    public float Comon154 = 0.0f;
    public TextView Comtxt150;
    public float DiryV5;
    public float DiryV7;
    String InterstitialAd;
    private TextView M7ComCem;
    public TextView MComCem;
    public float Metal5;
    public float Metal7;
    public float N = 0;
    public EditText N_edt;
    public float Rat5 = 0;
    public float Rat7 = 0;
    public float RodCal;
    public float Sand5;
    public float Sand7;
    public float Sftrod = 0;
    public float area;
    public TextView area_ans;
    private Button areabtn;
    String bannerid;
    TextView c_out;
    public TextView cement5Ans;
    public TextView cement7Ans;
    public Boolean hasFuture = false;
    public float m = 0.0f;
    public TextView m7CommonStone;
    AdView mAdview;
    FirebaseAuth mAuth;
    public TextView mCommonSand5;
    public TextView mCommonStone;
    DatabaseReference mDatabase;
    private InterstitialAd mInterstitialAd;
    public TextView mRonehufthree;
    public TextView mRonetowfour;
    public EditText m_edt;
    TextView m_out;
    public TextView metal5Ans;
    public TextView metal7Ans;
    public float n = 0.0f;
    public EditText n_edt;
    private Button nextbtn;
    public TextView rod_ans;
    public TextView rod_sft;
    TextView s_out;
    public TextView sand5Ans;
    public TextView sand7Ans;
    public float t = 0.0f;
    public EditText t_edt;
    TextView vol_forward;
    private Button volbtn;
    public float volume;public TextView volume_ans;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_slab);
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();
        this.m_edt = (EditText) findViewById(R.id.editText_mc);
        this.n_edt = (EditText) findViewById(R.id.editText_nc);
        this.t_edt = (EditText) findViewById(R.id.editText_t);
        this.N_edt = (EditText) findViewById(R.id.editText_Nos);
        this.area_ans = (TextView) findViewById(R.id.textView_area);
        this.volume_ans = (TextView) findViewById(R.id.textView_volumeh);
        this.rod_ans = (TextView) findViewById(R.id.textView_rod);
        this.rod_sft = (TextView) findViewById(R.id.slabrodsft);
        this.mRonehufthree = (TextView) findViewById(R.id.Ronehufthree);
        this.mRonetowfour = (TextView) findViewById(R.id.Ronetowfour);
        this.Comtxt150 = (TextView) findViewById(R.id.CommonTXT150);
        this.mCommonStone = (TextView) findViewById(R.id.CommonStone);
        this.MComCem = (TextView) findViewById(R.id.ComCementTXT);
        this.mCommonSand5 = (TextView) findViewById(R.id.CommonSand5);
        this.C7omtxt150 = (TextView) findViewById(R.id.CommonTXTSand7);
        this.m7CommonStone = (TextView) findViewById(R.id.CommonStone7);
        this.M7ComCem = (TextView) findViewById(R.id.ComCementTXT);
        this.cement5Ans = (TextView) findViewById(R.id.view_cement5);
        this.sand5Ans = (TextView) findViewById(R.id.view_sand5);
        this.metal5Ans = (TextView) findViewById(R.id.view_metal5);
        this.cement7Ans = (TextView) findViewById(R.id.view_cement7);
        this.sand7Ans = (TextView) findViewById(R.id.view_sand7);
        this.metal7Ans = (TextView) findViewById(R.id.view_metal7);
        this.areabtn = (Button) findViewById(R.id.button_area1);
        this.volbtn = (Button) findViewById(R.id.button_vol1);
        this.nextbtn = (Button) findViewById(R.id.button_next1);

        this.areabtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    m = Float.parseFloat(Slab.this.m_edt.getText().toString());
                    n = Float.parseFloat(Slab.this.n_edt.getText().toString());
                    N = Float.parseFloat(Slab.this.N_edt.getText().toString());
                    Sftrod = Float.parseFloat(Slab.this.rod_sft.getText().toString());

                    area = m * n * N;
                    RodCal = area * Sftrod;
                    area_ans.setText(Float.toString(Slab.this.area));
                    rod_ans.setText(Float.toString(Slab.this.RodCal));
                    volume_ans.setText("");
                } catch (Exception e) {
                    Toast.makeText(Slab.this.getApplicationContext(), "All value is not valid", Toast.LENGTH_SHORT).show();
                    Slab.this.area_ans.setText("");
                }
            }
        });
        this.volbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    m = Float.parseFloat(Slab.this.m_edt.getText().toString());
                    n = Float.parseFloat(Slab.this.n_edt.getText().toString());
                    N = Float.parseFloat(Slab.this.N_edt.getText().toString());
                    t = Float.parseFloat(Slab.this.t_edt.getText().toString());
                    area = m * n * N;
                    volume = t * area;
                    hasFuture = true;
                    area_ans.setText(Float.toString(Slab.this.area));
                    volume_ans.setText(Float.toString(Slab.this.volume));
                } catch (Exception e) {
                    Toast.makeText(Slab.this.getApplicationContext(), "All value is not valid",Toast.LENGTH_SHORT).show();
                    Slab.this.area_ans.setText("");
                    Slab.this.volume_ans.setText("");
                }
            }
        });
        this.nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Rat5 = Float.parseFloat(Slab.this.mRonehufthree.getText().toString());
                    Rat7 = Float.parseFloat(Slab.this.mRonetowfour.getText().toString());
                    Comon154 = Float.parseFloat(Slab.this.Comtxt150.getText().toString());
                    ComCement5 = Float.parseFloat(Slab.this.MComCem.getText().toString());
                    ComSand5 = Float.parseFloat(Slab.this.mCommonSand5.getText().toString());
                    ComStome5 = Float.parseFloat(Slab.this.mCommonStone.getText().toString());
                    ComCement7 = Float.parseFloat(Slab.this.MComCem.getText().toString());
                    ComSand7 = Float.parseFloat(Slab.this.C7omtxt150.getText().toString());
                    ComStome7 = Float.parseFloat(Slab.this.m7CommonStone.getText().toString());
                    m = Float.parseFloat(Slab.this.m_edt.getText().toString());
                    n = Float.parseFloat(Slab.this.n_edt.getText().toString());
                    t = Float.parseFloat(Slab.this.t_edt.getText().toString());
                    N = Float.parseFloat(Slab.this.N_edt.getText().toString());
                    Sftrod = Float.parseFloat(Slab.this.rod_sft.getText().toString());

                    area = m * n * N;
                    volume = t * area;
                    hasFuture = true;

                    DiryV5 = (volume * Comon154) / Rat5;
                    RodCal = area * Sftrod;
                    Cement5 = DiryV5 / ComCement5;
                    Sand5 = DiryV5 * ComSand5;
                    Metal5 = DiryV5 * ComStome5;
                    DiryV7 = (volume * Comon154) / Rat7;
                    Cement7 = DiryV7 / ComCement7;
                    Sand7 = DiryV7 * ComSand7;
                    Metal7 = DiryV7 * ComStome7;

                    rod_ans.setText(Float.toString(RodCal));
                    cement5Ans.setText(Float.toString(Cement5));
                    sand5Ans.setText(Float.toString(Sand5));
                    metal5Ans.setText(Float.toString(Metal5));
                    cement7Ans.setText(Float.toString(Cement7));
                    sand7Ans.setText(Float.toString(Sand7));
                    metal7Ans.setText(Float.toString(Metal7));
                    volume_ans.setText(Float.toString(volume));
                    area_ans.setText(Float.toString(area));
                } catch (Exception e) {
                    Toast.makeText(Slab.this.getApplicationContext(), "All value is not valid", Toast.LENGTH_SHORT).show();
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
               mAdview = new AdView(Slab.this);
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
        interstitialAd.setAdUnitId(InterstitialAd);
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
