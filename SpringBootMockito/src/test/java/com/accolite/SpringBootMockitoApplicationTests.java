package com.accolite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.accolite.model.User;
import com.accolite.repository.UserRepository;
import com.accolite.service.UserService;

/*
 General flow : Controller -> Service -> Repository -> DB
 For testing, it is not a good practice to hit the repository many number of times.
 So we are mocking the repository to act like a DB and the control from service goes to mockito.
 */

@SpringBootTest
class SpringBootMockitoApplicationTests {
	@Autowired 
	private UserService userService;
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(1,"asd",34,"grt"),new User(2, "fer", 30, "trgr")).collect(Collectors.toList()));
		assertEquals(2, userService.getUsers().size());
	}
	
	@Test
	public void getUserByAddressTest() {
		when(userRepository.findByAddress("bangalore")).thenReturn(Stream.of(new User(1, "asd", 20, "fsv")).collect(Collectors.toList()));
		assertEquals(1, userService.getUserByAddress("bangalore").size());
	}
	
	@Test
	public void addUserTest() {
		User user= new User(12, "gree", 35, "ghtf");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.addUser(user) );
	}
	
	@Test
	public void deleteUserTest() {
		User user= new User(12, "gree", 35, "ghtf");
		userService.deleteUser(user);
		verify(userRepository,times(1)).delete(user);
	}
	
}
