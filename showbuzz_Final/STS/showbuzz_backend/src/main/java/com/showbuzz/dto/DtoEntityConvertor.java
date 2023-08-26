package com.showbuzz.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showbuzz.dao.IShowsDao;
import com.showbuzz.entity.City;
import com.showbuzz.entity.Movie;
import com.showbuzz.entity.MovieRating;
import com.showbuzz.entity.MovieReview;
import com.showbuzz.entity.Orders;
import com.showbuzz.entity.Payment;
import com.showbuzz.entity.Screen;
import com.showbuzz.entity.Seat;
import com.showbuzz.entity.SeatBooking;
import com.showbuzz.entity.Shows;
import com.showbuzz.entity.Theatre;
import com.showbuzz.entity.User;
@Component
public class DtoEntityConvertor {

	
	
	@Autowired
	private IShowsDao showDao;
	
	private static SimpleDateFormat sdf;
	static{
		sdf = new SimpleDateFormat("hh:mm:ss");
	}
	
	/* User	*/
	public UserDTO toUserDto(User entity) {
		
		UserDTO dto = new UserDTO();
		dto.setUser_id(entity.getUser_id());
		dto.setFirst_name(entity.getFirst_name());
		dto.setLast_name(entity.getLast_name());
		dto.setEmail(entity.getEmail());
		dto.setRole(entity.getRole());
		dto.setPassword(entity.getPassword());
		return dto;
	
	}

	public User toUserEntity(UserDTO dto) {
		
		User entity = new User();
		entity.setUser_id(dto.getUser_id());
		entity.setFirst_name(dto.getFirst_name());
		entity.setLast_name(dto.getLast_name());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return entity;
		
	}
	
	public User toUserDetailsEntity(UserDTO dto) {
		if(dto==null)
			return null;
		User entity = new User();
		entity.setUser_id(dto.getUser_id());
		entity.setFirst_name(dto.getFirst_name());
		entity.setLast_name(dto.getLast_name());
		entity.setEmail(dto.getEmail());
		entity.setPhone_no(dto.getPhone_no());
		entity.setAddress_line(dto.getAddress_line());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
		entity.setCountry(dto.getCountry());
		entity.setPincode(dto.getPincode());
		entity.setGender(dto.getGender());
		return entity;
	}
	
	public UserDTO toUserDetailsDto(User entity) {
		if(entity == null)
			return null;
		UserDTO dto = new UserDTO();
		dto.setUser_id(entity.getId());
		dto.setFirst_name(entity.getFirst_name());
		dto.setLast_name(entity.getLast_name());
		dto.setEmail(entity.getEmail());
		dto.setPhone_no(entity.getPhone_no());
		dto.setPassword("********");
		dto.setAddress_line(entity.getAddress_line());
		dto.setCity(entity.getCity());
		dto.setState(entity.getState());
		dto.setCountry(entity.getCountry());
		dto.setPincode(entity.getPincode());
		dto.setGender(entity.getGender());
		List<OrderDTO> orderList = entity.getOrderList().stream().map(o->this.OrderEntityToDto(o)).collect(Collectors.toList());
		dto.setOrderList(orderList);
		List<SeatBookingDTO> bookingList = entity.getBookingList().stream().map(o->this.toSeatBookingDTO(o)).collect(Collectors.toList());
		dto.setBookingList(bookingList);
		List<MovieRatingDTO> ratings = entity.getRatingList().stream().map(mr -> toRatingDTO(mr)).collect(Collectors.toList());
		dto.setRatingList(ratings);
		List<MovieReviewDTO> reviews = entity.getReviewList().stream().map(mrv -> toReviewDTO(mrv)).collect(Collectors.toList());
		dto.setReviewList(reviews);
		return dto;
	}
	
	/*	CITY	*/
	
	public CityDTO toCityDto(City entity) {
		
		CityDTO dto = new CityDTO();
		dto.setCity_id(entity.getId());
		dto.setCity_name(entity.getName());
		dto.setCity_state(entity.getState());
		dto.setCity_pincode(entity.getPincode());
		return dto;
	
	}

