package com.showbuzz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.MovieRatingDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.services.MovieRatingServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class MovieRatingControllerImpl {

	@Autowired
	private MovieRatingServiceImpl ratingService;
	
	@PostMapping("rating/{movie_id}")
	public ResponseEntity<?> addMovieRating(@PathVariable("movie_id") int movie_id,@RequestBody MovieRatingDTO ratingDto){
		Map<String,Object> result = ratingService.saveMovieRating(movie_id, ratingDto);
		return Response.success(result);
	}
	
	@GetMapping("/rating/{id}/aggregate")
	public ResponseEntity<?> getAvgRating(@PathVariable("id") int movieId) {
		Map<String, Object> result = ratingService.getMovieAvgRating(movieId);
		return Response.success(result);
	}
	
	@GetMapping("/rating/{id}")
	public ResponseEntity<?> findMovieRatings(@PathVariable("id") int movieId) {
		List<MovieRatingDTO> result = ratingService.findMovieRatingsByMovieId(movieId);
		return Response.success(result);
	}
	
	@DeleteMapping("/rating/{id}")
	public ResponseEntity<?> deleteRating(@PathVariable("id") int id){
		Map<String,Object> result = ratingService.deleteRating(id);
		return Response.success(result);
	}
	
}//-p1999
