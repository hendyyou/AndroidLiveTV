package com.app.androidlivetv;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VideoPlayActivity extends AppCompatActivity {

    private BetterVideoPlayer player;
    String url;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        Intent intent = getIntent();
        url = intent.getStringExtra("videoUrl");
        player = findViewById(R.id.player);
        player.setSource(Uri.parse(url));

        final InterstitialAd interstitialAd = new InterstitialAd(this, "193554061465594_193654451455555");
        interstitialAd.setAdListener(new AbstractAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                super.onError(ad, adError);
                Toast.makeText(VideoPlayActivity.this, adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                interstitialAd.show();
            }
        });

        AdSettings.addTestDevice("768ef5d6-74f7-4b77-8df1-09f0c16fb186");

        interstitialAd.loadAd();

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }
}
