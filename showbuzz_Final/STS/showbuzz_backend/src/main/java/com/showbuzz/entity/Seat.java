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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "seat")
public class Seat {
//	seat_id
//	seat_type
//	price
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "seat_id")
	private String id;
	private String seat_type;
	private float price;
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy = "seat", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Orders> orderList;
	
	public Seat() {
		this.orderList = new ArrayList<Orders>();
	}


	public Seat(String id) {
	this.id = id;
	}




	public Seat(String id, String seat_type, float price) {
		this.id = id;
		this.seat_type = seat_type;
		this.price = price;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSeat_type() {
		return seat_type;
	}


	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public List<Orders> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setSeat(this);
	}
	

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seat_type=" + seat_type + ", price=" + price + ", orderList="
				+ orderList + "]";
	}


	


	
	
	
}
