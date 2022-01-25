package com.returnordermanag.authorizationService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.returnordermanagement.authorizationService.model.AuthenticationResponse;
import com.returnordermanagement.authorizationService.service.ValidateService;
import com.returnordermanagement.authorizationService.util.JwtUtil;

public class ValidateServiceTest {
	
	UserDetails userDetails;
	
	@Test
	void testValidate() {
		Boolean authenticationResponse;
		ValidateService validateService=new ValidateService();
		JwtUtil jwtUtil=new JwtUtil();
		userDetails = new User("Shivam", "shivam", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userDetails);
		authenticationResponse = jwtUtil.validateToken(generateToken);
		assertEquals(true,authenticationResponse);
	}

}
