package autocar.microservice.services;

import autocar.microservice.dto.RegistraAutoRequest;
import autocar.microservice.dto.TokenCheckResponse;
import autocar.microservice.exceptions.TokenIsNotValid;
import autocar.microservice.models.Auto;
import autocar.microservice.repositories.AutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class AutoService {

    @Value("${autocar.microservice.auth.token.check}")
    private String tockenCheck;
    private final AutoRepository autoRepository;
    private final RestTemplate restTemplate;

    public Auto registraAuto(String token, RegistraAutoRequest request) throws TokenIsNotValid {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<TokenCheckResponse> response = restTemplate.exchange(
            tockenCheck,
            HttpMethod.POST,
            entity,
            TokenCheckResponse.class
        );

        if (response.getBody().isValido()) {
            Auto auto = new Auto();
            auto.setMarca(request.getMarca());
            auto.setModello(request.getModello());
            auto.setColore(request.getColore());
            auto.setNumeroRuote(request.getNumeroRuote());
            auto.setCavalli(request.getCavalli());
            return autoRepository.save(auto);
        } else {
            throw new TokenIsNotValid();
        }
    }

}
