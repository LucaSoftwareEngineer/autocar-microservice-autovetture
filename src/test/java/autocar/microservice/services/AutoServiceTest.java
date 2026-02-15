package autocar.microservice.services;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import autocar.microservice.dto.RegistraAutoRequest;
import autocar.microservice.dto.TokenCheckResponse;
import autocar.microservice.exceptions.TokenIsNotValid;
import autocar.microservice.models.Auto;
import autocar.microservice.repositories.AutoRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoServiceTest {

	@InjectMocks
	AutoService autoService;
	
	@Mock
	AutoRepository autoRepository;
	
	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void registraAutoTest() throws TokenIsNotValid {
		Auto auto = new Auto();
		auto.setId(1L);
		auto.setMarca("Ferrari");
		auto.setColore("Rosso");
		auto.setModello("SF35");
		auto.setNumeroRuote(4);
		auto.setVenduta(false);
		auto.setCavalli(300);
		auto.setNumeroRuote(4);
		
		RegistraAutoRequest req = new RegistraAutoRequest();
		req.setMarca(auto.getMarca());
		req.setColore(auto.getColore());
		req.setModello(auto.getModello());
		req.setNumeroRuote(auto.getNumeroRuote());
		req.setCavalli(auto.getCavalli());
		
		String token = "token-test";
		
		TokenCheckResponse res = new TokenCheckResponse();
		res.setValido(true);
		
		when(autoRepository.save(any(Auto.class))).thenReturn(auto);
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.POST), any(HttpEntity.class), eq(TokenCheckResponse.class))).thenReturn(ResponseEntity.ok(res));
		
		assertEquals(autoService.registraAuto(token, req), auto);
	}
	
}
