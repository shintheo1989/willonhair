package com.shintheo.willonhair.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shintheo.willonhair.entity.UserDao;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {
	Optional<UserDao> findByEmail(String email);
}
