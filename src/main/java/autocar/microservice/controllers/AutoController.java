package autocar.microservice.controllers;

import autocar.microservice.dto.AutoReportResponse;
import autocar.microservice.dto.ImpostaTargaRequest;
import autocar.microservice.dto.ImpostaTargaResponse;
import autocar.microservice.dto.RegistraAutoRequest;
import autocar.microservice.exceptions.TokenIsNotValid;
import autocar.microservice.models.Auto;
import autocar.microservice.services.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auto/")
public class AutoController {

    private final AutoService autoService;

    @PostMapping("registra")
    public ResponseEntity<Auto> registraAuto(
        @RequestHeader(name = "Authorization") String token,
        @RequestBody RegistraAutoRequest request
    ) {
        try {
            return ResponseEntity.ok(autoService.registraAuto(token, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("elenco")
    public ResponseEntity<List<Auto>> getElencoAuto(
        @RequestHeader(name = "Authorization") String token
    ) {
        try {
            return ResponseEntity.ok(autoService.getElencoAuto(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("report")
    public ResponseEntity<AutoReportResponse> getReportAuto(
        @RequestHeader(name = "Authorization") String token
    ) {
        try {
            return ResponseEntity.ok(autoService.getReportAuto(token));
        } catch (TokenIsNotValid e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("imposta/targa")
    public ResponseEntity<ImpostaTargaResponse> impostaTarga(
        @RequestHeader(name = "Authorization") String token,
        @RequestBody ImpostaTargaRequest request
    ) {
        try {
            return ResponseEntity.ok(autoService.impostaTarga(token, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
