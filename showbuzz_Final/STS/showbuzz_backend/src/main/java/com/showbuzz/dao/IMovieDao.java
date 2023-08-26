package com.showbuzz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showbuzz.entity.Movie;

public interface IMovieDao extends JpaRepository<Movie, Integer> {
	
	Movie findById(int id);
	List<Movie> findByCategory(String category);
	List<Movie> findByName(String name);
	List<Movie> findByGenre(String genre);
	
	@Query(value = "SELECT * FROM movie m WHERE m.movie_id IN (SELECT s.movie_id FROM shows s WHERE s.screen_id IN (SELECT sc.screen_id FROM screen sc WHERE sc.theatre_id IN (SELECT t.theatre_id FROM theatre t WHERE city_id = ?)));",nativeQuery = true)
	List<Movie> findByCityId(int id);
	
	@Query(value = "SELECT * FROM movie m WHERE m.movie_id = (SELECT s.movie_id FROM shows s WHERE s.show_id = ?);", nativeQuery = true)
	Movie findByShowId(int id);
	
	@Query(value="SELECT * FROM movie order by movie_id desc;",nativeQuery = true)
	List<Movie> findAll();
	
}//-p1999
