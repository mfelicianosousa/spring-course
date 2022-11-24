package br.com.mfsdevsys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mfsdevsys.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User FindByName( String name);
	
	@Query("SELECT FROM User WHERE email =?1 AND password =?2")
	public Optional<User> Login( String email, String password);
	
}
