package com.showbuzz.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Orders;

public interface IOrderDao extends JpaRepository<Orders, Integer>
{
	@Query(value="SELECT * FROM orders WHERE user_id=?;",nativeQuery = true)
	List<Orders> findByUserId(int id);
	
	@Query(value="SELECT * FROM orders WHERE show_id=? ;",nativeQuery = true)
	List<Orders> findByShowId(int showId);
	
	@Query(value="SELECT * FROM orders WHERE screen_id=? ;",nativeQuery = true)
	List<Orders> findByScreenId(int screenId);
	
	@Query(value="SELECT * FROM orders WHERE theatre_id=? ;",nativeQuery = true)
	List<Orders> findByTheatreId(int theatreId);
	
	@Query(value = "SELECT * FROM orders WHERE movie_id=?;",nativeQuery = true)
	List<Orders> findByMovieId(int movieId);
	
	@Query(value="SELECT * FROM orders WHERE show_date=?",nativeQuery = true)
	List<Orders> findByShowDate(Date showDate);
}
//@Query(value = "SELECT * FROM seat_booking sb WHERE sb.order_id = ?;", nativeQuery = true)
//List<SeatBooking> findSeatBookingByOrderId(String order_id);