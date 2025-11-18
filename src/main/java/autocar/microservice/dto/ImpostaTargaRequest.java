package autocar.microservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpostaTargaRequest {

    private Long idAuto;
    private String targa;

}
