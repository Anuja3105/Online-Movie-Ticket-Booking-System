package com.showbuzz.dto;

public class SeatDTO {

	private String seat_id;
	private String seat_type;
	private float price;
	
	public SeatDTO() {
	}

	public SeatDTO(String seat_id, String seat_type, int price) {
		this.seat_id = seat_id;
		this.seat_type = seat_type;
		this.price = price;
	
	}

	public String getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
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

	public void setPrice(float f) {
		this.price = f;
	}

	

	@Override
	public String toString() {
		return "SeatDTO [seat_id=" + seat_id + ", seat_type=" + seat_type + ", price=" + price+"]" ;
	}

	
	
	
}