	public City toCityEntity(CityDTO dto) {
		
		City entity = new City();
		
		entity.setId(dto.getCity_id());
		entity.setName(dto.getCity_name());
		entity.setState(dto.getCity_state());
		entity.setPincode(dto.getCity_pincode());
		return entity;
		
	}

	
	/*	Theatre	*/

public TheatreDTO toTheatreDto (Theatre entity) {
		
		TheatreDTO dto = new TheatreDTO();
		
		dto.setTheatre_id(entity.getId());
		dto.setName(entity.getName());
		dto.setCity_id(entity.getCity().getId());
		dto.setAddress(entity.getAddress());
		dto.setNo_of_screen(entity.getNoOfScreen());
		dto.setKey1(entity.getKey1());
		dto.setKey2(entity.getKey2());
		dto.setManager_id(entity.getUser().getId());
		List<OrderDTO> orderList = entity.getOrderList().stream().map(o->this.OrderEntityToDto(o)).collect(Collectors.toList());
		dto.setOrderList(orderList);
		List<ScreenDTO> screenList = entity.getListOfScreen().stream().map(o->this.toScreenDTO(o)).collect(Collectors.toList());
		dto.setScreenList(screenList);
		
		return dto;
		
	}
	
	public Theatre toTheatreEntity (TheatreDTO dto) {
		
		Theatre entity= new Theatre();
		
		entity.setId(dto.getTheatre_id());
		entity.setName(dto.getName());
		entity.setCity(new City(dto.getCity_id()));
		entity.setAddress(dto.getAddress());
		entity.setNoOfScreen(dto.getNo_of_screen());
		entity.setKey1(dto.getKey1());
		entity.setKey2(dto.getKey2());
		entity.setUser(new User(dto.getManager_id()));
		return entity;
	}

	
	/*	Movie	*/
	//dto -> entity-------------------------
		public Movie toMovieEntity(MovieDTO dto) {
			Movie entity= new Movie();
			entity.setName(dto.getMovie_name());
			entity.setMovie_details(dto.getMovie_details());
			entity.setCategory(dto.getCategory());
			entity.setLanguage(dto.getLanguage());
			entity.setReleaseDate(dto.getRelease_date());
			entity.setStatus(dto.getStatus());
			entity.setGenre(dto.getGenre());
			entity.setMovie_poster(dto.getMoviePoster());
			
			//-----------------------doubt-------------------
//			
//			try {
//				entity.setMovie_poster(dto.getDataFile().getBytes());
//			} catch (Exception e) {
//				throw new MultipartException("Can't convert MultipartFile to bytes : " + dto.getDataFile(), e);
//			}
			
			return entity;
		}
		
		//entity -> dto-----------------------------------
		public MovieDTO toMovieDto(Movie entity) {
			if (entity == null) 
				return null;
			MovieDTO dto=new MovieDTO();
			dto.setId(entity.getId());
			dto.setMovie_name(entity.getName().toUpperCase());
			dto.setMovie_details(entity.getMovie_details());
			dto.setCategory(entity.getCategory());
			dto.setLanguage(entity.getLanguage());
			dto.setRelease_date(entity.getReleaseDate());
			dto.setStatus(entity.getStatus());
			dto.setGenre(entity.getGenre());
			dto.setMoviePoster(entity.getMovie_poster());
			List<MovieRatingDTO> ratings = entity.getRatingList().stream().map(mr -> toRatingDTO(mr)).collect(Collectors.toList());
			dto.setRatings(ratings);
			List<MovieReviewDTO> reviews = entity.getReviewList().stream().map(mrv -> toReviewDTO(mrv)).collect(Collectors.toList());
			dto.setReviews(reviews);
			List<ShowDTO> showList = entity.getShowList().stream().map(mr -> this.toShowsDTO(mr)).collect(Collectors.toList());
			dto.setShowList(showList);
			List<OrderDTO> orderList = entity.getOrderList().stream().map(mrv -> this.OrderEntityToDto(mrv)).collect(Collectors.toList());
			dto.setOrderList(orderList);
			return dto;
		}
		
		//entity -> dto-----------------------------
		public MovieRatingDTO toRatingDTO(MovieRating entity) {
			if(entity == null)
				return null;
			MovieRatingDTO dto= new MovieRatingDTO();
			dto.setId(entity.getId());
			dto.setMovie_id(entity.getMovie().getId());
			dto.setUser_id(entity.getUser().getId());
			dto.setFirstName(entity.getUser().getFirst_name());
			dto.setLastName(entity.getUser().getLast_name());
			dto.setMovieName(entity.getMovie().getName());
			dto.setRating(entity.getRating());
			return dto;
		}
		
