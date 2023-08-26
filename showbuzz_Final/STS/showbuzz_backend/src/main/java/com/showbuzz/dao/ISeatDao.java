package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Seat;

public interface ISeatDao extends JpaRepository<Seat, String> {

	@Query( value = "SELECT * from Seat WHERE seat_id = ?;",nativeQuery = true)
	Seat findBySeatId(String id);
	List<Seat> findAll();
	
	@Query( value = "SELECT seat_id from Seat;",nativeQuery = true)
	List<String> findAllId();
}
