package com.showbuzz.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.Response;
import com.showbuzz.dto.ShowDTO;
import com.showbuzz.services.ShowServicesImpl;


@CrossOrigin(origins = "*")
@RestController
public class ShowController {

	@Autowired
	private ShowServicesImpl showService;
	
	@PostMapping("/show")
	public ResponseEntity<?> addShow(@RequestBody ShowDTO showDto) throws ParseException{
//		System.out.println("Time = "+showDto.getShowtime());
		Map<String, Object> result = showService.saveShows(showDto);
		return Response.success(result);
	}
	
	@PutMapping("/show/{id}")
	public ResponseEntity<?> updateShow(@PathVariable("id") int id,@RequestBody ShowDTO showDto) throws ParseException{
		Map<String, Object> result = showService.updateShows(id,showDto);
		return Response.success(result);		
	}
	
	@DeleteMapping("/show/{id}")
	public ResponseEntity<?> deleteShow(@PathVariable("id") int id){
		Map< String, Object > result = showService.deleteShow(id);
		return Response.success(result);
	}
	
	/*	Find All SHows */
	@GetMapping("/show")
	public  ResponseEntity<?> allshows() {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findAll();
		return Response.success(result);
	}
	
	/*	Find All Shows in current day */
	@GetMapping("/show/today")
	public ResponseEntity<?> todaysShow() throws ParseException{
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findTodaysShow();
		return Response.success(result);
	}
	
	/*	FInd ALl Show by movie id	*/
	
	@GetMapping("/show/movie/{id}")
	public  ResponseEntity<?> showByMovieId(@PathVariable(name = "id") int id) {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findShowByMovieId(id);
		return Response.success(result);
	}

	/*	FInd ALl Show by screen id	*/
	@GetMapping("/show/screen/{id}")
	public  ResponseEntity<?> showByScreenId(@PathVariable(name = "id") int id) {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findShowByScreenId(id);
		return Response.success(result);
	}

	/*	FInd ALl Show by theatre id	*/
	@GetMapping("/show/theatre/{id}")
	public  ResponseEntity<?> showByTheatreId(@PathVariable(name = "id") int id) {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findShowByTheatreId(id);
		return Response.success(result);
	}
	
	/*	FInd ALl Show by City id	*/
	@GetMapping("/show/city/{id}")
	public  ResponseEntity<?> showByCityId(@PathVariable(name = "id") int id) {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findShowByCityId(id);
		return Response.success(result);
	}

	/*	FInd ALl Show by City id	*/
	@GetMapping("/show/showdate/{date}")
	public  ResponseEntity<?> showByBookingDate(@PathVariable(name = "date") String date) throws ParseException {
		List<ShowDTO> result = new ArrayList<>();
		result = showService.findShowByBookingDate(date);
		return Response.success(result);
	}
	
	@GetMapping("/show/{id}")
	public ResponseEntity<?> findShowById(@PathVariable(name = "id") int id){
		ShowDTO show= showService.findByShowId(id);
		return Response.success(show);
		
	}
	
}
