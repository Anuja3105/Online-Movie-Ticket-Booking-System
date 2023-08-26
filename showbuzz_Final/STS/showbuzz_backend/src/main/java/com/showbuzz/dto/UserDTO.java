package com.showbuzz.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private int user_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String phone_no;
	private String address_line;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String gender;
	private String role;
	private List<OrderDTO> orderList;
	private List<SeatBookingDTO> bookingList;
	private List<MovieRatingDTO> ratingList;
	private List<MovieReviewDTO> reviewList;
	
	public UserDTO() {
		this.orderList  = new ArrayList<>();
		this.bookingList  = new ArrayList<>();
		this.reviewList  = new ArrayList<>();
		this.ratingList  = new ArrayList<>();
	}


	
	public UserDTO(int user_id, String first_name, String last_name, String email, String password) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}


	public UserDTO(int user_id, String first_name, String last_name, String email, String password, String phone_no,
			String address_line, String city, String state, String country, int pincode, String gender) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
		this.address_line = address_line;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.gender = gender;
	}



	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getPhone_no() {
		return phone_no;
	}



	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}



	public String getAddress_line() {
		return address_line;
	}



	public void setAddress_line(String address_line) {
		this.address_line = address_line;
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



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public List<OrderDTO> getOrderList() {
		return orderList;
	}



	public void setOrderList(List<OrderDTO> orderList) {
		this.orderList = orderList;
	}



	public List<SeatBookingDTO> getBookingList() {
		return bookingList;
	}



	public void setBookingList(List<SeatBookingDTO> bookingList) {
		this.bookingList = bookingList;
	}

	


	public List<MovieRatingDTO> getRatingList() {
		return ratingList;
	}



	public void setRatingList(List<MovieRatingDTO> ratingList) {
		this.ratingList = ratingList;
	}



	public List<MovieReviewDTO> getReviewList() {
		return reviewList;
	}



	public void setReviewList(List<MovieReviewDTO> reviewList) {
		this.reviewList = reviewList;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", password=" + password + ", phone_no=" + phone_no + ", address_line=" + address_line
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + ", gender="
				+ gender + ", role=" + role + ", orderList=" + orderList + ", bookingList=" + bookingList
				+ ", ratingList=" + ratingList + ", reviewList=" + reviewList + "]";
	}



	



	
	
	
}
