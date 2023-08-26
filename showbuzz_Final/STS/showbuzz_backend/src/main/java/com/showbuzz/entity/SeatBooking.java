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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "Seat_Booking")
public class SeatBooking {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "booking_id")
	private int id;

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
	@JoinColumn(name = "user_id")
	private User user;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date booking_date;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_timestamp;
	
	private String order_id;
	/*	0/1	*/
	private int payment_status;
	
	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = { CascadeType.ALL})
	private List<Orders> orderList;
	/*	Constructors 	*/
	public SeatBooking() {
		orderList = new ArrayList<>();
	}
	public SeatBooking(int id) {
		this.id = id;
	}

	public SeatBooking(int id, Date booking_date, Date created_timestamp, String order_id, int payment_status) {
		this.id = id;
		this.booking_date = booking_date;
		this.created_timestamp = created_timestamp;
		this.order_id = order_id;
		this.payment_status = payment_status;
	}
	/*	Getter Setter	*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	

	public Date getBooking_date() {
		return booking_date;
	}
	
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	
	
	public List<Orders> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setBooking(this);
	}
	
	@Override
	public String toString() {
		return "SeatBooking [id=" + id + ", show=" + shows + ", seat=" + seat + ", user=" + user + ", booking_date="
				+ booking_date + ", created_timestamp=" + created_timestamp + ", order_id=" + order_id
				+ ", payment_status=" + payment_status + "]";
	}
	
	
	
	
	
	
	
	






}
