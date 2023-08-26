package com.showbuzz.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IMovieDao;
import com.showbuzz.dao.IMovieReviewDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.MovieReviewDTO;
import com.showbuzz.entity.Movie;
import com.showbuzz.entity.MovieReview;
import com.showbuzz.entity.User;


@Transactional
@Service
public class MovieReviewServiceImpl {
	
	@Autowired
	private IMovieReviewDao movieReviewDao;

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private DtoEntityConvertor converter;
	
	@Autowired
	private IMovieDao movieDao;
	
	//Find movieReview By its id-------------------------
		public MovieReviewDTO findMovieReviewById(int movieId) {
			MovieReview movie = movieReviewDao.findById(movieId);
			return converter.toReviewDTO(movie);
		}
	
	public Map<String,Object> addMovieReview(int movieId,MovieReviewDTO movieReview){
		MovieReview review = new MovieReview(movieReview.getReview());
		review.setUser(new User(movieReview.getUser_id()));
		
		User user = userDao.findById(movieReview.getUser_id());
		user.addUserReview(review);
		
		Movie movie=movieDao.findById(movieId);
		movie.addMovieReview(review);
		review = movieReviewDao.save(review);

		return Collections.singletonMap("insertedId", review.getId());
	}
	
	public List<MovieReviewDTO> findMovieReviewByMovieId(int movieId) {
		List<MovieReview> list = movieReviewDao.findByMovie_Id(movieId);
		return list.stream().map(r -> converter.toReviewDTO(r)).collect(Collectors.toList());
	}
	
	public Map<String,Object> deleteReview(int id){
		if(movieReviewDao.existsById(id)) {
			MovieReview review = movieReviewDao.findById(id);
			movieReviewDao.delete(review);
			return Collections.singletonMap("affectedRow", 1);
		}
		return Collections.singletonMap("affectedRow", 0);
	}
}//-p1999
