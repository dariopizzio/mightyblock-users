package com.mightyblock.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Microservice that implements all the necessary functions for the user model
 *
 * @author  Dario Pizzio
 * @version 1.0
 * @since   2021-08-16
 */
@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
