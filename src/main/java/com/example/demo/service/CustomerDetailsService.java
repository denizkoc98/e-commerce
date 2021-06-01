package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.User;
import com.example.demo.repository.UserRepository;


@Service
@CrossOrigin("http://localhost:4200")

public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> customer =repository.findByUsername(username);
		/*if (customer==null)
		{
			throw new UsernameNotFoundException("User not found!");
		}

		return new CustomerDetails(customer);*/
		return customer.map(com.example.demo.UserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));
		
	}

}



