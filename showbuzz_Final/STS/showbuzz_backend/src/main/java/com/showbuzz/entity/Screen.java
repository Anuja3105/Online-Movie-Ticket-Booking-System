package com.showbuzz.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "screen")
public class Screen {
//	screen_id
//	screen_no
//	theatre_id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "screen_id")
	private int id;
	private int screen_no;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;

	
	@JsonProperty("shows")
	@JsonIgnore
	@OneToMany(mappedBy = "screen", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Shows> listOfShows;
	
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy = "screen")
	private List<Orders> orderList;
	
	
	
	
	/*Constructors*/
	public Screen() {
		this.listOfShows = new ArrayList<Shows>();
//		this.orderList = new ArrayList<Orders>();
	}
	
	public Screen(int screen_id, int screen_no) {
		this.id = screen_id;
		this.screen_no = screen_no;
	}

	public Screen(int screen_id) {
		this.id = screen_id;
	}

	public int getScreen_id() {
		return id;
	}

	public void setScreen_id(int screen_id) {
		this.id = screen_id;
	}

	public int getScreen_no() {
		return screen_no;
	}

	public void setScreen_no(int screen_no) {
		this.screen_no = screen_no;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public List<Shows> getListOfShows() {
		return listOfShows;
	}

	public void setListOfShows(List<Shows> listOfShows) {
		this.listOfShows = listOfShows;
	}

	
	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	/*	@OneToMany	*/	
	
	public void addShow(Shows show) {
		this.listOfShows.add(show);
		show.setScreen(this);
	}

	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setScreen(this);
	}
	
	
	
	
	@Override
	public String toString() {
		return "Screen [id=" + id + ", screen_no=" + screen_no + ", theatre=" + theatre + ", listOfSeats=" + listOfShows
				+ ", orderList=" + /*orderList +*/ "]";
	}

	
	
	
	
	
}
