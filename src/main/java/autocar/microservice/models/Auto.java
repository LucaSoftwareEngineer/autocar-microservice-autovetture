package autocar.microservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private Long id;

    @Column(name = "auto_targa", nullable = true)
    private String targa;

    @Column(name = "auto_marca")
    private String marca;

    @Column(name = "auto_modello")
    private String modello;

    @Column(name = "auto_colore")
    private String colore;

    @Column(name = "auto_numero_ruote")
    private Integer numeroRuote;

    @Column(name = "auto_cavalli")
    private Integer cavalli;

    @Column(name = "auto_venduta", insertable = false)
    private Boolean venduta;

}
