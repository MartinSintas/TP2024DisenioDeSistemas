package ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tarjeta_persona_vulnerable")
public class TarjetaPersonaVulnerable extends Persistente {

    @Column(name = "codigo_tarjeta", nullable = false, unique = true)
    private String codigoTarjeta;

    @ManyToOne
    @JoinColumn(name = "persona_vulnerable_id", nullable = false, referencedColumnName = "id")
    private PersonaVulnerable owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tarjeta",nullable = false)
    private EstadoTarjeta estadoTarjeta;

    // Constructor que acepta todos los campos
    public TarjetaPersonaVulnerable(String codigoTarjeta, PersonaVulnerable owner, EstadoTarjeta estadoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
        this.owner = owner;
        this.estadoTarjeta = estadoTarjeta != null ? estadoTarjeta : EstadoTarjeta.ACTIVA; // Valor por defecto
    }

/*
    public TarjetaPersonaVulnerable(String codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
        this.estadoTarjeta = EstadoTarjeta.ACTIVA;
    }*/
}
