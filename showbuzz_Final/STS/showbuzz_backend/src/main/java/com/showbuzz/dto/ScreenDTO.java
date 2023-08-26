package com.showbuzz.dto;

import java.util.ArrayList;
import java.util.List;

public class ScreenDTO {

	private int id;
	private int screen_no;
	private int theatre_id;
	private List<ShowDTO> showList;
//	private List<Orders> orderList;
	
	public ScreenDTO() {
		this.showList = new ArrayList<ShowDTO>();
		//this.orderList = new ArrayList<Orders>();
		
	}

	public ScreenDTO(int id, int screen_no, int theatre_id) {
		this.id = id;
		this.screen_no = screen_no;
		this.theatre_id = theatre_id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScreen_no() {
		return screen_no;
	}

	public void setScreen_no(int screen_no) {
		this.screen_no = screen_no;
	}

	public int getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}


	public List<ShowDTO> getShowList() {
		return showList;
	}

	public void setShowList(List<ShowDTO> showList) {
		this.showList = showList;
	}

	@Override
	public String toString() {
		return "ScreenDTO [id=" + id + ", screen_no=" + screen_no + ", theatre_id=" + theatre_id + ", showList="
				+ showList + "]";
	}

	

	
	
	
}
