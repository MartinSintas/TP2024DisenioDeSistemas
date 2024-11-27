package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FALTAN_VIANDAS")
@NoArgsConstructor
public class FaltanViandas extends TipoSuscripcion {

    @Column(name = "numero_minimo")
    private Integer numeroMinimo;

    public FaltanViandas(Integer numeroCritico) {
        this.numeroMinimo = numeroCritico;
    }

    @Override
    public Boolean cumpleCondicion(Heladera heladera) {
        return heladera.espacioDisponible().equals(numeroMinimo);
    }

    public Notificacion notificacionAEnviar(Heladera heladera) {
        // Se puede delegar el armado de la notificacion...

        String nombreHeladera = heladera.getNombreSignificativo();
        String numero = numeroMinimo.toString();

        String ubicacion =  heladera.getDireccion() +
                            ", " +
                            heladera.getLocalidad().getNombre();

        String mensaje =    "En la heladera '" +
                            nombreHeladera +
                            "' ubicada en " +
                            ubicacion +
                            " faltan " +
                            numero +
                            " viandas para completar el stock.";

        String asunto = "Faltan viandas en una heladera a la que estas suscrito.";


        return new Notificacion(asunto, mensaje);
    }
}
