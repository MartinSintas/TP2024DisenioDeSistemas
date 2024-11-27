package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TipoSuscripcion extends Persistente {

    public abstract Boolean cumpleCondicion(Heladera heladera);

    void notificar(Heladera heladera, PersonaHumana colaborador) {
        colaborador.notificar(this.notificacionAEnviar(heladera));
    }

    public abstract Notificacion notificacionAEnviar(Heladera heladera);
}
