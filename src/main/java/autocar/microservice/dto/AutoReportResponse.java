package autocar.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AutoReportResponse {
    private int autoConTarga = 0;
    private int autoSenzaTarga = 0;
    private int autoComplessive = 0;
}
