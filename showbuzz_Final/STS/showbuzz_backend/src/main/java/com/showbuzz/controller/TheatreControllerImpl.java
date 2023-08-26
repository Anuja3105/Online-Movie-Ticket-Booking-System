package com.showbuzz.controller;

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

import com.showbuzz.dto.Response;
import com.showbuzz.dto.TheatreDTO;
import com.showbuzz.services.TheatreServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class TheatreControllerImpl {
	
	@Autowired
	private TheatreServiceImpl theatreService;
	
	@GetMapping("/theatre/{id}")
	public ResponseEntity<?> findTheatreById(@PathVariable("id") int id) {
		TheatreDTO result = theatreService.findById(id);
		return Response.success(result);
	}

	@GetMapping("/theatre/all")
	public ResponseEntity<?> allTheatre() {
		List<TheatreDTO> result = theatreService.findAllTheatre();
		return Response.success(result);
	}
	
	@GetMapping("/theatre/name/{name}")
	public ResponseEntity<?> findTheatreByName(@PathVariable("name") String name ) {
		TheatreDTO result = theatreService.findByName(name);
		return Response.success(result);
	}
	
	@PostMapping("/theatre/add")
	public ResponseEntity<?> addTheatre (@RequestBody TheatreDTO theatreDto){
		Map<String, Object> result = theatreService.saveTheatre(theatreDto);
		return Response.success(result);
	}
	
	@DeleteMapping("/theatre/delete/{id}")
	public ResponseEntity<?> deleteTheatre (@PathVariable int id) {
		Map<String, Object> result = theatreService.deleteTheatre(id);
		return Response.success(result);
	}
	
	@PutMapping("/theatre/{id}")
	public ResponseEntity<?> updateTheatre(@PathVariable("id") int id,@RequestBody TheatreDTO theatre) {
		Map<String, Object> result = theatreService.updateTheatre(id, theatre);
		return Response.success(result);
	}
	
	@GetMapping("/theatre/movie")
	public ResponseEntity<?> findTheatreByMovieId(
			@RequestParam(name = "movie_id", defaultValue = "") Integer movie_id,
			@RequestParam(name = "booking_date", defaultValue =  "") String booking_date,
			@RequestParam(name = "city_id", defaultValue = "") Integer city_id,
			HttpServletResponse resp) {
		System.out.println();
		System.out.println("movie_id " + movie_id + " booking date " + booking_date );
		System.out.println();
		List<TheatreDTO> result = theatreService.findByMovieId(movie_id,booking_date, city_id);
		return Response.success(result);
	}
	
	@GetMapping("/theatre/show/{id}")
	public ResponseEntity<?> findTheatreBySHowId(@PathVariable("id") int id) {
		TheatreDTO result = theatreService.findByShowId(id);
		return Response.success(result);
	}

	@GetMapping("/theatre/user/{id}")
	public ResponseEntity<?> findTheatreByUserId(@PathVariable("id") int id) {
		TheatreDTO result = theatreService.findByUserId(id);
		return Response.success(result);
	}
}
