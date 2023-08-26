package com.showbuzz.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Shows;

public interface IShowsDao extends JpaRepository<Shows, Integer>{

	Shows findById(int id);
	List<Shows> findAll();
	List<Shows> findByShowdate(Date showdate);
	List<Shows> findByShowtime(Date showtime);
	@Query(value = "SELECT * FROM shows s WHERE s.movie_id = ?;",nativeQuery = true)
	List<Shows> findByMovieId(int id);
//	List<Shows> findByMovie(Movie movie_id);
	@Query(value = "SELECT * FROM shows s WHERE s.screen_id = ?;",nativeQuery = true)
	List<Shows> findByScreenId(int id);
	
	@Query(value="select * from shows where screen_id IN (select screen_id from screen where theatre_id IN (select theatre_id from theatre where city_id = ?));", nativeQuery = true)
	List<Shows> findByCityId(int id);

	@Query(value="select * from shows where screen_id IN (select screen_id from screen where theatre_id = ? );", nativeQuery = true)
	List<Shows> findByTheatreId(int id);
	
	@Query(value = "SELECT * FROM shows WHERE showdate > ?;",nativeQuery = true)
	List<Shows> findByBookingDate(Date date);
	
}
