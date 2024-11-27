package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras;


import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraLlenaException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Table(name = "heladera")
public class Heladera {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  @Column(name = "nombre_significativo", nullable = false)
  private String nombreSignificativo;

  @ManyToOne
  @JoinColumn(name = "persona_juridica_id", referencedColumnName = "id")
  @Getter
  private PersonaJuridica personaACargo;

  @Column
  @Getter
  @Setter
  private String latitud;

  @Column
  @Getter
  @Setter
  private String longitud;

  @Getter
  @Setter
  @Column(name = "direccion_completa", nullable = false)
  private String direccion;

  @Setter
  @Getter
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "fecha_puesta_en_funcionamiento", columnDefinition = "DATE")
  private LocalDateTime fechaPuestaEnFuncionamiento;

  @Setter
  @Getter
  @Column(name = "capacidad_viandas", nullable = false)
  private Integer capacidadEnViandas;

  @Getter
  @Setter
  @Column(name = "descripcion")
  private String descripcion;

  @OneToMany
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  @Column(name = "inventario_actual_viandas")
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private List<Vianda> inventarioActualViandas;

  @Setter
  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "temperatura_registrada_id", referencedColumnName = "id")
  private RegistroTemperatura ultimaTemperaturaRegistrada;

  @ManyToOne
  @Getter
  @Setter
  @JoinColumn(name = "modelo_heladera_id", referencedColumnName = "id")
  private ModeloHeladera modeloHeladera;

  @Getter
  @Setter
  @Column(name = "modelo_detalle")
  private String modeloDetalle;

  @Setter
  @Getter
  @Enumerated(EnumType.STRING)
  @Column(name = "estado_heladera")
  private EstadoHeladera estadoHeladera;

  @Getter
  @ManyToOne
  @JoinColumn(name = "localidad_id", referencedColumnName = "id")
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private Localidad localidad;

  @Getter
  @Setter
  @Column(name = "horas_para_apertura")
  private Integer horasParaApertura = 3;

  @ManyToMany
  @JoinTable(name = "suscripcion_incidentes_heladera",
          joinColumns = @JoinColumn(name = "heladera_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id"),
          uniqueConstraints = @UniqueConstraint(columnNames = {"heladera_id", "persona_id"})
  )
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private Set<PersonaHumana> suscriptoresDeIncidentes;

  @OneToMany(mappedBy = "heladera")
  private List<FallaTecnica> fallasTecnicas;

  @OneToMany(mappedBy = "heladera")
  private List<MovimientoHeladera> movimientosViandas;

  @Setter
  @OneToOne(mappedBy = "heladera")
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private SensorMovimiento sensorMovimiento;

  @Setter
  @OneToOne(mappedBy = "heladera")
  @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
  private SensorTemperatura sensorTemperatura;


  public Heladera() {
    this.fechaPuestaEnFuncionamiento = LocalDateTime.now();
    this.inventarioActualViandas = new ArrayList<>();
    this.estadoHeladera = EstadoHeladera.ACTIVA;
    this.fallasTecnicas = new ArrayList<>();
    this.movimientosViandas = new ArrayList<>();
    this.suscriptoresDeIncidentes = new HashSet<>();
  }

  public Heladera(ModeloHeladera modelo) {
    this.fechaPuestaEnFuncionamiento = LocalDateTime.now();
    this.inventarioActualViandas = new ArrayList<>();
    this.estadoHeladera = EstadoHeladera.ACTIVA;
    this.modeloHeladera = modelo;
  }

  public Heladera(PersonaJuridica personaJuridica) {
    this.fechaPuestaEnFuncionamiento = LocalDateTime.now();
    this.inventarioActualViandas = new ArrayList<>();
    this.estadoHeladera = EstadoHeladera.ACTIVA;
    this.personaACargo = personaJuridica;
  }

  public void agregarVianda(Vianda vianda) {
    if (!this.puedeRecibirViandas(1)){
      throw new HeladeraLlenaException("La heladera no tiene espacio para recibir mas viandas: " +
              this.getNombreSignificativo());
    }
    this.inventarioActualViandas.add(vianda);
  }

  public void sacarVianda(Vianda vianda) {
    this.inventarioActualViandas.remove(vianda);
  }

  public Integer mesesActiva() {
    return Period.between(this.fechaPuestaEnFuncionamiento.toLocalDate(), LocalDate.now()).getMonths();
  }

  public Boolean estaActiva() {
    return this.estadoHeladera == EstadoHeladera.ACTIVA;
  }

  public void registrarNuevaTemperatura(Double temperatura) {
    RegistroTemperatura registro = new RegistroTemperatura();
    registro.setTemperatura(temperatura);
    registro.setFecha(LocalDateTime.now());

    this.ultimaTemperaturaRegistrada = registro;

    this.compararTemperatura();
  }

  public void setModeloHeladeraDetalles(String detalle){
    this.modeloHeladera = new ModeloHeladera();
    this.modeloHeladera.setDetalle(detalle);
  }
  public void ponerEnAlertaMovimiento() {
    this.estadoHeladera = EstadoHeladera.ALERTA_MOVIMIENTO;

  }

  public void ponerEnInactiva() {
    this.estadoHeladera = EstadoHeladera.INACTIVA;
  }

  private void compararTemperatura() {
    if (!this.modeloHeladera.temperaturaEnRango(this.ultimaTemperaturaRegistrada.getTemperatura())) {
      //si no esta en rango => descompuesta
      this.estadoHeladera = EstadoHeladera.DESCOMPUESTA;
    }
  }

  public List<Heladera> heladerasEnLocalidad() {
    return this.localidad.getHeladeras();
  }

  public Integer espacioDisponible() {
    return this.capacidadEnViandas - this.cantidadViandas();
  }

  public Integer cantidadViandas() {
    return this.inventarioActualViandas.size();
  }

  public Boolean tieneEspacioDisponible(Integer cantidad) {
    return this.espacioDisponible() >= cantidad;
  }

  public Boolean puedeRecibirViandas(Integer cantidad) {
    return this.estaActiva() && this.tieneEspacioDisponible(cantidad);
  }

  public LocalDateTime getFechaUltimaConexion() {
    return this.ultimaTemperaturaRegistrada.getFecha();
  }

  public void ponerEnAlertaConexion() {

    this.estadoHeladera = EstadoHeladera.FALLA_CONEXION;

  }

  public void agregarSuscriptorDeIncidentes(PersonaHumana persona) {
    this.suscriptoresDeIncidentes.add(persona);
  }

  public Boolean yaSuscripto(PersonaHumana persona) {
    return this.suscriptoresDeIncidentes.contains(persona);
  }

  public void notificarSubscriptores(Notificacion notificacion) {
    suscriptoresDeIncidentes.forEach(persona -> persona.notificar(notificacion));
  }

  public void agregarFallaTecnica(FallaTecnica fallaTecnica) {
    this.fallasTecnicas.add(fallaTecnica);
  }

  public int cantidadDeFallasTecnicas() {
    return this.fallasTecnicas.size();
  }

  public String cantidadDeViandasDistribuidas() {
    return String.valueOf(this.movimientosViandas.size());
  }

  public void agregarMovimientoDeVianda(MovimientoHeladera movimiento) {
    this.movimientosViandas.add(movimiento);
  }
}