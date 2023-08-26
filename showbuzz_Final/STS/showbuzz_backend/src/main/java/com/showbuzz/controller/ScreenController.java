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
import com.showbuzz.dto.ScreenDTO;
import com.showbuzz.dto.ShowDTO;
import com.showbuzz.services.ScreenServiceImpl;


@CrossOrigin(origins = "*")
@RestController
public class ScreenController {

	@Autowired
	private ScreenServiceImpl screenService;
	
	@PostMapping("/screen")
	public ResponseEntity<?> addScreen(@RequestBody ScreenDTO screenDto) throws ParseException{
//		System.out.println("Time = "+showDto.getShowtime());
		Map<String, Object> result = screenService.saveScreen(screenDto);
		return Response.success(result);
	}
	
	@PutMapping("/screen/{id}")
	public ResponseEntity<?> updateScreen(@PathVariable("id") int id,@RequestBody ScreenDTO screenDto) throws ParseException{
		Map<String, Object> result = screenService.updateScreen(id,screenDto);
		return Response.success(result);		
	}
	
	@DeleteMapping("/screen/{id}")
	public ResponseEntity<?> deleteShow(@PathVariable("id") int id){
		Map< String, Object > result = screenService.deleteShow(id);
		return Response.success(result);
	}
	
	/*	Find All Screen */
	@GetMapping("/screen")
	public  ResponseEntity<?> allshows() {
		List<ScreenDTO> result = new ArrayList<>();
		result = screenService.findAll();
		return Response.success(result);
	}
	
	/*	Find All Screen for particular theatre */
	@GetMapping("/screen/{id}")
	public ResponseEntity<?> todaysShow(@PathVariable("id") int id){
		List<ScreenDTO> result = new ArrayList<>();
		result = screenService.findScreenByTheatreId(id);
		return Response.success(result);
	}
	
	
	
}
