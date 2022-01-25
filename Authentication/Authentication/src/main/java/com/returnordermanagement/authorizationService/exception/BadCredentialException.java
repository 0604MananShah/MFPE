package com.returnordermanagement.authorizationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.returnordermanagement.authorizationService.model.AuthenticationResponse;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Username/Password incorrect")
public class BadCredentialException extends Exception {

}
