package com.showbuzz.dto;

public class MovieRatingDTO {

	private int id;
	private int movie_id;
	private int user_id;
	private float rating;
	private String firstName;
	private String lastName;;
	private String movieName;
	
	public MovieRatingDTO() {
		
	}

	public MovieRatingDTO(int id, int movie_id, int user_id, float rating, String firstName, String lastName,
			String movieName) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.user_id = user_id;
		this.rating = rating;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "MovieRatingDTO [id=" + id + ", movie_id=" + movie_id + ", user_id=" + user_id + ", rating=" + rating
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", movieName=" + movieName + "]";
	}
	
	
}
