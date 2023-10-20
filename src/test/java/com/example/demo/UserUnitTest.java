package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@SpringBootTest
public class UserUnitTest {

	@Autowired
	UserRepo usersRepository;

	@Test
	public void testSaveUsers() {
		User users = new User();
		users.setId(1L);
		users.setName("Test");
		users.setAge("18");
		users.setUserName("Test");
		users.setEmail("Test@gmail.com");
		users.setMobile("0771425478");
		usersRepository.save(users);
		assertNotNull(usersRepository.findById(1L).get());
	}

	@Test
	public void testReadAll() {
		List<User> list = usersRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}



	@Test
	public void testDelete() {
		usersRepository.deleteById(2L);
		assertThat(usersRepository.existsById(2L)).isFalse();
	}

}
