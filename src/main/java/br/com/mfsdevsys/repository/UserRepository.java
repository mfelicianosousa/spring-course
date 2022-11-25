package br.com.mfsdevsys.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mfsdevsys.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	// public User FindByName( String name);
	
	@Query("SELECT u FROM user u WHERE u.email =?1 AND u.password =?2")
	public Optional<User> login( String email, String password);
	
}
