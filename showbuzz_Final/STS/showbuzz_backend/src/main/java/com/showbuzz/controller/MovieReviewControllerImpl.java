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

import com.showbuzz.dto.MovieReviewDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.services.MovieReviewServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class MovieReviewControllerImpl {

	@Autowired
	private MovieReviewServiceImpl reviewService;
	
	@PostMapping("review/{id}")
	public ResponseEntity<?> addMovieReview(@PathVariable("id") int id,@RequestBody MovieReviewDTO reviewDto){
		Map<String,Object> result = reviewService.addMovieReview(id, reviewDto);
		return Response.success(result);
	}
	
	@GetMapping("/review/{id}")
	public ResponseEntity<?> findMovieReviews(@PathVariable("id") int movieId) {
		List<MovieReviewDTO> result = reviewService.findMovieReviewByMovieId(movieId);
		return Response.success(result);
	}
	
	@DeleteMapping("/review/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable("id") int id){
		Map<String,Object> result = reviewService.deleteReview(id);
		return Response.success(result);
	}
	
	
}//-p1999
