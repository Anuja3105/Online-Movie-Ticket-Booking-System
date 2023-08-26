package com.showbuzz.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dao.ISeatDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.Response;
import com.showbuzz.dto.SeatBookingDTO;
import com.showbuzz.dto.SeatDTO;
import com.showbuzz.entity.Seat;
import com.showbuzz.services.SeatBookingServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class SeatBookingControllerImpl {

	@Autowired
	private SeatBookingServiceImpl bookingService;
	
	@Autowired
	private ISeatDao seatDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	@GetMapping("/seatBooking/available")
	public ResponseEntity<?> availableSeats(
			@RequestParam(name = "show_id", defaultValue = "") Integer show_id,
			@RequestParam(name = "booking_date", defaultValue =  "") String booking_date,
			HttpServletResponse resp) throws ParseException{
		List<String> seatList = bookingService.findUnbookedSeats(show_id,booking_date);
		return Response.success(seatList);
	}
	
	@GetMapping("/seat/booked")
	public ResponseEntity<?> bookedSeats(
			@RequestParam(name = "show_id", defaultValue = "") Integer show_id,
			@RequestParam(name = "booking_date", defaultValue =  "") String booking_date,
			HttpServletResponse resp) throws ParseException{
		List<String> seatList = bookingService.findBookedSeats(show_id,booking_date);
		return Response.success(seatList);
	}
	
	@GetMapping("/seat/{id}")
	public ResponseEntity<?> allSeats(
			@RequestParam(name = "show_id", defaultValue = "") Integer show_id,
			@RequestParam(name = "booking_date", defaultValue =  "") Date booking_date,
			HttpServletResponse resp){
		List<String> seatList = bookingService.findAllSeatsByShowAndDate(show_id,booking_date);
		return Response.success(seatList);
	}
	
	@PostMapping("/seat/insert")
	public ResponseEntity<?> insertSeat(@RequestBody SeatBookingDTO sbDto) throws ParseException{
		SeatBookingDTO list = sbDto;
		System.out.println(list.getBooking_date());
//		Map<String, Object> result = null ;
//		for (SeatBookingDTO sbd : list) {
//			System.out.println(sbd.getOrder_id());
		Map<String, Object> result = bookingService.insertBooking(sbDto);
//		}
		return Response.success(result);
	}
	
	
	/*	Unbooked Seats	*/
	@GetMapping("/seat/unbooked")
	public ResponseEntity<?> unbookedSeats(
			@RequestParam(name = "show_id", defaultValue = "") Integer show_id,
			@RequestParam(name = "booking_date", defaultValue =  "") String booking_date,
			HttpServletResponse resp) throws ParseException{
		List<String> seatList = bookingService.unbookedSeats(show_id,booking_date);
		
		List<Seat> unbookedSeats = new ArrayList<>();
		for (String s : seatList) {
			Seat b = seatDao.findBySeatId(s);
			unbookedSeats.add(b);
		}
		
		List<SeatDTO> seats = unbookedSeats.stream().map(b->convertor.toSeatDTO(b)).collect(Collectors.toList());
		return Response.success(seats);
	}	
	
	
	/*	update Order id	*/
	@PutMapping("/seat/update/{id}")
	public ResponseEntity<?> updateOrderId(@PathVariable(name = "id") int id, @RequestBody SeatBookingDTO dto){
		Map<String,Object> result = bookingService.updateOrderId(id, dto);
		return Response.success(result);
	}
	
	/*	UPdate SeatBooking Status to 1	*/
	@PutMapping("/seat")
	public ResponseEntity<?> updatePaymentStatus(){
		Map<String,Object> result = bookingService.updateBookingStatus();
		return Response.success(result);
	}
	
	
	
	
}
