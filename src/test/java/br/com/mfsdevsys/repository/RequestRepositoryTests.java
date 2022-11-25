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
import br.com.mfsdevsys.domain.User;
import br.com.mfsdevsys.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

	@Autowired 
	private RequestRepository requestRepository;
	
	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Laptop HP","HP laptop new model", new Date(), RequestState.OPEN, owner, null);
		
		Request createdRequest = requestRepository.save(request) ;
		assertEquals(createdRequest.getId(), 1L);
	}
	
	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(1L, "Laptop HP","HP laptop new model, RAM 16 GB", null, RequestState.OPEN, owner, null);
		
		Request updateRequest = requestRepository.save(request) ;
		assertEquals(updateRequest.getId(), 1L);
		assertTrue( updateRequest.getDescription().contains("HP laptop new model, RAM 16 GB"));
	}
	
	@Test
	public void getByIdTest() {

		Optional<Request> result = requestRepository.findById(1L) ;
		Request request = result.get();
		
		assertTrue( request.getSubject().contains("Laptop HP"));
	}
	
	@Test
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		assertEquals(requests.size(), 1);
	}
	
	@Test
	public void listByOwnerTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertEquals(requests.size(), 1);
		
	}
	
	@Test
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		assertEquals(affectedRows, 1);
	}
	
	
}
