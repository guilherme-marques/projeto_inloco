package com.example.guilherme.inlocoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilherme.inlocoapp.model.Weather;

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
        setContentView(R.layout.activity_details);
        TextView textView = (TextView) findViewById(R.id.textView);

        Intent i = getIntent();
        Weather weather = (Weather) i.getSerializableExtra("Weather");

        textView.setText("Cidade: "+weather.location.getName()+"\n\nMaxºC: "+String.format("%.2f", weather.temperature.getMaxTempCelsius())+
                           "\n\nMinºC: "+String.format("%.2f", weather.temperature.getMinTempCelsius())+"\n\nDescrição: "+weather.currentCondition.getDescr());


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