package com.showbuzz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movie_review")
public class MovieReview {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "movie_reveiw_id")
	private int id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String review;
	
	
	
	public MovieReview(int id) {
		this.id = id;
	}

	public MovieReview() {
		
	}

	public MovieReview(int id, String review) {
		super();
		this.id = id;
		this.review = review;
	}
	
	

	public MovieReview(String review) {
		this.review = review;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	@Override
	public String toString() {
		return "MovieRating [id=" + id +", Movie=" + movie + ", User=" + user + ", Review=" + review + "]";
	}
	
}
