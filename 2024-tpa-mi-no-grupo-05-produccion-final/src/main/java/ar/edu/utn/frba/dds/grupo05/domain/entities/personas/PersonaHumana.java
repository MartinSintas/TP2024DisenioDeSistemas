package ar.edu.utn.frba.dds.grupo05.domain.entities.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.*;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDinero;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro.RegistroPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DonacionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.RespuestasFormulario;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.TarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona_humana")
public class PersonaHumana implements AcumuladorPuntos {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "owner")
    @Cascade(CascadeType.PERSIST)
    @Setter
    @Getter
    private TarjetaColaborador tarjeta;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @Cascade(CascadeType.PERSIST)
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Getter
    @Setter
    @Column(name = "Apellido", nullable = false)
    private String apellido;

    @Getter
    @Setter
    @Column(name = "Nro_Documento", nullable = false)
    private String nroDocumento;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_Documento",nullable = false)
    private TipoDocumento tipoDocumento;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "persona_humana_id", referencedColumnName = "id")
    @Column(name = "medios_de_contacto")
    @Cascade(CascadeType.PERSIST)
    private List<MedioDeContacto> mediosDeContacto;

    @OneToOne
    @JoinColumn(name = "respuesta_formulario_id", referencedColumnName = "id")
    @Setter
    @Cascade(CascadeType.PERSIST)
    private RespuestasFormulario respuestasFormulario;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "forma_de_colaborar", joinColumns = @JoinColumn(name = "persona_humana_id"))
    @Column(name = "forma_de_colaborar")
    private List<FormaDeColaborar> formaDeColaborar;

    @Getter

    @OneToMany(mappedBy = "donante")
    @Column(name = "donaciones_viandas")
    private List<DonacionVianda> donacionesViandas;

    @OneToMany
    @JoinColumn(name = "persona_humana_id",referencedColumnName = "id")
    @Column(name = "distribuciones_viandas")
    private List<DistribucionVianda> distribucionesVianda;

    @Getter
    @OneToMany
    @JoinColumn(name = "persona_humana_id",referencedColumnName = "id")
    @Column(name = "donaciones_dinero")
    @Cascade(CascadeType.PERSIST)
    private List<DonacionDinero> donacionesDinero;

    @OneToMany
    @JoinColumn(name = "persona_humana_id",referencedColumnName = "id")
    @Column(name = "donaciones_programadas")
    @Cascade(CascadeType.PERSIST)
    private List<DonacionDineroProgramada> donacionesDineroProgramadas;

    @OneToMany
    @JoinColumn(name = "persona_humana_id",referencedColumnName = "id")
    @Column(name = "personas_vulnerables_registradas", columnDefinition = "DOUBLE")
    private List<RegistroPersonaVulnerable> personasVulnerablesRegistradas;

    @ManyToOne
    @JoinColumn(name = "punto_geografico_id",referencedColumnName = "id")
    @Setter
    @Cascade(CascadeType.PERSIST)
    private PuntoGeografico direccion;

    @Setter
    @Getter
    @Column(name = "puntos_acumulados")
    private Double puntosAcumulados;

    @Getter
    @OneToMany
    @JoinColumn(name = "persona_humana_id", referencedColumnName = "id")
    @Column(name = "canjes_realizados")
    private List<Canje> canjesRealizados;


    @Column(name = "fecha_nacimiento")
    @Getter
    @Setter
    private LocalDate fechaNacimiento;

    public PersonaHumana() {
        puntosAcumulados = 0d;
        this.mediosDeContacto = new ArrayList<>();
        this.formaDeColaborar = new ArrayList<>();
        this.donacionesViandas = new ArrayList<>();
        this.distribucionesVianda = new ArrayList<>();
        this.donacionesDinero = new ArrayList<>();
        this.canjesRealizados = new ArrayList<>();
        this.personasVulnerablesRegistradas = new ArrayList<>();
        this.donacionesDineroProgramadas = new ArrayList<>();
    }

    public PersonaHumana(String tipoDoc, String documento, String nombre, String apellido, String mail, String fechaColaboracion, String formaColaboracion, Integer cantidad) {
    }


    public void agregarDonacionDeDinero(DonacionDinero donacion) {

        this.donacionesDinero.add(donacion);
        //completar fomrulario

        /*
            la fecha de la donación
            un monto
            una frecuencia (en caso de que se opte por donar de forma periódica)
        */

        //actualizar en bdd
        return;
    }

    public void agregarDonacionDineroProgramada(DonacionDineroProgramada donacion) {
        this.donacionesDineroProgramadas.add(donacion);
    }

    public void agregarDistribucionVianda(DistribucionVianda distribucion) {
        this.distribucionesVianda.add(distribucion);
        //formulario
        /*
        heladera origen,
        la heladera destino,
        la cantidad de viandas a mover
        el motivo de la distribución
                un desperfecto en la heladera
                falta de viandas en la heladera destino
        la fecha en que se realizó la distribución. LocalDateTime.now();
        */

        //actualizar heladera
        //actualizar bdd
    }

    public void agregarDonacionVianda(DonacionVianda donacionVianda) {
        this.donacionesViandas.add(donacionVianda);
        //crear colaboracion
        //completar formulario
        /*
        formulario:
            que comida es
            fecha caducidad
            fecha donacion
            colaborador
            heladera
            opcionales
                calorias
                peso
        */

        //buscar heladera en la cual fue actualizada

        //actualizar heladera

        //actualizar puntos de usuario
        return;
    }

    public void agregarRegistroPersonaVulnerable(RegistroPersonaVulnerable registro) {
        this.personasVulnerablesRegistradas.add(registro);
    }

    public void agregarMedioDeContacto(MedioDeContacto medioDeContacto) {
        this.mediosDeContacto.add(medioDeContacto);
    }

    public Double montoTotalDonado() {
        return this.donacionesDinero.stream()
                .mapToDouble(DonacionDinero::getMonto)
                .sum();
    }

    public Integer cantViandasDonadas() {
        return this.donacionesViandas.size();
    }

    public Integer cantViandasDistribuidas() {

        return this.distribucionesVianda.stream()
                .mapToInt(DistribucionVianda::cantidadDeViandasAMover)
                .sum();
    }

    public Integer cantTarjetasRepartidas() {
        return this.personasVulnerablesRegistradas.size();
    }

    public Double getPuntosConsumidos() {
        return this.canjesRealizados.stream()
                .mapToDouble(Canje::getPuntosUtilizados)
                .sum();
    }
    
    public void agregarCanje(Canje canje) {
        this.canjesRealizados.add(canje);
    }

    public void descontarPuntos(Double puntos) {
        this.puntosAcumulados -= puntos;
    }

    public void agregarPuntos(Double puntos) {this.puntosAcumulados += puntos;}

    public void notificar(Notificacion notificacion) {
        this.mediosDeContacto.parallelStream().forEach(m -> m.notificar(notificacion));
    }

    public String cantidadViandasDonadas(){
        return String.valueOf(this.donacionesViandas.size());
    }
}
