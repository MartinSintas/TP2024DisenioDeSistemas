package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suscripcion_heladera", uniqueConstraints = {
        @javax.persistence.UniqueConstraint(columnNames = {"persona_humana_id", "heladera_id", "tipo_suscripcion_id"})
})
public class SuscripcionHeladera extends Persistente {

    @ManyToOne
    @JoinColumn(name = "persona_humana_id", referencedColumnName = "id")
    @Setter
    private PersonaHumana personaHumana;

    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    @Setter
    private Heladera heladera;

    @OneToOne
    @JoinColumn(name = "tipo_suscripcion_id", referencedColumnName = "id")
    @Setter
    private TipoSuscripcion tipoSuscripcion;



    public Boolean cumpleCondicion() {
        return tipoSuscripcion.cumpleCondicion(heladera);
    }

    public void notificar() {
        tipoSuscripcion.notificar(heladera, personaHumana);
    }


}
