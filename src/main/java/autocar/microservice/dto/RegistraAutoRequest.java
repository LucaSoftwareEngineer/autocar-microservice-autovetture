package autocar.microservice.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistraAutoRequest {

    private String targa;
    private String marca;
    private String modello;
    private String colore;
    private Integer numeroRuote;
    private Integer cavalli;

}
