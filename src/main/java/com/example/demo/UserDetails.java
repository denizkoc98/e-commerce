package com.example.demo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.User;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	
private User customer;
	
	
	public UserDetails(User customer) {
		
		this.customer = customer;
	}


	/*public CustomerDetails (Customer customer) {
		
		this.username=customer.getUsername();
		this.password=customer.getPassword();
		this.authorities = Arrays.stream(customer.getRoles().split(","))
	                .map(SimpleGrantedAuthority::new)
	                .collect(Collectors.toList());
	
	
	}*/
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("CUSTOMER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return customer.getUsername();
	}

	public int getId() {
		// TODO Auto-generated method stub
		return customer.getId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
