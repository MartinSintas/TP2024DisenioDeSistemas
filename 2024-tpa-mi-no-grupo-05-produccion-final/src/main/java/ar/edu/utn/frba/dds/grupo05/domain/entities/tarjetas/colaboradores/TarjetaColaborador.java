package ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.EstadoTarjeta;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjeta_colaborador")
@Setter
public class TarjetaColaborador extends Persistente {

    @Column(name = "codigo_tarjeta", nullable = false)
    @Getter
    private String codigoTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tarjeta", nullable = false)
    private EstadoTarjeta estadoTarjeta;

    @Getter
    @OneToOne
    @JoinColumn(name = "persona_humana_id", nullable = false, referencedColumnName = "id")
    private PersonaHumana owner;

    public TarjetaColaborador(String codigoTarjeta, PersonaHumana owner) {
        this.codigoTarjeta = codigoTarjeta;
        this.owner = owner;
        this.estadoTarjeta = EstadoTarjeta.ACTIVA;
    }
}
