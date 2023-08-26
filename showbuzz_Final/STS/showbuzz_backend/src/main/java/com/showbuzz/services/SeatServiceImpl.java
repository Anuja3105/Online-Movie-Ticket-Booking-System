package com.showbuzz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.ISeatDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.SeatDTO;
import com.showbuzz.entity.Seat;

@Transactional
@Service
public class SeatServiceImpl {

	@Autowired
	private ISeatDao seatDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public SeatDTO findSeatById(String id) {
		Seat seat = seatDao.findBySeatId(id);
		return convertor.toSeatDTO(seat);
	}

	/*	Find seats By Screen	*/
	/*
	public List<SeatDTO> findSeatByScreenId(int id) {
		List<Seat> seatList = seatDao.findAll();
		return seatList.stream()
				.filter(s -> s.getScreen().getScreen_id() == id)
				.map(s -> convertor.toSeatDTO(s))
				.collect(Collectors.toList());
	}
	 */

	/*	Find Available seats	*/
//	public List<SeatDTO> findUnbookedSeats(int id) {
//		List<Seat> seatList = seatDao.findAll();
//		return seatList.stream()
//				.filter(s ->s.getScreen().getScreen_id() == id && s.getSeat_status()==0)
//				.map(s -> convertor.toSeatDTO(s))
//				.collect(Collectors.toList());
//	}

	/*	Find booked seats	*/
//	public List<SeatDTO> findBookedSeats(int id) {
//		List<Seat> seatList = seatDao.findAll();
//		return seatList.stream()
//				.filter(s ->s.getScreen().getScreen_id() == id && s.getSeat_status()==1)
//				.map(s -> convertor.toSeatDTO(s))
//				.collect(Collectors.toList());
//	}
	
	/*	Book Seat	*/
	
//	public Map<String, Object> bookSeat(String seatNo){
//		Seat seat = seatDao.findBySeatNo(seatNo);
//		if(seat.getSeat_status()==0) {
//			seat.setSeat_status(1);
//			return Collections.singletonMap("SeatBooked", seat.getSeat_no());
//		}
//		else
//		{
//			return Collections.singletonMap("This Seat is already booked", seat.getSeat_no());
//		}
//	}
	
	public List<SeatDTO> findAll(){
		List<Seat> list = new ArrayList<Seat>();
		list = seatDao.findAll();
		return list.stream()
				.map(s -> convertor.toSeatDTO(s))
				.collect(Collectors.toList());
	}
	
}
