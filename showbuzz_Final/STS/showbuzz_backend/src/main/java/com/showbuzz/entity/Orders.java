package com.showbuzz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "orders")
public class Orders {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "order_id")
	private int id;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screen_id")
	private Screen screen;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id")
	private Shows shows;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="booking_id")
	private SeatBooking booking;
	
	@Temporal(TemporalType.DATE)
	private Date show_date;
	
	@Temporal(TemporalType.TIME)
	private Date show_time;
	
	public Orders() {

	}
	
	public Orders(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Shows getShow() {
		return shows;
	}

	public void setShow(Shows show) {
		this.shows = show;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Date getShow_date() {
		return show_date;
	}

	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}

	public Date getShow_time() {
		return show_time;
	}

	public void setShow_time(Date show_time) {
		this.show_time = show_time;
	}
	
	
	

	public SeatBooking getBooking() {
		return booking;
	}

	public void setBooking(SeatBooking booking) {
		this.booking = booking;
	}

	
	
	
	@Override
	public String toString() {
		return "Orders [id=" + id + ", user=" + user + ", payment=" + payment + ", movie=" + movie + ", theatre="
				+ theatre + ", screen=" + screen + ", show=" + shows + ", seat=" + seat + ", show_date=" + show_date
				+ ", show_time=" + show_time + "]";
	}

	

	
}
