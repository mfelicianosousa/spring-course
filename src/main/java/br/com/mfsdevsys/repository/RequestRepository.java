package br.com.mfsdevsys.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mfsdevsys.domain.Request;
import br.com.mfsdevsys.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository< Request, Long > {

	public List< Request > findAllByOwnerId( Long id );
	
	@Transactional(readOnly=false)
	@Modifying
	@Query("UPDATE request r SET r.state = ?2 WHERE r.id = ?1")
	public int updateStatus(Long id, RequestState state);
}
