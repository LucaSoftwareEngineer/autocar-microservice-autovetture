package autocar.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import autocar.microservice.dto.TokenCheckResponse;

@FeignClient(name = "microservice-auth")
public interface AutocarMicroserviceAuthClient {

	@PostMapping("/api/user/token/check")
    public ResponseEntity<TokenCheckResponse> tokenCheck(@RequestHeader(name = "Authorization") String token);
	
}
