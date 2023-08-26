package com.showbuzz.services;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IMovieDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.MovieDTO;
import com.showbuzz.entity.Movie;

@Transactional
@Service
public class MovieServiceImpl {
	
	@Autowired
	private IMovieDao moviedao;
	
	@Autowired
	private DtoEntityConvertor converter;
	
	//Add movie-------------------
	public Map<String,Object> addMovie(MovieDTO movieDto){
		Movie movie = converter.toMovieEntity(movieDto);				
		movie = moviedao.save(movie);
		return Collections.singletonMap("insertedId", movie.getId());
	}
	
	//Show All movies--------------------
	public List<MovieDTO> findAllMovie(){
		List<Movie> movieList =moviedao.findAll();
		return movieList.stream()
				.map(movie -> converter.toMovieDto(movie))
				.collect(Collectors.toList());
	}
	
	//Find movie By its id-------------------------
	public MovieDTO findMovieById(int movieId) {
		Movie movie = moviedao.findById(movieId);
		return converter.toMovieDto(movie);
	}
	
	//edit movie field-----------------------------
	public Map<String,Object> editMovie(int id,MovieDTO movieDto){
		if(moviedao.existsById(id)) {
			MovieDTO movie = findMovieById(id);
			Movie updateMovie = converter.toMovieEntity(movie);
			updateMovie.setCategory(movieDto.getCategory());
			updateMovie.setMovie_details(movieDto.getMovie_details());
			updateMovie.setGenre(movieDto.getGenre());
			updateMovie.setStatus(movieDto.getStatus());
			updateMovie.setId(id);
			updateMovie = moviedao.save(updateMovie);
			return Collections.singletonMap("changeRows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
	
	//delete movie field-----------------------------
	public Map<String,Object> deleteMovie(int id){
		if(moviedao.existsById(id)) {
			Movie movie=moviedao.findById(id);
			moviedao.delete(movie);
			return Collections.singletonMap("affectedRow", 1);
		}
		return Collections.singletonMap("affectedRow", 0);
	}
	
	public List<MovieDTO> findMovieByName(String name){
		List<Movie> movieList = moviedao.findByName(name);
		return movieList.stream()
				.map(movie -> converter.toMovieDto(movie))
				.collect(Collectors.toList());
	}
	
	public List<MovieDTO> findByMoviesCategory(String category){
		List<Movie> movieList = moviedao.findByCategory(category);
		return movieList.stream()
				.map(mo -> converter.toMovieDto(mo))
				.collect(Collectors.toList());
	}
	
	public List<MovieDTO> findByMoviesGenre(String genre){
		List<Movie> movieList = moviedao.findByGenre(genre);
		return movieList.stream()
				.map(mo -> converter.toMovieDto(mo))
				.collect(Collectors.toList());
	}

	public List<MovieDTO> findByCityId(int id){
		List<Movie> movieList = moviedao.findByCityId(id);
		return movieList.stream()
				.map(mo -> converter.toMovieDto(mo))
				.collect(Collectors.toList());
	}

	public MovieDTO findByShowId(int id) {
		Movie movie = moviedao.findByShowId(id);
		return converter.toMovieDto(movie);
	}
	
	
}//-p1999