		//dto -> entity-------------------------
		public MovieRating toRatingEntity(MovieRatingDTO dto) {
		
			if(dto == null)
				return null;
			MovieRating entity = new MovieRating();
			entity.setMovie(new Movie(dto.getId()));
			entity.setUser(new User(dto.getId()));
			entity.setRating(entity.getRating());
			return entity;
			
		}
		
		//entity -> dto-----------------------------------
		public MovieReviewDTO toReviewDTO(MovieReview entity) {
			if(entity == null)
				return null;
			MovieReviewDTO dto = new MovieReviewDTO();
			dto.setId(entity.getId());
			dto.setFirstName(entity.getUser().getFirst_name());
			dto.setLastName(entity.getUser().getLast_name());
			dto.setMovie_id(entity.getMovie().getId());
			dto.setUser_id(entity.getUser().getId());
			dto.setMovieName(entity.getMovie().getName());
			dto.setReview(entity.getReview());
			return dto;
		}
		
		//dto -> entity-------------------------
		public MovieReview toReviewEntity(MovieReviewDTO dto) {
			if(dto == null)
				return null;
			MovieReview entity = new MovieReview();
			entity.setMovie(new Movie(dto.getId()));
			entity.setReview(dto.getReview());
			entity.setUser(new User(dto.getId()));
			return entity;
		}
		
	
	/*	Screen	*/
	public Screen toScreenEntity(ScreenDTO dto) {
		if(dto==null)
			return null;
		Screen entity = new Screen();
		entity.setScreen_id(dto.getId());
		entity.setScreen_no(dto.getScreen_no());
		return entity;
	}
	public ScreenDTO toScreenDTO(Screen entity) {
		if(entity==null)
			return null;
		ScreenDTO dto = new ScreenDTO();
		dto.setId(entity.getScreen_id());
		dto.setScreen_no(entity.getScreen_no());
		dto.setTheatre_id(entity.getTheatre().getId());
		List<ShowDTO> shows = entity.getListOfShows().stream().map(sh -> toShowsDTO(sh)).collect(Collectors.toList());
		dto.setShowList(shows);
		/*
		 * List<MovieRatingDTO> ratings = entity.getRatingList().stream().map(mr -> toRatingDTO(mr)).collect(Collectors.toList());
			dto.setRatings(ratings);
			List<MovieReviewDTO> reviews = entity.getReviewList().stream().map(mrv -> toReviewDTO(mrv)).collect(Collectors.toList());
			dto.setReviews(reviews);
		 */
		
		return dto;
	}
	
	
	/*	Show 	*/
	public Shows toShowsEntity(ShowDTO dto) throws ParseException {
		if(dto==null)
			return  null;
		Shows entity = new Shows();
		entity.setShow_id(dto.getId());
		entity.setMovie(new Movie(dto.getMovie_id()));
		entity.setScreen(new Screen(dto.getScreen_id()));
		entity.setCost_factor(dto.getCost_factor());
		entity.setShowdate(dto.getShowdate());

		Date time1= sdf.parse(dto.getShowtime());
		System.out.println("Time : "+time1);
		entity.setShowtime(time1);
		return entity;
	}
	
	public ShowDTO toShowsDTO(Shows entity){
		if(entity==null)
			return  null;
		ShowDTO dto = new ShowDTO();
		dto.setId(entity.getShow_id());
		dto.setMovie_id(entity.getMovie().getId());
		dto.setCost_factor(entity.getCost_factor());
		dto.setScreen_id(entity.getScreen().getScreen_id());
		dto.setShowdate(entity.getShowdate());
		dto.setShowtime(entity.getShowtime().toString());
		return dto;
	}
	
	/* Seat */
	public Seat toSeatEntity(SeatDTO dto) {
		if(dto == null)
			return null;

		Seat entity =  new Seat();
		entity.setId(dto.getSeat_id());
		entity.setSeat_type(dto.getSeat_type());
		entity.setPrice(dto.getPrice());
		return entity;
		
	}

