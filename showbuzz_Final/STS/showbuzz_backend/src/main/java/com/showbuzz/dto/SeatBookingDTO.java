package com.showbuzz.dto;

import java.util.Date;

public class SeatBookingDTO {

	private int booking_id;
	private int show_id;
	private String seat_id;
	private int user_id;
	private String booking_date;
	private Date created_timestamp;
	private String order_id;
	private int payment_status;
	
	public SeatBookingDTO() {
	}

	public SeatBookingDTO(int booking_id, int show_id, String seat_id, int user_id, String booking_date,
			Date created_timestamp,String order_id, int payment_status) {
		this.booking_id = booking_id;
		this.show_id = show_id;
		this.seat_id = seat_id;
		this.user_id = user_id;
		this.booking_date = booking_date;
		this.created_timestamp = created_timestamp;
		this.order_id = order_id;
		this.payment_status = payment_status;
	}




	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(Date created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public String getBooking_date() {
		return booking_date;
	}


	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "SeatBookingDTO [booking_id=" + booking_id + ", show_id=" + show_id + ", seat_id=" + seat_id
				+ ", user_id=" + user_id + ", booking_date=" + booking_date + ", created_timestamp=" + created_timestamp
				+ ", order_id=" + order_id + ", payment_status=" + payment_status + "]";
	}

	
	

	
	
}
