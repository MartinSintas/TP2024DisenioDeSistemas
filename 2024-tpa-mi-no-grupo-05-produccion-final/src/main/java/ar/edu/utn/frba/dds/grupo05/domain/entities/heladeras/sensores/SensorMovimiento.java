package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sensor_movimiento")
public class SensorMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    public void alertarMovimiento(){
        this.heladera.ponerEnAlertaMovimiento();
    }
}
