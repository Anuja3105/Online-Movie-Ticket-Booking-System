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
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="city_id")
	int id;
	@Column(name="city_name")
	String name;
	@Column(name="city_state")
	String state;
	@Column(name="city_pincode")
	int pincode;
	@JsonProperty("theatre")
	@JsonIgnore
	@OneToMany(mappedBy = "city" ,fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Theatre> theatreList;
	
	public City() {
		this.theatreList = new ArrayList<Theatre>();
	}

	public City(int id, String name, String state, int pincode) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.pincode = pincode;
	}

	public City(int city_id) {
		this.id = city_id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	
	public List<Theatre> getTheatreList() {
		return theatreList;
	}

	public void setTheatreList(List<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

	/*	@OneToMany	*/
	public void addTheatre(Theatre theatre) {
		this.theatreList.add(theatre);
		theatre.setCity(this);
	}
	
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	

}