	public SeatDTO toSeatDTO(Seat entity) {
		if(entity == null)
			return null;
		
		SeatDTO dto =  new SeatDTO();
		dto.setSeat_id(entity.getId());
		dto.setSeat_type(entity.getSeat_type());
		dto.setPrice(entity.getPrice());
		return dto;
		
	}
	
	
	/*	Seat Booking	*/
	
	public SeatBooking toSeatBookingEntity(SeatBookingDTO dto) throws ParseException {
		if(dto == null)
			return null;
		SeatBooking entity = new SeatBooking();
		entity.setId(dto.getBooking_id());
		entity.setUser(new User(dto.getUser_id()));
		entity.setShow(new Shows(dto.getShow_id()));
		entity.setSeat(new Seat(dto.getSeat_id()));
		entity.setCreated_timestamp(new Date());
		entity.setPayment_status(dto.getPayment_status());
		Date date = sdf.parse(dto.getBooking_date());
		entity.setBooking_date(date);
		entity.setOrder_id(dto.getOrder_id());
		return entity;
	}
	
	public SeatBookingDTO toSeatBookingDTO(SeatBooking entity) {
		if(entity == null)
			return null;
		
		SeatBookingDTO dto = new SeatBookingDTO();
		dto.setBooking_id(entity.getId());
		dto.setCreated_timestamp(entity.getCreated_timestamp());
		dto.setPayment_status(entity.getPayment_status());
		dto.setSeat_id(entity.getSeat().getId());
		dto.setShow_id(entity.getShow().getShow_id());
		dto.setUser_id(entity.getUser().getUser_id());
		dto.setBooking_date(entity.getBooking_date().toString());
		dto.setOrder_id(entity.getOrder_id());
		return dto;
	}
	
	/*	Payment	*/
	public PaymentDTO EntityToDto(Payment entity)
	{
		PaymentDTO dto=new PaymentDTO();
		dto.setPaymentId(entity.getId());
		dto.setRazorpayPaymentId(dto.getRazorpayPaymentId());
		dto.setOrderId(entity.getOrderId());
		dto.setSignature(entity.getSignature());
		dto.setTotalAmount(entity.getTotalAmount());
		dto.setUserId(entity.getUser().getId());
		return dto;
	}
	public Payment DtoToEntity(PaymentDTO dto)
	{
		Payment entity=new Payment(dto.getPaymentId(), dto.getRazorpayPaymentId(), dto.getOrderId(), new User(dto.getUserId()), dto.getSignature(), dto.getTotalAmount(),new Date());;
		return entity;
	}
	
	/*	Orders	*/
	
	public Orders OrderDtoToEntity(OrderDTO dto) throws ParseException
	{
		if(dto == null)
			return null;
		Orders entity=new Orders();
		entity.setBooking(new SeatBooking(Integer.valueOf(dto.getBooking_id())));
		entity.setUser(new User(dto.getUser_id()));
		entity.setPayment(new Payment(dto.getPayment_id()));
		entity.setMovie(showDao.getById(dto.getShow_id()).getMovie());
		entity.setTheatre(showDao.findById(dto.getShow_id()).getScreen().getTheatre());
		entity.setScreen(showDao.findById(dto.getShow_id()).getScreen());
		entity.setShow(new Shows(dto.getShow_id()));
		entity.setSeat(new Seat(dto.getSeat_id()));
		entity.setShow_date(showDao.findById(dto.getShow_id()).getShowdate());
		entity.setShow_time(showDao.findById(dto.getShow_id()).getShowtime());
		return entity;
	}

	public OrderDTO OrderEntityToDto(Orders entity)
	{
		if(entity == null)
			return null;
		OrderDTO dto=new OrderDTO();
		dto.setOrder_id(entity.getId());
		dto.setUser_id(entity.getUser().getId());
		dto.setPayment_id(entity.getPayment().getId());
		dto.setMovie_id(entity.getMovie().getId());
		dto.setTheatre_id(entity.getTheatre().getId());
		dto.setScreen_id(entity.getScreen().getScreen_id());
		dto.setShow_id(entity.getShow().getShow_id());
		dto.setSeat_id(entity.getSeat().getId());
		dto.setBooking_id(entity.getBooking().getId());
		dto.setShow_date(showDao.findById(entity.getShow().getShow_id()).getShowdate());
		dto.setShow_time(showDao.findById(entity.getShow().getShow_id()).getShowtime());
		return dto;
	}
	
	
	
}
