package com.showbuzz.services;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.showbuzz.dao.IUserDao;
import com.showbuzz.dto.Credential;
import com.showbuzz.dto.DtoEntityConvertor;
import com.showbuzz.dto.UserDTO;
import com.showbuzz.entity.User;

@Transactional
@Service
public class UserServicesImpl {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DtoEntityConvertor convetor;
	
	public UserDTO findById(int userId) {
		User user = userDao.findById(userId);
		return convetor.toUserDto(user);
	}
	
	public UserDTO findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		return convetor.toUserDto(user);
	}
	
	public UserDTO findUserByEmailAndPassword(Credential cred) {
		User dbUser = userDao.findByEmail(cred.getEmail());
		System.out.println(cred.getEmail());
		System.out.println(cred.getPassword());
		String rawPassword = cred.getPassword();
		if(dbUser != null && passwordEncoder.matches(rawPassword, dbUser.getPassword())) {
			UserDTO result = convetor.toUserDto(dbUser);
			result.setPassword("********");
			return result;
		}
		return null;
	}
	
	public Map<String, Object> saveUser(UserDTO userDTO){
		User user = userDao.findByEmail(userDTO.getEmail());
		
		if(user == null) {
		String rawPassword = userDTO.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		userDTO.setPassword(encPassword);
		User newUser = convetor.toUserEntity(userDTO);
		newUser = userDao.save(newUser);
		newUser.setRole("user");
		return Collections.singletonMap("insertedId", newUser.getUser_id());
		}
		else
		{
			return Collections.singletonMap("msg", "User with this email is already exist...!!!");
		}
	}
	public Map<String, Object> updateUser(int id,User user){
		if(userDao.existsById(id)) {
			User updatedUser = userDao.findById(id);
			user.setUser_id(id);
			user.setFirst_name(updatedUser.getFirst_name());
			user.setLast_name(updatedUser.getLast_name());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setRole(updatedUser.getRole());
			System.out.println();
			System.out.println(user.getGender());
			System.out.println();
			updatedUser = userDao.save(user);
			return Collections.singletonMap("changedRows", 1);
		}
		return Collections.singletonMap("changedRows", 0);
	}
	
	
	public Map<String, Object> deleteUser(int id){
		if(userDao.existsById(id)) {
			userDao.deleteById(id);
			return Collections.singletonMap("deletedRow", 1);
		}
		return Collections.singletonMap("deletedRow", 0);
	}
	
	
	public UserDTO userDetails(int id){
		User user = userDao.findById(id);
		return  convetor.toUserDetailsDto(user);
	}
	
	
}
