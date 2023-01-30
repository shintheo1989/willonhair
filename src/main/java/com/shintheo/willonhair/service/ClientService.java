package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.UserDao;

public interface ClientService {
	Optional<UserDao> findClientById(Long clientId);

	List<UserDao> fetchAll();

	UserDao createClient(UserDao client);
	
	UserDao updateClient(UserDao client, Long clientId);
	
	void deleteClient(Long clientId);
}
