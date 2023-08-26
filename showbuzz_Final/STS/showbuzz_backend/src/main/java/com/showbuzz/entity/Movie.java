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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movie")
public class Movie {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "movie_id")
	private int id;
	@Column(name="movie_name")
	private String name;
	@Column(name="movie_details")
	private String movie_details;
	private String category;
	private String language;
	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;
	private int status;
	
	private String movie_poster;
	private String genre;
	@JsonProperty("movie_rating")
	@JsonIgnore
	@OneToMany(mappedBy= "movie",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<MovieRating> ratingList;
	@JsonProperty("movie_review")
	@JsonIgnore
	@OneToMany(mappedBy= "movie",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<MovieReview> reviewList;
	@JsonProperty("shows")
	@JsonIgnore
	@OneToMany(mappedBy= "movie",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Shows> showList;
	@JsonProperty("orders")
	@JsonIgnore
	@OneToMany(mappedBy= "movie",fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Orders> orderList;
	
	public Movie() {
		this.ratingList = new ArrayList<>();
		this.reviewList = new ArrayList<>();
		this.showList = new ArrayList<>();
	}

	public Movie(int id) {
		this.id = id;
	}

	public Movie(int id, String name, String movie_details, String category, String language, Date releaseDate,
			int status, String movie_poster, String genre, List<MovieRating> ratingList, List<MovieReview> reviewList) {
		super();
		this.id = id;
		this.name = name;
		this.movie_details = movie_details;
		this.category = category;
		this.language = language;
		this.releaseDate = releaseDate;
		this.status = status;
		this.movie_poster = movie_poster;
		this.genre = genre;
		this.ratingList = ratingList;
		this.reviewList = reviewList;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMovie_poster() {
		return movie_poster;
	}

	public void setMovie_poster(String movie_poster) {
		this.movie_poster = movie_poster;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
	
	

	public List<Shows> getShowList() {
		return showList;
	}

	public void setShowList(List<Shows> showList) {
		this.showList = showList;
	}

	
	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	//only for OneT0Many this function is use  rating-------------
	public void addMovieRating(MovieRating rating) {
				this.ratingList.add(rating);
				rating.setMovie(this);
	}
	
	//only for OneT0Many this function is use review-------------
	public void addMovieReview(MovieReview review) {
				this.reviewList.add(review);
				review.setMovie(this);
	}
	public void addMovieOrders(Orders order) {
		this.orderList.add(order);
		order.setMovie(this);
	}
	public void addShows(Shows show) {
		this.showList.add(show);
		show.setMovie(this);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", movie_details=" + movie_details + ", category=" + category
				+ ", language=" + language + ", releaseDate=" + releaseDate + ", status=" + status + ", movie_poster="
				+ movie_poster + ", genre=" + genre + ", ratingList=" + ratingList + ", reviewList="
				+ reviewList + ", showList=" + showList + ", orderList=" + orderList + "]";
	}

	

	
}//-p1999
