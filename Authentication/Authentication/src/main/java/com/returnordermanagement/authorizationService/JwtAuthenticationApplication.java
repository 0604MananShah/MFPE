package com.returnordermanagement.authorizationService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.returnordermanagement.authorizationService.Repository.UserRepository;
import com.returnordermanagement.authorizationService.model.MyUser;

/**
 * 
 * @author bhargav vaikunta
 *
 */
 

@SpringBootApplication

public class JwtAuthenticationApplication {
	
	@Autowired
    private UserRepository repository;
	

   
	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

}
