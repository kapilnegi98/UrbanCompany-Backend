package com.urbancompany.usermanagementservice.repository;



import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.usermanagementservice.entity.User;

@Repository
public class UserRepository {

	private Long seqId;
	private List<User> users;
	
	public User findById(Long userId) {
		return users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
	}

	public User findByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public void save(User user) {
		user.setId(nextId());
		users.add(user);
	}
	
	private Long nextId() {
		return seqId++;
	}

	@PostConstruct
	private void initializaRepo() {
		users = new LinkedList<>();
		User userAdmin = new User();
		userAdmin.setId(1001l);
		userAdmin.setName("Admin User");
		userAdmin.setEmail("kapiloct98@gmail.com");
		userAdmin.setMobile("1111111");
		userAdmin.setRole(UserRole.SERVICE_RECIEVER);
		userAdmin.setPincode(110089l);
		User userSP = new User();
		userSP.setId(1002l);
		userSP.setName("kapil");
		userSP.setEmail("karan.negi@gmail.com");
		userSP.setMobile("1111111");
		userSP.setRole(UserRole.SERVICE_PROVIDER);
		userSP.setPincode(110089l);
		User userSR = new User();
		userSR.setId(1003l);
		userSR.setName("Geeta Mehta");
		userSR.setEmail("parag@gmail.com");
		userSR.setMobile("9582843305");
		userSR.setRole(UserRole.SERVICE_RECIEVER);
		userSR.setPincode(110045l);
		
		users.add(userAdmin);
		users.add(userSP);
		users.add(userSR);
		seqId = 1004l;
	}
}
