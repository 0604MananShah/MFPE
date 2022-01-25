package com.returnordermanagement.authorizationService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanagement.authorizationService.exception.BadCredentialException;
import com.returnordermanagement.authorizationService.model.AuthenticationRequest;
import com.returnordermanagement.authorizationService.model.AuthenticationResponse;
import com.returnordermanagement.authorizationService.service.MyUserDetailsService;
import com.returnordermanagement.authorizationService.service.ValidateService;
import com.returnordermanagement.authorizationService.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private ValidateService validateService;
	
	@Autowired
	private AuthenticationResponse res;


	/**
	 * Method-POST, http://localhost:8084/login
	 * 
	 * to allow login of a user only after verifying
	 * that the user trying to use this service is authenticated or not
	 * 
	 * @param userlogincredentials
	 * @return
	 */

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws BadCredentialException {
        
		log.info("Login Authenticating");
		 String jwt=null;
		Boolean flag=false;
		String message="invalid details";
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	         
			 jwt = jwtTokenUtil.generateToken(userDetails);
            message="Token will Expire in 30 min";
            flag=true;
			return ResponseEntity.ok(new AuthenticationResponse(jwt,flag, message));  
			
			
		} catch (Exception e) {
		    if(flag == false) {
		    	return ResponseEntity.ok(new AuthenticationResponse(jwt,flag,message));
		    }
			
			throw new BadCredentialException();
		}
		
        
		
		
	}
	
	

	/*
	 * validating token extraction from authorization header>> check the validity of
	 * token>> return an athenticationResponse Instance with two attributes String
	 * jwtToken , Boolean valid;
	 * 
	 * 
	 */

	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {

		log.info("Validate token");
		if (jwtTokenUtil.validateToken(token)) {
			res.setJwtToken(jwtTokenUtil.extractUsername(token));
			res.setValid(true);
		} else
			{
			res.setValid(false);
			}
		
		return validateService.validate(token);
	}

}
