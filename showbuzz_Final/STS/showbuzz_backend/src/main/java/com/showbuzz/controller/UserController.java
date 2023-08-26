package com.showbuzz.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showbuzz.dto.Credential;
import com.showbuzz.dto.Response;
import com.showbuzz.dto.UserDTO;
import com.showbuzz.entity.User;
import com.showbuzz.services.UserServicesImpl;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserServicesImpl userService;
	
	@PostMapping("/user/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody Credential cred){
		UserDTO userDto = userService.findUserByEmailAndPassword(cred);
		if(userDto == null)
			return Response.error("user not found");
		return Response.success(userDto);
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDTO userDto){
		
		Map<String, Object> result = userService.saveUser(userDto);
		return Response.success(result);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id,@RequestBody User user){
		Map<String, Object> result = userService.updateUser(id,user);
		return Response.success(result);
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
		Map<String, Object> result = userService.deleteUser(id);
		return Response.success(result);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findUserById(@PathVariable("id") int id){
		UserDTO result = userService.userDetails(id);
		return Response.success(result);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex){
		return Response.error(ex.getMessage());
	}
		
}
