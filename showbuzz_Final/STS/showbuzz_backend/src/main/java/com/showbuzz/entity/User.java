package com.showbuzz.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_no;
	private String password;
	private String address_line;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String role;
	private String gender;
	
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private Theatre theatre;
	@JsonProperty("seat_booking")
	@JsonIgnore
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<SeatBooking> bookingList;
	@JsonProperty("payment")
	@JsonIgnore
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Payment> paymentList;
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Orders> orderList;
	@JsonProperty("movie_rating")
	@JsonIgnore
	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<MovieRating> ratingList;
	@JsonProperty("movie_review")
	@JsonIgnore
	@OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<MovieReview> reviewList;
	
	public User() {
		this.orderList = new ArrayList<>();
		this.paymentList = new ArrayList<>();
		this.bookingList = new ArrayList<>();
		this.ratingList = new ArrayList<>();
		this.reviewList = new ArrayList<>();
	}

	
	public User(int id, String first_name, String last_name, String email, String phone_no, String password,
			String address_line, String city, String state, String country, int pincode,
			String role, String gender) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_no = phone_no;
		this.password = password;
		this.address_line = address_line;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.role = role;
		this.gender = gender;
	}


	public User(int user_id) {
		this.id = user_id;
	}


	public int getUser_id() {
		return id;
	}

	public void setUser_id(int user_id) {
		this.id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress_line() {
		return address_line;
	}

	public void setAddress_line(String address_line1) {
		this.address_line = address_line1;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	
	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public List<Orders> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setUser(this);		
	}
	
	
	public List<Payment> getPaymentList() {
		return paymentList;
	}


	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}


	public Theatre getTheatre() {
		return theatre;
	}


	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	
	public List<SeatBooking> getBookingList() {
		return bookingList;
	}
	
	
	public void setBookingList(List<SeatBooking> bookingList) {
		this.bookingList = bookingList;
	}
	
	

	public List<MovieRating> getRatingList() {
		return ratingList;
	}
	
	
	public void setRatingList(List<MovieRating> ratingList) {
		this.ratingList = ratingList;
	}
	
	
	public List<MovieReview> getReviewList() {
		return reviewList;
	}
	
	
	public void setReviewList(List<MovieReview> reviewList) {
		this.reviewList = reviewList;
	}

	/*	@OneToMany	List Methods 	*/
	
	public void addTheatreUser(Theatre theatre) {
//		this.theatre.setUser(this);
		theatre.setUser(this);
	}

	public void addUserPayment(Payment payment) {
		this.paymentList.add(payment);
		payment.setUser(this);
	}
	
	public void addUserOrder(Orders orders) {
		this.orderList.add(orders);
		orders.setUser(this);
	}
	public void addUserRating(MovieRating rating) {
		this.ratingList.add(rating);
		rating.setUser(this);
	}
	public void addUserReview(MovieReview review) {
		this.reviewList.add(review);
		review.setUser(this);
	}
	
	public void addUserSeatBooking(SeatBooking booking) {
		this.bookingList.add(booking);
		booking.setUser(this);
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone_no=" + phone_no + ", password=" + password + ", address_line=" + address_line + ", city="
				+ city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + ", role=" + role
				+ ", gender=" + gender + ", theatre=" + theatre + ", bookingList=" + bookingList + ", paymentList="
				+ paymentList + ", orderList=" + orderList + "]";
	}


//	@Override
//	public int hashCode() {
//		return Objects.hash(address_line, bookingList, city, country, email, first_name, gender, id, last_name,
//				orderList, password, paymentList, phone_no, pincode, ratingList, reviewList, role, state, theatre);
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		return Objects.equals(address_line, other.address_line) && Objects.equals(bookingList, other.bookingList)
//				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
//				&& Objects.equals(email, other.email) && Objects.equals(first_name, other.first_name)
//				&& Objects.equals(gender, other.gender) && id == other.id && Objects.equals(last_name, other.last_name)
//				&& Objects.equals(orderList, other.orderList) && Objects.equals(password, other.password)
//				&& Objects.equals(paymentList, other.paymentList) && Objects.equals(phone_no, other.phone_no)
//				&& pincode == other.pincode && Objects.equals(ratingList, other.ratingList)
//				&& Objects.equals(reviewList, other.reviewList) && Objects.equals(role, other.role)
//				&& Objects.equals(state, other.state) && Objects.equals(theatre, other.theatre);
//	}
	
	

	

	
	


	

	
	
}

