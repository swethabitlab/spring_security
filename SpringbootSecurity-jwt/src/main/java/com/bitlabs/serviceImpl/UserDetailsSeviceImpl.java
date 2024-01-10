package com.bitlabs.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bitlabs.entity.UserInfo;
import com.bitlabs.repository.UserRepository;

@Component
public class UserDetailsSeviceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	UserInfo user=userRepository.findByUsername(username);
	
	if(user==null) {
		throw new UsernameNotFoundException("could not found user...!");
	}
		return new CustomUserDetails(user);
	}
	
	

}
