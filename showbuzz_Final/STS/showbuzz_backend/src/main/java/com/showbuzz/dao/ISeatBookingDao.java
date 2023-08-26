package com.showbuzz.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.SeatBooking;

public interface ISeatBookingDao extends JpaRepository<SeatBooking, Integer>{

	SeatBooking findById(int id);
//	void updateStatus(int id);
//	@Query("SELECT sb.seat_id FROM Seat_Boooking sb WHERE sb.show_id = ?1 AND sb.status = ?2 AND sb.booking_date = ?3")
//	List<String> findSeats(int show_id,int booking_status, String booking_date);
//
//	@Query("SELECT sb.seat_id FROM Seat_Boooking sb WHERE sb.show_id = ?1  AND sb.booking_date = ?2")
//	List<String> findAllSeatsByShowDate(int show_id, String booking_date);
//	
//	@Query("SELECT sb.seat_id FROM Seat_Boooking sb WHERE sb.user_id = ?1 sb.show_id = ?2  AND sb.booking_date = ?3")
//	List<String> findSeatBookedByUser(int user_id, int show_id, String booking_date);

	@Query(value="SELECT sb.seat_id FROM Seat_Booking sb WHERE sb.show_id = ? AND sb.payment_status = ? AND sb.booking_date = ?;", nativeQuery = true)
	List<String> findSeats(int show_id,int booking_status, Date booking_date);
	
	@Query(value="SELECT sb.seat_id FROM Seat_Booking sb WHERE sb.show_id = ?  AND sb.booking_date = ?;",nativeQuery = true)
	List<String> findAllSeatsByShowDate(int show_id, Date booking_date);
	
	@Query(value = "SELECT sb.seat_id FROM Seat_Booking sb WHERE sb.user_id = ? AND sb.show_id = ?  AND sb.booking_date = ?;",nativeQuery = true)
	List<String> findSeatBookedByUser(int user_id, int show_id, Date booking_date);
	
//	@Query(value = "SELECT s.seat_id from seat s WHERE seat_id NOT IN (?);", nativeQuery = true)
//	List<String> findUnBookedSeats(List<String> bookedString);
	
	@Query(value = "SELECT * FROM seat_booking sb WHERE sb.order_id = ?;", nativeQuery = true)
	List<SeatBooking> findSeatBookingByOrderId(String order_id);
	
	@Modifying
	@Query(value = "UPDATE seat_booking sb SET order_id = ? WHERE user_id = ? AND  (current_timestamp - created_timestamp)<100;", nativeQuery = true)
	void updateOrderId(String order_id, int user_id);
	
	@Modifying
	@Query(value = "UPDATE seat_booking sb SET sb.payment_status = 1 WHERE sb.order_id = ?",nativeQuery = true )
	void updateSeatBookingStatus(String order_id);
	
}


/*
 * @Query(value = "select u.firstName,u.lastName,p.parking_Name,r.rating from rating r inner join parking p on p.parkid=r.ParkingId inner join user u on u.Userid=r.UserId where r.rating>?;", nativeQuery = true)
	List<Object> findRatingAboveSpecificRating(double rating);
	
	@Query(value = "select u.firstName,u.lastName,p.parking_Name,r.rating from rating r inner join parking p on p.parkid=r.ParkingId inner join user u on u.Userid=r.UserId where r.rating<?;", nativeQuery = true)
	List<Object> findRatingBelowSpecificRating(double rating);
 * */
 