package autocar.microservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import autocar.microservice.dto.TokenCheckResponse;

@RestController
public class AutocarMicroserviceAuthClientImpl {

	@Autowired
	private AutocarMicroserviceAuthClient autocarMicroserviceAuthClient;
	
	@PostMapping("/api/user/token/check")
	public ResponseEntity<TokenCheckResponse> tokenCheck(@RequestHeader(name = "Authorization") String token) {
		return autocarMicroserviceAuthClient.tokenCheck(token);
	}
	
}
