package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.ServiceProviderDao;
import com.shintheo.willonhair.entity.UserDao;

public interface ServiceProviderRepository  extends JpaRepository<ServiceProviderDao, Long> {
	List<ServiceProviderDao> findByUser(UserDao user);
	List<ServiceProviderDao> findByService(ServiceDao service);
}
