package com.shintheo.willonhair.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.shintheo.willonhair.entity.UserDao;

public interface UserRepository extends CrudRepository<UserDao, Long> {
	Optional<UserDao> findByEmail(String email);
}
