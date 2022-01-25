package com.returnordermanagement.authorizationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.returnordermanagement.authorizationService.model.AuthenticationResponse;
import com.returnordermanagement.authorizationService.util.JwtUtil;

@Component
public class ValidateService {

	@Autowired
	private JwtUtil jwtutil;
	
	/**
	 * validating the token
	 * @param token
	 * @return
	 */

	public AuthenticationResponse validate(String token) {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

			final String jwt = token.substring(7);
			authenticationResponse.setJwtToken(jwt);
			
			if (jwtutil.validateToken(jwt)) {
				authenticationResponse.setValid(true);
				authenticationResponse.setMessage("Validation Successfull");
			} else {
				authenticationResponse.setValid(false);
				authenticationResponse.setMessage("Invalid Token");
			}
		
		return authenticationResponse;
	}
}
