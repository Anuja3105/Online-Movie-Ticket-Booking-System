package com.showbuzz.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IMovieDao;
import com.showbuzz.dao.IScreenDao;
import com.showbuzz.dao.IShowsDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.ShowDTO;
import com.showbuzz.entity.Movie;
import com.showbuzz.entity.Screen;
import com.showbuzz.entity.Shows;

@Service
@Transactional
public class ShowServicesImpl {
	
	private static SimpleDateFormat sdf;
	private static SimpleDateFormat sdf1;
	static{
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf1 = new SimpleDateFormat("hh:mm:ss");
	}
	@Autowired
	private IShowsDao showDao;
	
	@Autowired
	private IScreenDao screenDao;
	
	@Autowired
	private IMovieDao movieDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public Shows findById(int id) {
		Shows  shows = showDao.findById(id);
		return shows;
	}
	
	public List<ShowDTO> findAll(){
		List<Shows> showsList = showDao.findAll();
		return showsList.stream()
				.map(show -> convertor.toShowsDTO(show))
				.collect(Collectors.toList());
				
	}
	
	public Map<String , Object> saveShows(ShowDTO showDTO) throws ParseException{
		
		Shows show = convertor.toShowsEntity(showDTO);
		show.setMovie(new Movie(showDTO.getMovie_id()));
		Screen screen = screenDao.findById(showDTO.getScreen_id());
		screen.addShow(show);
		Movie m = movieDao.findById(showDTO.getMovie_id());
		m.addShows(show);
		showDao.save(show);
		return Collections.singletonMap("insertedId", show.getShow_id());
	}

	public Map<String, Object> updateShows(int id, ShowDTO showDto) throws ParseException {
		if(showDao.existsById(id)) {
			Shows updateShow = showDao.findById(id);
			showDto.setId(id);
			Shows show = convertor.toShowsEntity(showDto);
			updateShow = showDao.save(show);
			return Collections.singletonMap("changedRows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}

	public Map<String, Object> deleteShow(int id) {
		if(showDao.existsById(id)) {
			showDao.deleteById(id);
		return Collections.singletonMap("changedRows", 1);
	}
	return Collections.singletonMap("changedRows", 0);
	}


	
	public List<ShowDTO> findTodaysShow() throws ParseException {
		List<Shows> showsList = showDao.findAll();
		Date current_time = new Date();
		String c_time = sdf1.format(current_time);
		Date s_time = sdf1.parse(c_time);
//		System.out.println(current_time);
		return showsList.stream()
				.filter(show -> show.getShowdate().after(new Date()) && show.getShowtime().after(s_time))
				.map(show -> convertor.toShowsDTO(show))
				.collect(Collectors.toList());
	}

	public List<ShowDTO> findShowByMovieId(int id) {
		List<Shows> list = showDao.findByMovieId(id);
		return list.stream().map(s -> convertor.toShowsDTO(s)).collect(Collectors.toList());
	}
	
	/*	Find Show by Screen ID	*/

	public List<ShowDTO> findShowByScreenId(int id) {
		List<Shows> list = showDao.findByScreenId(id);
		return list.stream().map(s -> convertor.toShowsDTO(s)).collect(Collectors.toList());
	}
	
	/*	Find Show by Theatre ID	*/
	
	public List<ShowDTO> findShowByTheatreId(int id) {
		List<Shows> list = showDao.findByTheatreId(id);
		return list.stream().map(s -> convertor.toShowsDTO(s)).collect(Collectors.toList());
	}
	
	/*	Find Show by City ID	*/
	public List<ShowDTO> findShowByCityId(int id) {
		List<Shows> list = showDao.findByCityId(id);
		return list.stream().map(s -> convertor.toShowsDTO(s)).collect(Collectors.toList());
	}
	
	/*	Find show by booking date	*/
	public List<ShowDTO> findShowByBookingDate(String date) throws ParseException {
		Date showDate = sdf.parse(date);
		List<Shows> list = showDao.findByBookingDate(showDate);
		return list.stream().map(s -> convertor.toShowsDTO(s)).collect(Collectors.toList());
	}

	
	public ShowDTO findByShowId(int id) {
		Shows  shows = showDao.findById(id);
		return convertor.toShowsDTO(shows);
	}
	

	
}
