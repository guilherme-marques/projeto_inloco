package com.example.guilherme.inlocoapp.model;

import java.io.Serializable;


public class Weather implements Serializable {
	
	public Location location;
	public CurrentCondition currentCondition = new CurrentCondition();
	public Temperature temperature = new Temperature();


	public  class CurrentCondition implements Serializable {
		private int weatherId;
		private String condition;
		private String descr;
		

		
		public int getWeatherId() {
			return weatherId;
		}
		public void setWeatherId(int weatherId) {
			this.weatherId = weatherId;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public String getDescr() {
			return descr;
		}
		public void setDescr(String descr) {
			this.descr = descr;
		}
		
		
	}
	
	public  class Temperature implements Serializable {
		private float temp;
		private float minTemp;
		private float maxTemp;
		
		public float getTemp() {
			return temp;
		}
		public void setTemp(float temp) {
			this.temp = temp;
		}
		public float getMinTemp() {
			return minTemp;
		}
		public void setMinTemp(float minTemp) {
			this.minTemp = minTemp;
		}
		public float getMaxTemp() {
			return maxTemp;
		}
		public void setMaxTemp(float maxTemp) {
			this.maxTemp = maxTemp;
		}
		public float getMaxTempCelsius() {
			return maxTemp-273.15f;
		}
		public float getMinTempCelsius() {
			return minTemp-273.15f;
		}
	}
	


}
