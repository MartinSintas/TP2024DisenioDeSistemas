package ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento",nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "cuil",nullable = false)
    private String cuil;

    @OneToOne
    @JoinColumn(name = "medio_de_contacto_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private MedioDeContacto medioDeContacto;

    // Cobertura por localidad
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private PuntoGeografico ubicacion;

    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Localidad localidad;

    @Column(name = "latitud", nullable = false)
    private String latitud;

    @Column(name = "longitud", nullable = false)
    private String longitud;

    public Tecnico(String nombre,
                   String apellido,
                   TipoDocumento tipoDocumento,
                   String cuil,
                   MedioDeContacto medioDeContacto,
                   PuntoGeografico ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.cuil = cuil;
        this.medioDeContacto = medioDeContacto;
        this.ubicacion = ubicacion;
    }

    public Localidad getLocalidad() {
        return ubicacion.getLocalidad();
    }

    public String getNombreLocalidad() {return localidad.getNombre();}

    //public void setLocalidad(String localidad){this.ubicacion.setLocalidad(localidad);}

    public void notificar(Notificacion notificacion) {
        medioDeContacto.notificar(notificacion);
    }

    public void setearMedioDeContacto(TipoContacto tipo, String detalle){
        MedioDeContacto medioDeContacto = new MedioDeContacto();
        medioDeContacto.setTipo(tipo);
        medioDeContacto.setDetalle(detalle);

        if (tipo == TipoContacto.WHATSAPP) {
            medioDeContacto.setEnviador(new EnviadorWhatsapp());
        } else if (tipo == TipoContacto.EMAIL) {
            medioDeContacto.setEnviador(new EnviadorEmail());
        }

        this.medioDeContacto = medioDeContacto;
    }

    public TipoContacto getTipoContacto(){return this.medioDeContacto.getTipo();}

    public String getValorContacto(){return this.medioDeContacto.getDetalle();}

}
