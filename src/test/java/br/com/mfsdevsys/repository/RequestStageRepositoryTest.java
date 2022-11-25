package br.com.mfsdevsys.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mfsdevsys.domain.Request;
import br.com.mfsdevsys.domain.RequestStage;
import br.com.mfsdevsys.domain.User;
import br.com.mfsdevsys.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTest {

	@Autowired 
	private RequestStageRepository requestStageRepository;
	
	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request();
		request.setId(1L);
		
		RequestStage stage = new RequestStage(null, "Laptop HP Intel core i7 16gb RAM 512GB SSD Tela 15,6, Window 11", new Date(), RequestState.CLOSE, request, owner);
		
		RequestStage createdStage = requestStageRepository.save(stage) ;
		assertEquals(createdStage.getId(), 1L);
	}
	

	@Test
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stage = result.get();
		
		assertTrue( stage.getDescription().contains("Laptop HP Intel core i7 16gb RAM 512GB SSD Tela 15,6, Window 11"));
		
	}
	
	@Test
	public void listByRequest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertEquals(stages.size(), 1);
	}
}

