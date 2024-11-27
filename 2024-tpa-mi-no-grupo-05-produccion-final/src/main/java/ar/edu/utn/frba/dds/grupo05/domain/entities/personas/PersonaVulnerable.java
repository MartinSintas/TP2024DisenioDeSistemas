package ar.edu.utn.frba.dds.grupo05.domain.entities.personas;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "persona_vulnerable")
public class PersonaVulnerable {
    @Id
    @GeneratedValue
    private Long id;
    //no creo que nos interese guardar un campo fijo en cada uno de las personas vulnerables
    private static Integer edadMayoria = 18;

    @Column(name = "nombre")
    private String nombre;


    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_nacimiento",columnDefinition = "DATE")
    private LocalDateTime fechaNacimiento;

    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "fecha_registro",columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaDeRegistro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "punto_geografico_id",referencedColumnName = "id")
    private PuntoGeografico domicilio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento")
    private String nroDocumento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persona_vulnerable_id", referencedColumnName = "id")
    @Column(name = "menores_a_cargo")
    private List<PersonaVulnerable> menoresACargo;

    //many to one -> ver cantidad de tarjeta
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tarjeta_id",referencedColumnName = "id")
    private TarjetaPersonaVulnerable tarjeta;

    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;

    @Column(name = "retiros_minimos")
    private Integer retirosMinimos;

    @Column(name = "viandas_por_menor")
    private Integer viandasPorMenor;

    public PersonaVulnerable(String nombre, LocalDateTime fechaNacimiento,
                             LocalDateTime fechaDeRegistro, PuntoGeografico domicilio,
                             TipoDocumento tipoDocumento, String nroDocumento, TarjetaPersonaVulnerable tarjeta) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDeRegistro = fechaDeRegistro;
        this.domicilio = domicilio;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.menoresACargo = new ArrayList<>();
        this.tarjeta = tarjeta;
    }
    public PersonaVulnerable(){
        this.menoresACargo = new ArrayList<>();
        this.fechaDeRegistro = LocalDateTime.now();
    }
    public Boolean esMenor(){

        Integer aniosDiferencia = Period
                .between(LocalDateTime.now().toLocalDate(), this.fechaNacimiento.toLocalDate())
                .getYears();

        return aniosDiferencia <= edadMayoria;
    }

    public Integer cantidadMenoresACargo() {
        return this.menoresACargo.size();
    }

    public void agregarMenorACargo(PersonaVulnerable menor) {
        this.menoresACargo.add(menor);
    }
}
