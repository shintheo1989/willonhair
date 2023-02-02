package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDao submitUser(UserDao user) {
		log.info("======>UserService: Save new user ({})<======", user);
		log.info("Info: ici je génère un Id pour pour l'utilisateur venant de willOnHair");
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

	@Override
	public UserDao deleteUser(UserDao user) {
		log.info("======>UserService: delete user ({})<======", user);
		user.setStatus(Status.DISABLED);
		return userRepository.save(user);
	}

	private void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("informatique@willonhair.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
		log.info("Mail sended successfully...");
	}

	@Override
	public void resetPassword(String emailAddress) {
		String body = "Utilisez le code suivant pour accéder à votre compte: ";
		String subject = "Code de Réinitialisation Ivisas-Affaires";
		Optional<UserDao> user = userRepository.findByEmail(emailAddress);
		if (!user.isPresent()) {
			throw new RuntimeException("Cette adresse email est introuvable!!");
		} else {
			String code = RandomStringUtils.randomAlphanumeric(8);
			this.sendEmail(user.get().getEmail(), subject, body + code);
			user.get().setPassword(passwordEncoder.encode(code));
			userRepository.save(user.get());
		}
	}

}
