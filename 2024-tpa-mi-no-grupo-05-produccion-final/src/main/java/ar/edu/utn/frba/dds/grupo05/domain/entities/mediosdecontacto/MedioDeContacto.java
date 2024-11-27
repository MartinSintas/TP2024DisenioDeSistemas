package ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto;


import ar.edu.utn.frba.dds.grupo05.converters.EnviadorMensajeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "medio_de_contacto")
public class MedioDeContacto extends Persistente {

    @Convert(converter = EnviadorMensajeAttributeConverter.class)
    @Column(name = "enviador_mensaje")
    private EnviadorMensaje enviador;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "detalle")
    private String detalle;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contacto")
    private TipoContacto tipo;

    @OneToMany
    @JoinColumn(name = "medio_de_contacto_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Notificacion> notificacionesEnviadas;

    public MedioDeContacto() {
        this.notificacionesEnviadas = new ArrayList<>();
    }

    public MedioDeContacto(EnviadorMensaje enviador, String contacto, TipoContacto tipo) {
        this.enviador = enviador;
        this.contacto = contacto;
        this.tipo = tipo;
        this.notificacionesEnviadas = new ArrayList<>();
    }

    public void notificar(Notificacion notificacion) {
        this.enviador.enviarNotificacion(detalle, notificacion.getAsunto(), notificacion.getMensaje());
        this.notificacionesEnviadas.add(notificacion);
    }

    public EnviadorMensaje getEnviador() {
        return enviador;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDetalle() {
        return detalle;
    }

    public TipoContacto getTipo() {
        return tipo;
    }
}
