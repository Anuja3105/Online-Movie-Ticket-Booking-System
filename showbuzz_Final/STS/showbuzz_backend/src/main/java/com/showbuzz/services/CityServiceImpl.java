package com.showbuzz.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.ICityDao;
import com.showbuzz.dto.CityDTO;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.entity.City;

@Transactional
@Service
public class CityServiceImpl {

	@Autowired
	private ICityDao cityDao;
	
	@Autowired
	private DtoEntityConvertor convertor;
	
	public CityDTO findById(int id) {
	
		City city = cityDao.findById(id);
		return convertor.toCityDto(city);
	}
	
	public Map<String, Object> findByName(String name) {
		
		City city = cityDao.findByName(name);
		if(city != null)
			return Collections.singletonMap("city found", convertor.toCityDto(city)) ;
		else
			return Collections.singletonMap("city not found with this name", null) ;
	}
	
	
	public Map<String, Object> saveCity(CityDTO cityDTO){
		City city = convertor.toCityEntity(cityDTO);
		city = cityDao.save(city);
		return Collections.singletonMap("insertedId", city.getId());
	}
	
	public Map<String, Object> deleteCity(int id) {
		if(cityDao.existsById(id)) {
			cityDao.deleteById(id);
			return Collections.singletonMap("City Deleted!",id) ;
		}
		else
			return Collections.singletonMap("City Not Found with this id ",id) ;
	}
	
	public Map<String,Object> updateCity(int id,CityDTO city) {
		
		City existingCity = cityDao.findById(id);
		if(existingCity != null) {
			city.setCity_id(id);			
			City findCity =  convertor.toCityEntity(city);
			existingCity = cityDao.save(findCity);
			return Collections.singletonMap("changedRows", 1); 
		}
		return Collections.singletonMap("changedRows", 0); 
	}
	
	public List<CityDTO> findAllCity(){
		List<City> cityList = cityDao.findAll();
		return cityList.stream()
				.map(c -> convertor.toCityDto(c))
				.collect(Collectors.toList());
	}
	
	
	
}
