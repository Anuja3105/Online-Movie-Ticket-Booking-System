package com.showbuzz.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IPaymentDao;
import com.showbuzz.dao.ISeatBookingDao;
import com.showbuzz.dao.ISeatDao;
import com.showbuzz.dao.IShowsDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.SeatBookingDTO;
import com.showbuzz.dto.SeatDTO;
import com.showbuzz.dto.ShowDTO;
import com.showbuzz.entity.Payment;
import com.showbuzz.entity.Seat;
import com.showbuzz.entity.SeatBooking;
import com.showbuzz.entity.Shows;
import com.showbuzz.entity.User;

@Service
@Transactional
public class SeatBookingServiceImpl {
	private static SimpleDateFormat sdf;
	
	static{
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	@Autowired
	private ISeatBookingDao bookingDao;
	
	@Autowired
	private ISeatDao seatDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IShowsDao showDao;

	@Autowired
	private IPaymentDao paymentDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public SeatBookingDTO findBookingById(int id) {
		SeatBooking booking = bookingDao.findById(id);
		return convertor.toSeatBookingDTO(booking);
	}
	
	/*	find show by seat booking id	*/
	public ShowDTO findShowByBookingId(int id) {
		SeatBooking booking = bookingDao.findById(id);
		Shows show = booking.getShow();
		return convertor.toShowsDTO(show);
	}
	
	/*	find seat by booking id	*/
	public SeatDTO findSeatByBookingId(int id) {
		SeatBooking booking = bookingDao.findById(id);
		Seat seat = booking.getSeat();
		return convertor.toSeatDTO(seat);
	}
	
	/*	All Seats Object	*/
	public List<SeatDTO> findAllSeats(){
		List <Seat> seatList = new ArrayList<Seat>();
		seatList = seatDao.findAll();
		return seatList.stream()
				.map(r -> convertor.toSeatDTO(r))
				.collect(Collectors.toList());
	}
	
	/*	All Seats_id	*/
	public List<String> findAllSeatsId(){
		List <String> seatList = new ArrayList<String>();
		seatList = seatDao.findAllId();
		return seatList;
	}
	
	
	/*	Find unbooked seats	for Show on particular date 	*/
	public List<String> findUnbookedSeats(int show_id, String booking_date) throws ParseException{
		Date b_date = sdf.parse(booking_date);
		List<String> unbookedSeats = new ArrayList<String>();
		unbookedSeats = bookingDao.findSeats(show_id, 0, b_date);
		return unbookedSeats;
	}

	/*	Find booked seats	for Show on particular date	*/
	public List<String> findBookedSeats(int show_id, String booking_date) throws ParseException{
		Date b_date = sdf.parse(booking_date);
		List<String> bookedSeats = new ArrayList<String>();
		bookedSeats = bookingDao.findSeats(show_id, 1, b_date);
		return bookedSeats;
	}
	
	/*	Find 	All seats for particular show and date */
	public List<String> findAllSeatsByShowAndDate(int show_id, Date booking_date){
		List<String> allSeats = new ArrayList<String>();
		allSeats = bookingDao.findAllSeatsByShowDate(show_id, booking_date);
		return allSeats;
	}
	
	
	/*	Book Seat	*/
	public Map<String, Object> BookSeat(int id){
		SeatBooking booking = bookingDao.findById(id);
		booking.setPayment_status(1);
		bookingDao.save(booking);
		return Collections.singletonMap("SeatBooked", booking.getSeat().getId());
	}
	
	/*	Find seat booked by user for particular date of show	*/
	public List<String> seatBookedByUser(int user_id,int show_id, Date booking_date){
		List<String> seatsBookedByUser = new ArrayList<String>();
		seatsBookedByUser = bookingDao.findSeatBookedByUser(user_id,show_id, booking_date);
		return seatsBookedByUser;
	}
	
	
	public Map<String, Object> insertBooking(SeatBookingDTO dto) throws ParseException{
		List<String> availableSeats = new ArrayList<String>();
//		String booking_date = sdf.format(dto.getBooking_date());
		availableSeats = this.unbookedSeats(dto.getShow_id(), dto.getBooking_date());
		if(availableSeats.contains(dto.getSeat_id())) {
			SeatBooking booking = new SeatBooking();
			Date bookingDate = sdf.parse(dto.getBooking_date());
			booking.setBooking_date(bookingDate);
			booking.setPayment_status(dto.getPayment_status());
			booking.setSeat(new Seat(dto.getSeat_id()));
			booking.setUser(new User(dto.getUser_id()));
			booking.setOrder_id(dto.getOrder_id());
			booking.setCreated_timestamp(new Date());
			Shows show = showDao.findById(dto.getShow_id());
			show.addSeatBooking(booking);
			User user = userDao.findById(dto.getUser_id());
			user.addUserSeatBooking(booking);
			booking = bookingDao.save(booking);
//			userDao.save(user);
			return Collections.singletonMap("insertedId", booking.getId());
		}
		return Collections.singletonMap("not inserted", 0);
	}
	
	
	/*	Unbooked Seats	*/
	public List<String> unbookedSeats(int show_id, String booking_date) throws ParseException{
		List <String> bookedSeats = this.findBookedSeats(show_id, booking_date);
		List<String> allSeats = this.findAllSeatsId();
		List<String> unbookedSeats = allSeats;

		unbookedSeats.removeAll(bookedSeats);
		return unbookedSeats;
	}
	
	
	/*	Find SeatBooking by order id	*/
	
	public List<SeatBooking> findSeatBookingsByOrderId(String order_id){
		List<SeatBooking> seatBookingList = new ArrayList<SeatBooking>();
		seatBookingList = bookingDao.findSeatBookingByOrderId(order_id);
		return seatBookingList;
	}
	
	/*	Update Status of seat Booking	*/
	
	public Map<String, Object> updateBookingStatus() {
		
		/*	Optional	*/
//	public void updateBookingStatus(String order_id) {
//		List<SeatBooking> seatBookingList = new ArrayList<SeatBooking>();
//		seatBookingList = this.findSeatBookingsByOrderId(order_id);
//		for (SeatBooking s : seatBookingList) {
//			s.setPayment_status(1);
//			bookingDao.save(s);
//		}
		
		/*	Perfect Working	*/
		List<Payment> paymentList = paymentDao.findAll();
		for (Payment p : paymentList) {
			bookingDao.updateSeatBookingStatus(p.getOrderId());
		}
		return Collections.singletonMap("seat booked", null);
	}
	
	
	/*	Update Order id	*/
	public Map<String, Object> updateOrderId( int user_id, SeatBookingDTO dto) {
		bookingDao.updateOrderId(dto.getOrder_id(), user_id);
		return Collections.singletonMap("orderId updated", dto.getOrder_id());
	}
	
	
	
	
}
