package com.company.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.demo.entity.User;
import com.company.demo.model.CustomUserDetails;
import com.company.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username).orElse(null);

		if (user == null) {
			String msg = "Username " + username + " not found";
			System.out.println("----- " + msg + " -----");
			throw new UsernameNotFoundException(msg);
		}

		return new CustomUserDetails(user);
	}

}
