package com.showbuzz.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "shows")

public class Shows {
//	show_id
//	movie_id
//	screen_id
//	cost_factor
//	showdate
//	showtime
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "show_id")
	private int id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screen_id")
	private Screen screen;
	
	private float cost_factor;
	
	@Column(name = "showdate")
	@Temporal(TemporalType.DATE)
	private Date showdate;

	@Column(name = "showtime")
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "'T'HH:mm:ss" ,shape = JsonFormat.Shape.STRING)
	private Date showtime;
	
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy = "shows",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Orders> orderList;

	@JsonProperty("seat_booking")
	@JsonIgnore
	@OneToMany(mappedBy = "shows" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<SeatBooking> bookingList;
	
	/*	Constructors	*/
	public Shows() {
		this.orderList = new ArrayList<Orders>();
		this.bookingList = new ArrayList<SeatBooking>();
	}


	

	public void setScreen(int screen_id) {
		this.id = screen_id;
	}

	public Shows(int id, Movie movie, float cost_factor, Date showdate, Date showtime) {
		this.id = id;
		this.movie = movie;
		this.cost_factor = cost_factor;
		this.showdate = showdate;
		this.showtime = showtime;
	}





	public float getCost_factor() {
		return cost_factor;
	}





	public void setCost_factor(float cost_factor) {
		this.cost_factor = cost_factor;
	}





	public Shows(int show_id) {
		this.id = show_id;
	}


	public int getShow_id() {
		return id;
	}


	public void setShow_id(int show_id) {
		this.id = show_id;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Screen getScreen() {
		return screen;
	}


	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	public Date getShowdate() {
		return showdate;
	}


	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}


	public Date getShowtime() {
		return showtime;
	}


	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	
	public List<SeatBooking> getBookingList() {
		return bookingList;
	}





	public void setBookingList(List<SeatBooking> bookingList) {
		this.bookingList = bookingList;
	}





	public List<Orders> getOrderList() {
		return orderList;
	}
	
	
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	
	/*	@OneToMany	*/
	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setShow(this);
	}
	
	public void addSeatBooking(SeatBooking booking) {
		this.bookingList.add(booking);
		booking.setShow(this);
	}





	@Override
	public String toString() {
		return "Shows [id=" + id + ", movie=" + movie + ", screen=" + screen + ", cost_factor=" + cost_factor
				+ ", showdate=" + showdate + ", showtime=" + showtime + ", orderList=" + orderList + ", bookingList="
				+ bookingList + "]";
	}





	
	


}
