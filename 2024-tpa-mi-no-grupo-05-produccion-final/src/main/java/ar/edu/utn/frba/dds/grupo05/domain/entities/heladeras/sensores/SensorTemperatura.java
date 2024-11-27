package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensor_temperatura")
public class SensorTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    public void recibirTemperatura(Double temperatura){
        this.heladera.registrarNuevaTemperatura(temperatura);
    }
    
}
