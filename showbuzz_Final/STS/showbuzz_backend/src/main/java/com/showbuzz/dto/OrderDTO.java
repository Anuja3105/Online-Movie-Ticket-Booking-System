package com.showbuzz.dto;

import java.util.Date;

public class OrderDTO {

	private int order_id;
	private int user_id;
	private int payment_id;
	private int movie_id;
	private int theatre_id;
	private int screen_id;
	private int show_id;
	private int booking_id;
	private String seat_id;
	private Date show_date;
	private Date show_time;

	

	public OrderDTO() {
		super();
	}

	public OrderDTO(int order_id, int user_id, int payment_id, int movie_id, int theatre_id, int screen_id, int show_id,
			int booking_id, String seat_id, Date show_date, Date show_time) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.payment_id = payment_id;
		this.movie_id = movie_id;
		this.theatre_id = theatre_id;
		this.screen_id = screen_id;
		this.show_id = show_id;
		this.booking_id = booking_id;
		this.seat_id = seat_id;
		this.show_date = show_date;
		this.show_time = show_time;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public String getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
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

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	@Override
	public String toString() {
		return "OrderDTO [order_id=" + order_id + ", user_id=" + user_id + ", payment_id=" + payment_id + ", movie_id="
				+ movie_id + ", theatre_id=" + theatre_id + ", screen_id=" + screen_id + ", show_id=" + show_id
				+ ", booking_id=" + booking_id + ", seat_id=" + seat_id + ", show_date=" + show_date + ", show_time="
				+ show_time + "]";
	}

	
}
