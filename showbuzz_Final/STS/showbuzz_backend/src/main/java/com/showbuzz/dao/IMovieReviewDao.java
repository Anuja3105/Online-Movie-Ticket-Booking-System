package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.showbuzz.entity.MovieReview;

public interface IMovieReviewDao extends JpaRepository<MovieReview, Integer> {
	MovieReview findById(int id);
	List<MovieReview> findByMovie_Id(int movie_id);
	MovieReview findByMovie_IdAndUser_Id(int movie_id, int user_Id);
}//-p1999
