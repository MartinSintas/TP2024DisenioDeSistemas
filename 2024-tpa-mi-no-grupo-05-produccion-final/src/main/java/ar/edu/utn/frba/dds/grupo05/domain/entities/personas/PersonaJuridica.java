package ar.edu.utn.frba.dds.grupo05.domain.entities.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDinero;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.RespuestasFormulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
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
@Table(name = "persona_juridica")
public class PersonaJuridica implements AcumuladorPuntos{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_juridico",nullable = false)
    private TipoJuridico tipoJuridico;

    @ManyToOne
    @JoinColumn(name = "rubro_id",referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Rubro rubro;

    @OneToOne
    @JoinColumn(name = "respuesta_formulario_id", referencedColumnName = "id")
    private RespuestasFormulario respuestasFormulario;

    @OneToMany
    @JoinColumn(name = "persona_juridica_id", referencedColumnName = "id")
    private List<MedioDeContacto> mediosDeContacto;

    @OneToMany
    @JoinColumn(name = "persona_juridica_id", referencedColumnName = "id")
    private List<Heladera> heladerasACargo;

    @OneToMany
    @JoinColumn(name = "persona_juridica_id", referencedColumnName = "id")
    private List<DonacionDinero> donacionesDinero;

    @OneToMany
    @JoinColumn(name = "persona_juridica_id",referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<DonacionDineroProgramada> donacionesProgramadas;

    @OneToOne
    @JoinColumn(name = "punto_geografico_id",referencedColumnName = "id")
    private PuntoGeografico direccion;

    @Column(name = "puntos_acumulados", nullable = false)
    private Double puntosAcumulados;

    @OneToMany
    @JoinColumn(name = "persona_juridica_id",referencedColumnName = "id")
    private List<Oferta> ofertasPuntos;

    @Getter
    @OneToMany
    @JoinColumn(name = "persona_juridica_id",referencedColumnName = "id")
    private List<Canje> canjesRealizados;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @Setter
    private Usuario usuario;

    public PersonaJuridica() {
        puntosAcumulados = 0d;
        // init empty lists
        this.mediosDeContacto = new ArrayList<>();
        this.heladerasACargo = new ArrayList<>();
        this.donacionesDinero = new ArrayList<>();
        this.donacionesProgramadas = new ArrayList<>();
        this.ofertasPuntos = new ArrayList<>();
        this.canjesRealizados = new ArrayList<>();
    }

    public void agregarNuevoCanjeRealizado(Canje canjeNuevo){ this.canjesRealizados.add(canjeNuevo); }

    public void agregarNuevaHeladeraACargo(Heladera nuevaHeladeraACargo){
        heladerasACargo.add(nuevaHeladeraACargo);
    }

    public void agregarDonacionDinero(DonacionDinero donacion){
        this.donacionesDinero.add(donacion);

        //completar fomrulario

        /*
            la fecha de la donación
            un monto
            una frecuencia (en caso de que se opte por donar de forma periódica)
              -> En este caso crearia tambien una DonacionProgramada
        */

        //actualizar en bdd
    }

    public void sumarPuntos(Double puntos) {
        this.puntosAcumulados += puntos;
    }

    public Integer cantHeladerasACargo() {
        return this.getHeladerasACargo()
                .stream()
                .toList()
                .size();
    }

    public List<MedioDeContacto> getMedioDeContacto() {
        return mediosDeContacto;
    }

    private List<Heladera> getHeladerasActivas() {
        return this.getHeladerasACargo()
                .stream()
                .filter(Heladera::estaActiva)
                .toList();
    }



    public Integer cantHeladerasActivas() {
        return this.getHeladerasActivas()
                .size();
    }

    public Integer mesesTotalesHeladerasActivas() {
        return this.getHeladerasActivas()
                .stream()
                .mapToInt(Heladera::mesesActiva)
                .sum();
    }

    public Double montoTotalDonado() {
        return this.getDonacionesDinero()
                .stream()
                .mapToDouble(DonacionDinero::getMonto)
                .sum();
    }

    public Double getPuntosConsumidos() {
        return this.canjesRealizados.stream()
                .mapToDouble(Canje::getPuntosUtilizados)
                .sum();
    }

    public void agregarDonacionDineroProgramada(DonacionDineroProgramada donacion) {
        this.donacionesProgramadas.add(donacion);
    }

    public void agregarCanje(Canje canje) {
        this.canjesRealizados.add(canje);
    }

    public void agregarOferta(Oferta oferta) {
        this.ofertasPuntos.add(oferta);
    }

    public void descontarPuntos(Double puntos) {
        this.puntosAcumulados -= puntos;
    }
}
