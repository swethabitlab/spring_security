package com.bitlabs.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bitlabs.entity.UserInfo;
import com.bitlabs.entity.UserRole;

@SuppressWarnings("serial")
public class CustomUserDetails extends UserInfo implements UserDetails {

	private String username;
	private String password;
	
	Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(UserInfo byUsername) {
		this.username=byUsername.getUsername();
		this.password=byUsername.getPassword();
		
	List<GrantedAuthority>auths=new ArrayList<>();
	
	for(UserRole role:byUsername.getRoles()) {
		
		auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		
	}
			this.authorities=auths;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return authorities;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	public boolean isAccountNonLocked() {
		
		return true;
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	public boolean isEnabled() {
		return true;
	}
	
}
