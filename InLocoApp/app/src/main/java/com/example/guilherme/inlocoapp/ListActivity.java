package com.example.guilherme.inlocoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guilherme.inlocoapp.model.ListWeather;

public class ListActivity extends AppCompatActivity {

    ListView mListView;

    String[] Cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //DO SOMETHING

        final ListWeather listWeather = (ListWeather) getIntent().getSerializableExtra("ListWeather");

        if (listWeather.getList().size() > 0) {
            String description = listWeather.getList().get(0).currentCondition.getDescr();
            Log.d("CODIGO", "RESULTADO: " + description);
        } else{
            Log.d("CODIGO", "RESULTADO: SIZE = 0");
        }



        Cidades = new String[listWeather.getSize()];
        for (int i = 0; i < Cidades.length; i++){
            Cidades[i] = listWeather.getName(i);
            Log.d("CODIGO","LADO RECEBEDOR ITERACAO "+i+" = "+listWeather.getName(i));
        }



        mListView = (ListView) findViewById(R.id.list);

        //Declaring Array adapter
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Cidades);

        //Setting the android ListView's adapter to the newly created adapter
        mListView.setAdapter(countryAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //A posição onde o item da lista eh clicado é passado como parâmetro para esse método (position)
                int itemPosition = position;

                String itemValue = (String) mListView.getItemAtPosition(position);

                //COMEÇA A NOVA ACTIVITY COM OS DETALHES
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                //Colocamos os detalhes do clima da cidade clicada como um extra
                intent.putExtra("Weather", listWeather.get(position));

                startActivity(intent);

            }
        });







    }

}
