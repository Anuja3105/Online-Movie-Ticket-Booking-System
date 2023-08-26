package com.showbuzz.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.OrderDTO;
import com.showbuzz.dto.Response;
import com.showbuzz.services.OrderServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class OrderControllerImpl
{
		@Autowired
		private OrderServiceImpl orderServiceImpl;
		
		@GetMapping("/orders/user/{id}")
		public ResponseEntity<?> findByUserId(@PathVariable("id") int id)
		{
			List<OrderDTO> orderList=new ArrayList<>();
			orderList=orderServiceImpl.findBbyUserId(id);
			return Response.success(orderList);
		}
		
		@GetMapping("/orders")
		public ResponseEntity<?> findAllOrders()
		{
			List<OrderDTO> list=orderServiceImpl.findAll();
			return Response.success(list);
		}
		
		@PostMapping("/orders")
		public ResponseEntity<?> addOrders(@RequestBody OrderDTO orderdto) throws ParseException
		{
			System.out.println(orderdto);
			Map<String, Object> result=orderServiceImpl.addOrder(orderdto);
			return Response.success(result);
		}
		
		@GetMapping("/orders/show/{id}")
		public ResponseEntity<?> findbyShowId(@PathVariable("id") int id)
		{
			List<OrderDTO> list=orderServiceImpl.findByShowId(id);
			return Response.success(list);
		}
		

		@GetMapping("/orders/screen/{id}")
		public ResponseEntity<?> findbyScreenId(@PathVariable("id") int id)
		{
			List<OrderDTO> list=orderServiceImpl.findByScreenId(id);
			return Response.success(list);
		}
		
		@GetMapping("/orders/theatre/{id}")
		public ResponseEntity<?> findbyTheatreId(@PathVariable("id") int id)
		{
			List<OrderDTO> list=orderServiceImpl.findByTheatreId(id);
			return Response.success(list);
		}
		
		
		@GetMapping("/orders/movie/{id}")
		public ResponseEntity<?> findbyMovieId(@PathVariable("id") int id)
		{
			List<OrderDTO> list=orderServiceImpl.findByMovieId(id);
			return Response.success(list);
		}
		
		

		@GetMapping("/orders/showdate")
		public ResponseEntity<?> findbyShowDate(@RequestParam(name="show_date") String showDate) throws ParseException
		{
			List<OrderDTO> list=orderServiceImpl.findByShowDate(showDate);
			return Response.success(list);
		}
		
		
}

