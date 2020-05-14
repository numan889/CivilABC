package com.e.civilabc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

public class Category extends AppCompatActivity {
    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String InterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        final Intent past = getIntent();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BannerAds();

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<CharSequence> catList = ArrayAdapter.createFromResource(this , R.array.category_list ,android.R.layout.simple_list_item_1);
        listView.setAdapter(catList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent concrete  = new Intent(getApplicationContext(),Concrete.class);
                    try {
                        concrete.putExtra("volume", past.getFloatExtra("volume",0));
                        startActivity(concrete);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Volume is not valid.",Toast.LENGTH_SHORT).show();
                    }
                }
                if(position==1){
                    //Intent BrickCal  = new Intent(getApplicationContext(),Concrete.class);
                    //try {
                       // BrickCal.putExtra("volume", past.getFloatExtra("volume",0));
                        //startActivity(BrickCal);
                    //}catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                    //}
                }
                if(position==2){
                    Intent BrickCal  = new Intent(getApplicationContext(),BrickCal.class);
                    try {
                        BrickCal.putExtra("volume", past.getFloatExtra("volume",0));
                        startActivity(BrickCal);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Volume is not valid.",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (position==5){
                    Intent painting  = new Intent(getApplicationContext(),Painting.class);
                    try {
                        painting.putExtra("area", past.getFloatExtra("area",0));
                        startActivity(painting);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Area is not valid.",Toast.LENGTH_SHORT).show();
                    }
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
                mAdview = new AdView(Category.this);
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
