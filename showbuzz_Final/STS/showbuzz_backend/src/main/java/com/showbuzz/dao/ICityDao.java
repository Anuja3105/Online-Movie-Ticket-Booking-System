package com.showbuzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbuzz.entity.City;

@Repository
public interface ICityDao extends JpaRepository<City, Integer> {

	City findById(int id);
	City findByName(String name);
	
	
	
}
