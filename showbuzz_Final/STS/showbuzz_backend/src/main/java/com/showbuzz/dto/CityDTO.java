package com.showbuzz.dto;

public class CityDTO {

	private int city_id;
	private String city_name;
	private String city_state;
	private int city_pincode;
	
	
	public CityDTO() {
		
	}
	
	
	public CityDTO(int city_id, String city_name, String city_state, int city_pincode) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.city_state = city_state;
		this.city_pincode = city_pincode;
	}


	public int getCity_id() {
		return city_id;
	}


	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}


	public String getCity_name() {
		return city_name;
	}


	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}


	public String getCity_state() {
		return city_state;
	}


	public void setCity_state(String city_state) {
		this.city_state = city_state;
	}


	public int getCity_pincode() {
		return city_pincode;
	}


	public void setCity_pincode(int city_pincode) {
		this.city_pincode = city_pincode;
	}


	@Override
	public String toString() {
		return "CityDTO [city_id=" + city_id + ", city_name=" + city_name + ", city_state=" + city_state
				+ ", city_pincode=" + city_pincode + "]";
	}
	
	
	
	
	
}