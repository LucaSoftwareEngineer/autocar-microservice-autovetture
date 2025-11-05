package autocar.microservice.controllers;

import autocar.microservice.dto.RegistraAutoRequest;
import autocar.microservice.models.Auto;
import autocar.microservice.services.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
