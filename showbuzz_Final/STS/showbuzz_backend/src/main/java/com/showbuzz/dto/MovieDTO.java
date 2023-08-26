package com.showbuzz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MovieDTO {

	private int id;
	private String movie_name;
	private String movie_details;
	private String category;
	private String language;
	private Date release_date;
	private int status;
	private String movie_poster;
	private String genre;
	private List<MovieRatingDTO> ratings;
	private List<MovieReviewDTO> reviews;
	private List<ShowDTO> showList;
	private List<OrderDTO> orderList;
	
	public MovieDTO() {
		this.ratings = new ArrayList<>();
		this.reviews = new ArrayList<>();
		this.showList = new ArrayList<>();
		this.orderList = new ArrayList<>();
	}

	public MovieDTO(int id, String movie_name, String movie_details, String category, String language,
			Date release_date, int status, String dataFile, String genre) {
		super();
		this.id = id;
		this.movie_name = movie_name;
		this.movie_details = movie_details;
		this.category = category;
		this.language = language;
		this.release_date = release_date;
		this.status = status;
		this.movie_poster = dataFile;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getMovie_details() {
		return movie_details;
	}

	public void setMovie_details(String movie_details) {
		this.movie_details = movie_details;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMoviePoster() {
		return movie_poster;
	}

	public void setMoviePoster(String dataFile) {
		this.movie_poster = dataFile;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<MovieRatingDTO> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRatingDTO> ratings) {
		this.ratings = ratings;
	}

	public List<MovieReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<MovieReviewDTO> reviews) {
		this.reviews = reviews;
	}

	
	
	public List<ShowDTO> getShowList() {
		return showList;
	}

	public void setShowList(List<ShowDTO> showList) {
		this.showList = showList;
	}

	public List<OrderDTO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDTO> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", movie_name=" + movie_name + ", movie_details=" + movie_details + ", category="
				+ category + ", language=" + language + ", release_date=" + release_date + ", status=" + status
				+ ", dataFile=" + movie_poster + ", genre=" + genre + ", ratings=" + ratings + ", reviews=" + reviews
				+ ", showList=" + showList + ", orderList=" + orderList + "]";
	}

	

	
	
	
}
