package com.showbuzz.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.ICityDao;
import com.showbuzz.dao.ITheatreDao;
import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.TheatreDTO;
import com.showbuzz.entity.City;
import com.showbuzz.entity.Theatre;
import com.showbuzz.entity.User;

@Transactional
@Service
public class TheatreServiceImpl {
private static SimpleDateFormat sdf;
	
	static{
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	@Autowired
	private ITheatreDao theatreDao;

	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public TheatreDTO findById (int id) {
		
		Theatre theatre = theatreDao.findById(id);
		return convertor.toTheatreDto(theatre);
	}
	
	public TheatreDTO findByName (String name) {
		
		Theatre theatre = theatreDao.findByName(name);
		return convertor.toTheatreDto(theatre);
	}
	
	public Map<String, Object> saveTheatre(TheatreDTO theatreDTO){
		Theatre theatre = convertor.toTheatreEntity(theatreDTO);
		System.out.println(theatreDTO.getManager_id());
		User user1 = userDao.findById(theatreDTO.getManager_id());
		user1.setRole("manager");
		User user = new User(theatreDTO.getManager_id());
		user = userDao.save(user1);
		
		City city= cityDao.findById(theatreDTO.getCity_id());
		city.addTheatre(theatre);
		theatre.setUser(user);
		theatre = theatreDao.save(theatre);

		System.out.println();
		System.out.println(user);
		System.out.println(theatre);
		System.out.println();
		return Collections.singletonMap("insertedId", theatre.getId());
		
	}
	
	public Map<String, Object> deleteTheatre(int id) {
		Theatre theatre = theatreDao.findById(id);
		if(theatre !=null) {
//			userDao.delete(theatre.getUser());
			theatreDao.deleteById(id);
			return Collections.singletonMap("Successfully Deleted!", id) ;
		}
		return Collections.singletonMap("Theatre does not exist with this theatre_id", null);
	}
	
	
	/*{
    "name" : "Asha square",
    "address" : "BM Road",
    "no_of_screen" : 3,
    "upi_id" : "ccc@axl"
}*/
	public Map<String, Object> updateTheatre(int id, TheatreDTO theatre) {
		Theatre t1 = theatreDao.findById(id);
		if(t1 != null) {
			
			if(theatre.getName()!= null)
				t1.setName(theatre.getName());
			if(theatre.getAddress()!= null)
				t1.setAddress(theatre.getAddress());
			if(theatre.getNo_of_screen()!= 0)
				t1.setNoOfScreen(theatre.getNo_of_screen());
			if(theatre.getKey1()!= null)
				t1.setKey1(theatre.getKey1());
			if(theatre.getKey2()!= null)
				t1.setKey2(theatre.getKey2());
			if(theatre.getManager_id()!= 0)
				t1.setUser(new User(  theatre.getManager_id()));
			t1=theatreDao.save(t1);
			
			return Collections.singletonMap("ChangedRows", t1.getId());
		}
		else {
			return Collections.singletonMap("Theatre does not exist with this theatre_id", null);
		}
	}

	public List<TheatreDTO> findAllTheatre() {
		List<Theatre> list = new ArrayList<>();
		list = theatreDao.findAll();

		return list.stream()
				.map(t -> convertor.toTheatreDto(t))
				.collect(Collectors.toList());
	}
	
	public List<TheatreDTO> findByMovieId(int id, String date, int city_id){
		List <Theatre> list = theatreDao.findByMovieId(id,date, city_id);
		return list.stream()
				.map(t -> convertor.toTheatreDto(t))
				.collect(Collectors.toList());
	}
	
	public TheatreDTO findByShowId (int id) {
		
		Theatre theatre = theatreDao.findByShowId(id);
		return convertor.toTheatreDto(theatre);
	}

	public TheatreDTO findByUserId (int id) {
		
		Theatre theatre = theatreDao.findByUserId(id);
		return convertor.toTheatreDto(theatre);
	}

}
