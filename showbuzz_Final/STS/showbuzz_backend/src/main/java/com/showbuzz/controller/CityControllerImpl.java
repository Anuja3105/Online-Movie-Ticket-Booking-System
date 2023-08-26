package com.showbuzz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.CityDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.services.CityServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class CityControllerImpl {

	@Autowired
	private CityServiceImpl cityService;

	@GetMapping("/city/name/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		Map<String, Object> result = cityService.findByName(name);
		return Response.success(result);
	}

	@GetMapping("/city/find/{id}")
	public ResponseEntity<?> findByCityId(@PathVariable("id") int id) {
		CityDTO result = cityService.findById(id);
		return Response.success(result);
	}

	@PostMapping("/city")
	public ResponseEntity<?> addCity(@RequestBody CityDTO cityDto) {
		Map<String, Object> result = cityService.saveCity(cityDto);
		return Response.success(result);
	}

	@DeleteMapping("/city/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable int id) {
		Map<String, Object> result = cityService.deleteCity(id);
		return Response.success(result);
	}

	@PutMapping("/city/{id}")
	public ResponseEntity<?> updateCity(@PathVariable("id") int id, @RequestBody CityDTO city) {
		// NEED TO LOOK
		Map<String, Object> result = cityService.updateCity(id, city);
		return Response.success(result);
		// NEED TO LOOK
	}

	@GetMapping("/city/all")
	public ResponseEntity<?> findAll() {
		List<CityDTO> result = cityService.findAllCity();
		if(result.isEmpty())
			return Response.error("List is empty...!!!");
		return Response.success(result);
	}

}
