package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
@DiscriminatorValue("HAY_VIANDAS")
public class HayViandas extends TipoSuscripcion {

    @Column(name = "numero_maximo")
    private Integer numeroMaximo;

    public HayViandas(Integer numeroCritico) {
        this.numeroMaximo = numeroCritico;
    }

    @Override
    public Boolean cumpleCondicion(Heladera heladera) {
        return heladera.cantidadViandas().equals(this.numeroMaximo);
    }


    @Override
    public Notificacion notificacionAEnviar(Heladera heladera) {

        String nombreHeladera = heladera.getNombreSignificativo();
        String numero = numeroMaximo.toString();

        String ubicacion =  heladera.getDireccion() +
                            ", " +
                            heladera.getLocalidad().getNombre();

        String mensaje =    "La heladera " +
                            nombreHeladera +
                            " ubicada en " +
                            ubicacion +
                            " tiene " +
                            numero +
                            " viandas disponibles";

        String asunto = "Numero critico de viandas en una heladera a la que estas suscrito.";

        return new Notificacion(asunto, mensaje);
    }

}
