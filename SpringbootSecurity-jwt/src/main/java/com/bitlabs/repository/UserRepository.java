package com.bitlabs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.entity.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo,Long>{

	public UserInfo findByUsername(String username);
	
	public boolean existsByUsername(String username);
}
