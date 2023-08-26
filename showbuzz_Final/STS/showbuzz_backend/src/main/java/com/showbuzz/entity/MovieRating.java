package com.showbuzz.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movie_rating")
public class MovieRating {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "movie_rating_id")
	private int id;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "movie_id")
	private Movie movie;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	private float rating;
	
	public MovieRating() {

	}
	
	public MovieRating(float rating) {
		super();
		this.rating = rating;
	}
	
	public MovieRating(int id, float rating) {
		super();
		this.id = id;
		this.rating = rating;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "MovieRating [id=" + id +", Movie=" + movie + ", User=" + user + ", rating=" + rating + "]";
	}
	
}//-p1999
