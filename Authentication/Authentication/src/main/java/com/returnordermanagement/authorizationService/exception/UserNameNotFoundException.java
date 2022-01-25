package com.returnordermanagement.authorizationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND,reason="Card not found")
public class UserNameNotFoundException {
	private static final long serialVersionUID = 1L;
    public UserNameNotFoundException(String msg) {
		super();
	}

}
