package autocar.microservice.services;

import autocar.microservice.dto.*;
import autocar.microservice.exceptions.TokenIsNotValid;
import autocar.microservice.models.Auto;
import autocar.microservice.repositories.AutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AutoService {

    @Value("${autocar.microservice.auth.token.check}")
    private String tockenCheck;
    private final AutoRepository autoRepository;
    private final RestTemplate restTemplate;

    public Boolean checkToken(String token) {
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

        return response.getBody().isValido();
    }

    public Auto registraAuto(String token, RegistraAutoRequest request) throws TokenIsNotValid {
        if (this.checkToken(token)) {
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

    public List<Auto> getElencoAuto(String token) throws TokenIsNotValid {
        if (this.checkToken(token)) {
            return autoRepository.findAll();
        } else {
            throw new TokenIsNotValid();
        }
    }

    public AutoReportResponse getReportAuto(String token) throws TokenIsNotValid {
        if (this.checkToken(token)) {
            List<Auto> elencoAuto = autoRepository.findAll();

            int numeroAutoConTarga = 0;
            int numeroAutoSenzaTarga = 0;

            Iterator iterator = elencoAuto.listIterator();
            while (iterator.hasNext()) {
                Auto auto = (Auto) iterator.next();
                if (auto.getTarga() != null)
                    numeroAutoConTarga++;
                numeroAutoSenzaTarga++;
            }

            AutoReportResponse res = new AutoReportResponse();
            res.setAutoConTarga(numeroAutoConTarga);
            res.setAutoSenzaTarga(numeroAutoSenzaTarga);
            res.setAutoComplessive(elencoAuto.size());

            return res;

        } else {
            throw new TokenIsNotValid();
        }
    }

    public ImpostaTargaResponse impostaTarga(String token, ImpostaTargaRequest req) throws AuthenticationException, TokenIsNotValid {
        if (this.checkToken(token)) {
            Auto auto = autoRepository.findById(req.getIdAuto()).get();
            if (auto == null)
                throw new AuthenticationException();
            auto.setTarga(req.getTarga());
            autoRepository.save(auto);
            ImpostaTargaResponse res = new ImpostaTargaResponse();
            res.setIdAuto(auto.getId());
            return res;
        } else {
            throw new TokenIsNotValid();
        }
    }

    public boolean eliminaAuto(String token, Long id) throws TokenIsNotValid {
        if (this.checkToken(token)) {
            try {
                autoRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        throw new TokenIsNotValid();
    }

}
