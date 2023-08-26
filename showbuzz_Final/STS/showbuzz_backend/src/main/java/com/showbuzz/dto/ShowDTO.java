package com.showbuzz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.showbuzz.entity.Orders;

public class ShowDTO {

	private int id;
	private int movie_id;
	private int screen_id;
	private float cost_factor;
	private Date showdate;
	private String showtime;
	private List<Orders> orderList;
	
	
	public ShowDTO() {
		this.orderList = new ArrayList<Orders>();
	}
	
	public ShowDTO(int id, int movie_id, int screen_id, float cost_factor, Date showdate, String showtime) {
		this.id = id;
		this.movie_id = movie_id;
		this.screen_id = screen_id;
		this.cost_factor = cost_factor;
		this.showdate = showdate;
		this.showtime = showtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public float getCost_factor() {
		return cost_factor;
	}
	public void setCost_factor(float cost_factor) {
		this.cost_factor = cost_factor;
	}
	public Date getShowdate() {
		return showdate;
	}
	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	
	
	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "ShowDTO [id=" + id + ", movie_id=" + movie_id + ", screen_id=" + screen_id + ", cost_factor="
				+ cost_factor + ", showdate=" + showdate + ", showtime=" + showtime + ", orderList=" + orderList + "]";
	}

	
	

	
	
	
	
}
