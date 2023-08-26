package com.showbuzz.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbuzz.dao.IMovieDao;
import com.showbuzz.dao.IOrderDao;
import com.showbuzz.dao.IPaymentDao;
import com.showbuzz.dao.IScreenDao;
import com.showbuzz.dao.ISeatBookingDao;
import com.showbuzz.dao.ISeatDao;
import com.showbuzz.dao.IShowsDao;
import com.showbuzz.dao.ITheatreDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.OrderDTO;
import com.showbuzz.entity.Movie;
import com.showbuzz.entity.Orders;
import com.showbuzz.entity.Payment;
import com.showbuzz.entity.Screen;
import com.showbuzz.entity.SeatBooking;
import com.showbuzz.entity.Shows;
import com.showbuzz.entity.Theatre;
import com.showbuzz.entity.User;

@Transactional
@Service
public class OrderServiceImpl {
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IScreenDao screenDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IShowsDao showDao;
	@Autowired
	private ITheatreDao theatreDao;
	@Autowired
	private IMovieDao movieDao;
	@Autowired
	private ISeatDao seatDao;
	@Autowired
	private ISeatBookingDao bookingDao;
	@Autowired
	private IPaymentDao paymentDao;
	
	@Autowired
	private DtoEntityConvertor convertor;

	private static SimpleDateFormat sdf;

	static {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	public Map<String, Object> addOrder(OrderDTO orderDto) throws ParseException {
		Orders order = convertor.OrderDtoToEntity(orderDto);
		User user = userDao.findById(orderDto.getUser_id());
		user.addOrder(order);
		Shows show = showDao.findById(orderDto.getShow_id());
		show.addOrder(order);
		int theatre_id = show.getScreen().getTheatre().getId();
		int movie_id = show.getMovie().getId();
		Screen screen = show.getScreen();
		screen.addOrder(order);
		Theatre theatre = theatreDao.findById(theatre_id);
		theatre.addOrder(order);
		
		Movie movie = movieDao.findById(movie_id);
		movie.addMovieOrders(order);
		int booking_id = Integer.valueOf(orderDto.getBooking_id());
		SeatBooking booking = bookingDao.findById(booking_id);
		booking.addOrder(order);
		int payment_id = paymentDao.findIdByOrderId(booking.getOrder_id());
//		Seat seat = seatDao.findBySeatId(orderDto.getSeat_id());
//		seat.addOrder(order);
		System.out.println();
		System.out.println("payment id : "+payment_id);
		System.out.println();
		
		Payment payment = paymentDao.findById(orderDto.getPayment_id());
		payment.addOrder(order);
		order = orderDao.save(order);
		paymentDao.save(payment);
		return Collections.singletonMap("InsertedId", order.getId());
	}

	public List<OrderDTO> findAll() {
		List<Orders> orders = orderDao.findAll();
		return orders.stream().map(allOrders -> convertor.OrderEntityToDto(allOrders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findBbyUserId(int id) {
		List<Orders> orderList = orderDao.findByUserId(id);
		return orderList.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findByShowId(int showId) {
		List<Orders> list = orderDao.findByShowId(showId);
		return list.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findByScreenId(int screenId) {
		List<Orders> list = orderDao.findByScreenId(screenId);
		return list.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findByTheatreId(int theatreId) {
		List<Orders> list = orderDao.findByTheatreId(theatreId);
		return list.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findByMovieId(int movieId) {
		List<Orders> list = orderDao.findByMovieId(movieId);
		return list.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}

	public List<OrderDTO> findByShowDate(String ShowDate) throws ParseException {
		Date showDate=sdf.parse(ShowDate);
		List<Orders> list = orderDao.findByShowDate(showDate);
		return list.stream().map(orders -> convertor.OrderEntityToDto(orders)).collect(Collectors.toList());
	}
}
