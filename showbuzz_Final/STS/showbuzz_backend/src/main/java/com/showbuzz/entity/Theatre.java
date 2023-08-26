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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="theatre")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="theatre_id")
	private int id;
	
	private String name;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;
	
	private String address;
	
	@Column(name="no_of_screen")
	private int noOfScreen;
	
	@Column(name="key1")
	private String key1;

	@Column(name="key2")
	private String key2;
	
	@OneToOne(cascade = {CascadeType.REMOVE})
	@JoinColumn(name = "user_id")
	private User user;

	@JsonProperty("screen")
	@JsonIgnore
	@OneToMany(mappedBy = "theatre" , fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Screen> listOfScreen;
	
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy = "theatre" , fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	private List<Orders> orderList;
	
	public Theatre() {
		this.listOfScreen = new ArrayList<>();
		this.orderList = new ArrayList<>();
	}


	public Theatre(int id) {
		this.id = id;
	}

	public Theatre(int id, String name, City city, String address, int noOfScreen, String key1, String key2) 
	{
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.noOfScreen = noOfScreen;
		this.key1 = key1;
		this.key2 = key2;
	}


	

	

	public Theatre(String name, String address, int noOfScreen, String key1, String key2) {
		this.name = name;
		this.address = address;
		this.noOfScreen = noOfScreen;
		this.key1 = key1;
		this.key2 = key2;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getNoOfScreen() {
		return noOfScreen;
	}


	public void setNoOfScreen(int noOfScreen) {
		this.noOfScreen = noOfScreen;
	}




	public String getKey1() {
		return key1;
	}


	public void setKey1(String key1) {
		this.key1 = key1;
	}


	public String getKey2() {
		return key2;
	}


	public void setKey2(String key2) {
		this.key2 = key2;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
	public List<Screen> getListOfScreen() {
		return listOfScreen;
	}


	public void setListOfScreen(List<Screen> listOfScreen) {
		this.listOfScreen = listOfScreen;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}
	
	
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	/*	@OneToMany	*/
	
	public void addScreen(Screen screen) {
		this.listOfScreen.add(screen);
		screen.setTheatre(this);
	}

	public void addOrder(Orders order) {
		this.orderList.add(order);
		order.setTheatre(this);
	}

	
	


	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", noOfScreen="
				+ noOfScreen + ", key1=" + key1 + ", key2=" + key2 + ", user=" + user + ", listOfScreen=" + listOfScreen
				+ "]";
	}


//	@Override
//	public int hashCode() {
//		return Objects.hash(address, city, id, key1, key2, listOfScreen, name, noOfScreen, orderList, user);
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
//		Theatre other = (Theatre) obj;
//		return Objects.equals(address, other.address) && Objects.equals(city, other.city) && id == other.id
//				&& Objects.equals(key1, other.key1) && Objects.equals(key2, other.key2)
//				&& Objects.equals(listOfScreen, other.listOfScreen) && Objects.equals(name, other.name)
//				&& noOfScreen == other.noOfScreen && Objects.equals(orderList, other.orderList)
//				&& Objects.equals(user, other.user);
//	}


	
	


	

	

	
}