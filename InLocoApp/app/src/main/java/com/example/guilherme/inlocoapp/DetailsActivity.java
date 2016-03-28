package com.example.guilherme.inlocoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guilherme.inlocoapp.model.Weather;

import in.ubee.api.Ubee;
import in.ubee.api.UbeeOptions;
import in.ubee.api.ads.AdType;
import in.ubee.api.ads.AdView;

/*

  a) Nome da cidade escolhida

  b) Temperatura máxima (em Celsius)

  c) Temperatura mínima (em Celsius)

  d) Descrição do tempo (ex: "light rain", "overcast clouds", "Sky is Clear")
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UbeeOptions options = UbeeOptions.getInstance(this);
        options.setAdsKey("1fb579e7a04475a3a4e818111d06717380befbe55443fa33c73768edcd3c8106","4c6fbf109e9a319544f0ea9a5125167f8555f7246db85b086839fb2bae030632");
        options.setLogEnabled(true);
        options.setDevelopmentDevices("43292C85F9C5D8846CF9F423306BD7EF");
        Ubee.init(this, options);
        setContentView(R.layout.activity_details);


        LinearLayout frameLayout = (LinearLayout) findViewById(R.id.linearLayout);
        TextView textView = (TextView) findViewById(R.id.textView);

        Intent i = getIntent();
        Weather weather = (Weather) i.getSerializableExtra("Weather");

        textView.setText("Cidade: "+weather.location.getName()+"\n\nMaxºC: "+String.format("%.2f", weather.temperature.getMaxTempCelsius())+
                           "\n\nMinºC: "+String.format("%.2f", weather.temperature.getMinTempCelsius())+"\n\nDescrição: "+weather.currentCondition.getDescr());

        AdView adView = new AdView(this);
        adView.setType(AdType.DISPLAY_BANNER_SMALL);
        adView.loadAd();
        //parent.addView(adView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        frameLayout.addView(adView, layoutParams);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}