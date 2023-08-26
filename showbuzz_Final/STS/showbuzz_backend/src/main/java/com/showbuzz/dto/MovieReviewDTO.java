package com.showbuzz.dto;


public class MovieReviewDTO {


	private int id;
	private int movie_id;
	private int user_id;
	private String review;
	private String firstName;
	private String lastName;
	private String movieName;
	
	public MovieReviewDTO() {
		
	}

	public MovieReviewDTO(int id, int movie_id, int user_id, String review, String firstName, String lastName,
			String movieName) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.user_id = user_id;
		this.review = review;
		this.firstName = firstName;
		this.lastName = lastName;
		this.movieName = movieName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public String toString() {
		return "MovieReviewDTO [id=" + id + ", movie_id=" + movie_id + ", user_id=" + user_id + ", review=" + review
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", movieName=" + movieName + "]";
	}

	
	
}
