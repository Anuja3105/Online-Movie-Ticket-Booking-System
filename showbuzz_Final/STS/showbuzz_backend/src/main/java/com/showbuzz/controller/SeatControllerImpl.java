package com.showbuzz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.Response;
import com.showbuzz.dto.SeatDTO;
import com.showbuzz.services.SeatServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class SeatControllerImpl {

	@Autowired
	private SeatServiceImpl seatService;
	
//	@GetMapping("/seat/available/{id}")
//	public ResponseEntity<?> availableSeats(@PathVariable("id") int id){
//		List<SeatDTO> seatList = seatService.findUnbookedSeats(id);
//		return Response.success(seatList);
//	}
//	
//	@GetMapping("/seat/booked/{id}")
//	public ResponseEntity<?> bookedSeats(@PathVariable("id") int id){
//		List<SeatDTO> seatList = seatService.findBookedSeats(id);
//		return Response.success(seatList);
//	}
//	
//	@GetMapping("/seat/{id}")
//	public ResponseEntity<?> allSeats(@PathVariable("id") int id){
//		List<SeatDTO> seatList = seatService.findSeatByScreenId(id);
//		return Response.success(seatList);
//	}
//	
//	@PutMapping("/seat/{no}")
//	public ResponseEntity<?> bookSeat(@PathVariable("no") String no){
//		Map<String, Object> result = seatService.bookSeat(no);
//		return Response.success(result);
//	}
	@GetMapping("/seat/all")
	public ResponseEntity<?> allSeats(){
		List<SeatDTO> list = new ArrayList<SeatDTO>();
		list = seatService.findAll();
		return Response.success(list);
	}
	
	
}
