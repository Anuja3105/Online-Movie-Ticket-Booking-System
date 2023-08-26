package com.showbuzz.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IMovieDao;
import com.showbuzz.dao.IMovieRatingDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.MovieRatingDTO;
import com.showbuzz.entity.Movie;
import com.showbuzz.entity.MovieRating;
import com.showbuzz.entity.User;


@Transactional
@Service
public class MovieRatingServiceImpl {

	@Autowired
	private IMovieRatingDao movieRatingDao;
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private DtoEntityConvertor converter;
	
	@Autowired
	private IMovieDao movieDao;
	
	
	public Map<String, Object> saveMovieRating(int movieId, MovieRatingDTO movieRating) {
		/*
		MovieRating rating = movieRatingDao.findByMovie_IdAndUser_Id(movieId, movieRating.getUser_id());
		System.out.println(movieRating + "\n" + rating);
		if(rating != null) {
			if(rating.getRating() != movieRating.getRating()) {
				rating.setRating(movieRating.getRating());
				movieRatingDao.save(rating);
				return Collections.singletonMap("changedRows", 1);
			}
			return Collections.singletonMap("affectedRows", 0);
		}
		else {
			rating = new MovieRating(movieRating.getRating());
			rating.setUser(new User(movieRating.getUser_id()));
			
			Movie movie=movieDao.findById(movieId);
			movie.addMovieRating(rating);
			rating = movieRatingDao.save(rating);
			
			return Collections.singletonMap("insertedId", rating.getId());	
		}
		*/
		MovieRating m_rating = movieRatingDao.findByMovie_IdAndUser_Id(movieId, movieRating.getUser_id());
		
		if(m_rating == null) {
			MovieRating rating = converter.toRatingEntity(movieRating);
			rating.setUser(new User(movieRating.getUser_id()));
			rating.setRating(movieRating.getRating());
			User user = userDao.findById(movieRating.getUser_id());
			user.addUserRating(rating);
			Movie movie = movieDao.findById(movieId);
			movie.addMovieRating(rating);
			movieRatingDao.save(rating);
			return Collections.singletonMap("changedRows", 1);
		}
		else{
			m_rating.setRating(movieRating.getRating());
			m_rating = movieRatingDao.save(m_rating);
			return Collections.singletonMap("changedRows", 1);
		}
	}
	
	public Map<String,Object> getMovieAvgRating(int movieId) {
		Float rating = movieRatingDao.getAvgRating(movieId);
		return Collections.singletonMap("avg", rating);
	}
	
	public List<MovieRatingDTO> findMovieRatingsByMovieId(int movieId) {
		List<MovieRating> list = movieRatingDao.findByMovie_Id(movieId);
		return list.stream().map(r -> converter.toRatingDTO(r)).collect(Collectors.toList());
	}
	
	public Map<String,Object> deleteRating(int id){
		if(movieRatingDao.existsById(id)) {
			MovieRating rating = movieRatingDao.findById(id);
			movieRatingDao.delete(rating);
			return Collections.singletonMap("affectedRow", 1);
		}
		return Collections.singletonMap("affectedRow", 0);
	}
	
}//-p1999
