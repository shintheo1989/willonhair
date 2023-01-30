package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.ClientRepository;
import com.shintheo.willonhair.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository ClientRepo;

	@Override
	public UserDao createClient(UserDao Client) {
		return ClientRepo.save(Client);
	}

	@Override
	public UserDao updateClient(UserDao Client, Long ClientId) {
		Client.setId(ClientId);
		return ClientRepo.save(Client);
	}

	@Override
	public void deleteClient(Long ClientId) {
		ClientRepo.deleteById(ClientId);
	}
	
	
	public Optional<UserDao> findClientById(Long ClientId) {
		return ClientRepo.findById(ClientId);
	}
	
	@Override
	public List<UserDao> fetchAll() {
		return ClientRepo.findAll();
	}
}
