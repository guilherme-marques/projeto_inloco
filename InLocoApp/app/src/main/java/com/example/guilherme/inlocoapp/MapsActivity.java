package com.example.guilherme.inlocoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.guilherme.inlocoapp.model.ListWeather;
import com.example.guilherme.inlocoapp.model.Weather;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng tempLatLng;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        button = (Button) findViewById(R.id.button1);
        button.setText("Buscar");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    public void buttonClicked (View view) {
        if (tempLatLng != null) {
            JSONWeatherTask task = new JSONWeatherTask();
            Log.d("CODIGO", "LATITUDE = " + tempLatLng.latitude + "; LONGITUDE = " + tempLatLng.longitude);
            task.execute(new LatLng[]{tempLatLng});
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub

                tempLatLng = point;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));
            }
        });

    }

    private class JSONWeatherTask extends AsyncTask<LatLng, Void, ListWeather> {

        @Override
        protected ListWeather doInBackground(LatLng... params) {
            ListWeather listWeather = new ListWeather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                if (data != null) {
                    listWeather = JSONWeatherParser.getListWeather(data);
                    Log.d("CODIGO","TESTE JSONWeatherParser.getListWeather(data): "+listWeather.getList().get(0).currentCondition.getDescr());
               } else
                    Log.e("CODIGO", "DATA EH NULL");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listWeather;

        }
        @SuppressLint("NewApi")
        @Override
        protected void onPostExecute(ListWeather listWeather) {
            super.onPostExecute(listWeather);
            Log.d("CODIGO", "TESTE ENVIAR " + listWeather.getList().get(0).currentCondition.getDescr());

            Intent myIntent = new Intent(MapsActivity.this, ListActivity.class);
            myIntent.putExtra("ListWeather", listWeather); //Optional parameters
            MapsActivity.this.startActivity(myIntent);


        }
    }
}