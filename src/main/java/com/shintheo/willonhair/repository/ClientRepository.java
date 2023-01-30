package com.shintheo.willonhair.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.UserDao;

public interface ClientRepository extends JpaRepository<UserDao, Long> {

}
