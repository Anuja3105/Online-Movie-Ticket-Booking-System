package com.showbuzz.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.showbuzz.entity.Theatre;

@Repository
public interface ITheatreDao extends JpaRepository<Theatre, Integer>{

	Theatre findById(int id);
	Theatre findByName(String name);
	Theatre findByCity_Id(int city_id);
	
	@Query(value = "SELECT * FROM theatre t WHERE t.theatre_id IN (SELECT sc.theatre_id FROM screen sc WHERE sc.screen_id IN (SELECT s.screen_id FROM shows s WHERE s.movie_id = ? AND s.showdate >= ? )) AND t.city_id=?;", nativeQuery = true)
	List<Theatre> findByMovieId(int id, String date, int city_id);

	@Query(value = "SELECT * FROM theatre t WHERE t.theatre_id IN (SELECT sc.theatre_id FROM screen sc WHERE sc.screen_id IN (SELECT s.screen_id FROM shows s WHERE s.show_id = ? ));", nativeQuery = true)
	Theatre findByShowId(int id);
	
	@Query(value = "SELECT * FROM theatre t WHERE t.user_id = ?",nativeQuery = true)
	Theatre findByUserId(int id);
	
}
