/**
 * This is a tutorial source code 
 * provided "as is" and without warranties.
 *
 * For any question please visit the web site
 * http://www.survivingwithandroid.com
 *
 * or write an email to
 * survivingwithandroid@gmail.com
 *
 */
package com.example.guilherme.inlocoapp;

import android.util.Log;

import com.example.guilherme.inlocoapp.model.Weather;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class WeatherHttpClient {

	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static String IMG_URL = "http://openweathermap.org/img/w/";
	private static String TESTE = "http://api.openweathermap.org/data/2.5/find?lat={-34}&lon={151}&APPID=d5e5e7bf0036493556227d17d41219bd";


	public String getWeatherData(LatLng latlong) {
		String LAT = ""+latlong.latitude;
		String LON = ""+latlong.longitude;
		String result = "";
		HttpURLConnection conn = null;
		try {
			//URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+location+"&APPID=ea574594b9d36ab688642d5fbeab847e");
			URL url = new URL("http://api.openweathermap.org/data/2.1/find/city?lat="+LAT+"&lon="+LON+"&cnt=15&APPID=d5e5e7bf0036493556227d17d41219bd");

			Log.d("CODIGO", "URL = "+url.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(conn.getInputStream());
			if (in != null) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
				String line = "";

				while ((line = bufferedReader.readLine()) != null) {
					Log.d("CODIGO","TESTE = "+line);
					result += line;
				}
			}
			in.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn!=null)
				conn.disconnect();
		}
		return result;
	}


	public byte[] getImage(String code) {
		HttpURLConnection con = null ;
		InputStream is = null;
		try {
			con = (HttpURLConnection) ( new URL(IMG_URL + code)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			// Let's read the response
			is = con.getInputStream();
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ( is.read(buffer) != -1)
				baos.write(buffer);

			return baos.toByteArray();
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
		finally {
			try { is.close(); } catch(Throwable t) {}
			try { con.disconnect(); } catch(Throwable t) {}
		}

		return null;

	}
}