package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Screen;

public interface IScreenDao extends JpaRepository<Screen, Integer> {

	Screen findById(int id);
	List<Screen> findAll();
	
	@Query(value = "SELECT * FROM screen s where s.theatre_id = ? AND s.screen_no = ?;",nativeQuery = true)
	Screen findByTheatreIdAndScreenNo(int theatre_id, int screen_no);
	
	
	@Query(value = "SELECT COUNT(screen_no) FROM screen WHERE theatre_id = ?;",nativeQuery = true)
	int countNoOfScreen(int theatre_id);
	
	@Query(value="SELECT * FROM screen s WHERE s.theatre_id = ?;",nativeQuery = true)
	List<Screen> findByTheatreId(int id);
}
