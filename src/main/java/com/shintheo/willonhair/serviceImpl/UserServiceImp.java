package com.shintheo.willonhair.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.UserRepository;
import com.shintheo.willonhair.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private JavaMailSender mailSender;

	@Override
	public UserDao submitUser(UserDao user) {
		log.info("======>UserService: Save new user ({})<======", user);
		log.info("Info: ici je génère un Id pour pour l'utilisateur venant de willOnHair");
		long leftLimit = 1L;
		long rightLimit = 10L;
		long newId = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
		return userRepository.save(user);
	}

	@Override
	public UserDao getUserById(long id) {
		log.info("======>UserService: Fetching user by id ({})<======", id);
		return userRepository.findById(id).get();
	}

	@Override
	public List<UserDao> getAllUsers() {
		log.info("======>UserService: Fetching all users<======");
		return (List<UserDao>) userRepository.findAll();
	}

	@Override
	public UserDao getUserByEmail(String email) {
		log.info("======>UserService: Fetching user by email {}<======", email);
		return userRepository.findByEmail(email).get();
	}

	@Override
	public UserDao updateUser(UserDao user) {
		UserDao userUpdate = userRepository.findById(user.getId()).get();
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setEmail(user.getEmail());
		userUpdate.setPhone(user.getPhone());
		log.info("======>UserService: update user<======");
		return userRepository.save(userUpdate);
	}

//	private boolean checkIfValidOldPassword(final UserDao user, final String oldPassword) {
//		return passwordEncoder.matches(oldPassword, user.getPassword());
//	}
//
//	public void updatePassword(String oldPassword, String newPassword, String userEmail) {
//		UserDao currentUser = this.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
//		if (!this.checkIfValidOldPassword(currentUser, oldPassword)) {
//			throw new RuntimeException("Please make sure you have entered the correct password ");
//		} else {
//			currentUser.setPassword(passwordEncoder.encode(newPassword));
//			userRepository.save(currentUser);
//		}
//	}

	@Override
	public UserDao deleteUser(UserDao user) {
		log.info("======>UserService: delete user ({})<======", user);
		user.setStatus(Status.DISABLED);
		return userRepository.save(user);
	}

	@Override
	public void updatePassword(String oldPassword, String newPassword, String userEmail) {
		// TODO Auto-generated method stub

	}

}
