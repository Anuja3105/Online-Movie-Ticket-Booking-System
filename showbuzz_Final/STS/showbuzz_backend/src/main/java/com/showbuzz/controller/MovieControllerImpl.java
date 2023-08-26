package com.showbuzz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.MovieDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.services.MovieServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class MovieControllerImpl {

	@Autowired
	private MovieServiceImpl movieService;
	
	@PostMapping("/movie")
	public ResponseEntity<?> addMovie(@RequestBody MovieDTO movieDto){
		System.out.println("Inserted: "+movieDto);
		Map<String,Object> result = movieService.addMovie(movieDto);
		return Response.success(result);
	}
	
	@GetMapping("/movie/all")
	public ResponseEntity<?> showAllMovie(){
		List<MovieDTO> result = new ArrayList<>();
		result = movieService.findAllMovie();
		return Response.success(result);
	}
	
	@GetMapping("/movie/find/{id}")
	public ResponseEntity<?> findMovieById(@PathVariable("id") int id){
		MovieDTO result = movieService.findMovieById(id);
		return Response.success(result);
	}
	
	@PutMapping("movie/{id}")
	public ResponseEntity<?> editMovie(@PathVariable("id") int id,@RequestBody MovieDTO movieDto){
		Map<String,Object> result = movieService.editMovie(id,movieDto);
		return Response.success(result);
	}
	
	@DeleteMapping("movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
		Map<String,Object> result = movieService.deleteMovie(id);
		return Response.success(result);
	}
	
	@GetMapping("/movie/search")
	public ResponseEntity<?> findBlogs(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "genre", defaultValue = "") String genre,
			HttpServletResponse resp) {
		List<MovieDTO> result = new ArrayList<>();
		if(!name.isEmpty())
			result = movieService.findMovieByName(name);
		else if(!category.isEmpty())
			result = movieService.findByMoviesCategory(category);
		else if(!genre.isEmpty())
			result = movieService.findByMoviesCategory(genre);
		else
			result = movieService.findAllMovie();
		return Response.success(result);
	}
	
	@GetMapping("/movie/name/{name}")
	public ResponseEntity<?> findMovieByName(@PathVariable("name") String name){
		List<MovieDTO> result = new ArrayList<MovieDTO>();
		result = movieService.findMovieByName(name);
		return Response.success(result);
	}
	
	@GetMapping("/movie/category/{category}")
	public ResponseEntity<?> findMovieByCategory(@PathVariable("category") String category){
		List<MovieDTO> result = new ArrayList<MovieDTO>();
		result = movieService.findByMoviesCategory(category);
		return Response.success(result);
	}
	
	@GetMapping("/movie/genre/{genre}")
	public ResponseEntity<?> findMovieByGenre(@PathVariable("genre") String genre){
		List<MovieDTO> result = new ArrayList<MovieDTO>();
		result = movieService.findByMoviesGenre(genre);
		return Response.success(result);
	}
	
	@GetMapping("/movie/city/{id}")
	public ResponseEntity<?> findMovieByCityId(@PathVariable("id") int id){
		List<MovieDTO> result = movieService.findByCityId(id);
		return Response.success(result);
	}

	@GetMapping("/movie/show/{id}")
	public ResponseEntity<?> findMovieByShowId(@PathVariable("id") int id){
		MovieDTO result = movieService.findByShowId(id);
		return Response.success(result);
	}
	
}//-p1999
