package com.showbuzz.dto;

import java.util.ArrayList;
import java.util.List;

public class TheatreDTO {

	private int theatre_id;
	private String name;
	private int city_id;
	private String address;
	private int no_of_screen;
	private String key1;
	private String key2;
	private int manager_id;
	private List<ScreenDTO> screenList;
	private List<OrderDTO> orderList;
	
	public TheatreDTO() {
		this.screenList = new ArrayList<>();
		this.orderList = new ArrayList<>();
		
	}

	

	public TheatreDTO(int theatre_id, String name, int city_id, String address, int no_of_screen, String key1,
			String key2, int manager_id) {
		this.theatre_id = theatre_id;
		this.name = name;
		this.city_id = city_id;
		this.address = address;
		this.no_of_screen = no_of_screen;
		this.key1 = key1;
		this.key2 = key2;
		this.manager_id = manager_id;
	}



	public int getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city) {
		this.city_id = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNo_of_screen() {
		return no_of_screen;
	}

	public void setNo_of_screen(int no_of_screen) {
		this.no_of_screen = no_of_screen;
	}

	
	public String getKey1() {
		return key1;
	}



	public void setKey1(String key1) {
		this.key1 = key1;
	}



	public String getKey2() {
		return key2;
	}



	public void setKey2(String key2) {
		this.key2 = key2;
	}



	public int getManager_id() {
		return manager_id;
	}



	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	


	public List<ScreenDTO> getScreenList() {
		return screenList;
	}



	public void setScreenList(List<ScreenDTO> screenList) {
		this.screenList = screenList;
	}



	public List<OrderDTO> getOrderList() {
		return orderList;
	}



	public void setOrderList(List<OrderDTO> orderList) {
		this.orderList = orderList;
	}



	@Override
	public String toString() {
		return "TheatreDTO [theatre_id=" + theatre_id + ", name=" + name + ", city=" + city_id + ", address=" + address
				+ ", no_of_screen=" + no_of_screen + ", key1=" + key1 + ", key2=" + key2 + ", manager_id=" + manager_id
				+ "]";
	}



	
	

}