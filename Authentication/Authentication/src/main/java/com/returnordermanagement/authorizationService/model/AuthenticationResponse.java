package com.returnordermanagement.authorizationService.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthenticationResponse {

	private String jwtToken;
	private Boolean valid;
	private String message;

}
