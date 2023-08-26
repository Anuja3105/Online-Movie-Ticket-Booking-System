package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.MovieRating;

public interface IMovieRatingDao extends JpaRepository<MovieRating, Integer> {
	MovieRating findById(int id);
	List<MovieRating> findByMovie_Id(int movie_id);
	
	MovieRating findByMovie_IdAndUser_Id(int movie_id, int user_Id);
	
	@Query("SELECT AVG(mr.rating) FROM MovieRating mr WHERE mr.movie.id = ?1")
	Float getAvgRating(int id);
}//-p1999
