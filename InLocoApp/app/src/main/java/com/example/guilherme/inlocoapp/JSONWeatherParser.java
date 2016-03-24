package com.example.guilherme.inlocoapp;

import android.util.Log;

import com.example.guilherme.inlocoapp.model.ListWeather;
import com.example.guilherme.inlocoapp.model.Weather;
import com.example.guilherme.inlocoapp.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONWeatherParser {

	public static ListWeather getListWeather(String data) throws JSONException {
		ListWeather listWeather = new ListWeather();
		Weather weather;


		Log.d("CODIGO", "INSIDE JSONWeatherParser constructor"+ data);

		// Cria um JSONObject com os dados
		JSONObject jObj = new JSONObject(data);

		JSONArray listArr = jObj.getJSONArray("list");
		Log.d("CODIGO", "listArr.length() = "+listArr.length());

		for(int i = 0; i<listArr.length(); i++) {
			try {
				weather = new Weather();

				//** VAMO FAZER ISSO AQUI 15x **//

				//PEGA O PRIMEIRO (OU PROXIMO DA LISTA)
				JSONObject jObjSub = listArr.getJSONObject(i);

				// Começamos a extrair a info
				Location loc = new Location();

				//SETA O NAME
				loc.setName(getString("name", jObjSub));

				JSONObject coordObj = getObject("coord", jObjSub);
				loc.setLatitude(getFloat("lat", coordObj));
				loc.setLongitude(getFloat("lon", coordObj));


				weather.location = loc;

				JSONArray jArr = jObjSub.getJSONArray("weather");

				JSONObject JSONWeather = jArr.getJSONObject(0);
				weather.currentCondition.setDescr(getString("description", JSONWeather));

				JSONObject mainObj = getObject("main", jObjSub);
				weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
				weather.temperature.setMinTemp(getFloat("temp_min", mainObj));




				//NO FINAL TEM QUE ADICIONAR O WEATHER EM UMA LISTA:
				listWeather.addToList(weather);

				Log.d("CODIGO", "ITERACAO " + i + " cidade = " + weather.location.getName());
			} catch(JSONException e){
				e.printStackTrace();
			}
			//***** FIM ******//
		}
		return listWeather;
	}
	
	
	private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
		JSONObject subObj = jObj.getJSONObject(tagName);
		return subObj;
	}
	
	private static String getString(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getString(tagName);
	}

	private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
		return (float) jObj.getDouble(tagName);
	}
	
	private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
		return jObj.getInt(tagName);
	}
	
}
