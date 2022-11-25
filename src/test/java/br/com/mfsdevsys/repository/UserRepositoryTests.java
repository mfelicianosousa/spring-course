package br.com.mfsdevsys.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mfsdevsys.domain.User;
import br.com.mfsdevsys.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {
    
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void AsaveTest() {
	
		User user = new User(null,"Marcelino","marcelino@gmail.com","123", Role.ADMINISTRATOR, null, null );
		User createdUser = userRepository.save( user );
		
		assertEquals(createdUser.getId(), 1L);
		
	}
	
	@Test
	public void updateTest() {
		User user = new User(1L,"Marcelino Sousa","marcelino.sousa@gmail.com","123", Role.ADMINISTRATOR, null, null );
		User updatedUser = userRepository.save( user );
		assertTrue( updatedUser.getName().contains("Marcelino Sousa"));
	}
	
	@Test
	public void getByIdTest() {
		String senha = "123";
		Optional<User> result = userRepository.findById(1L) ;
		User user = result.get();
		
		assertTrue( user.getPassword().contains(senha));
	}
	
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		assertEquals(users.size(), 1);
	}

	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("marcelino.sousa@gmail.com", "123");
		User loggedUser = result.get();
		
		assertEquals(loggedUser.getId(), 1L);
	}

}
