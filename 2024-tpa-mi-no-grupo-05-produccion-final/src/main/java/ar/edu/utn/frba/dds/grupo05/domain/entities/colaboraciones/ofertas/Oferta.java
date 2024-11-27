package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import lombok.*;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "oferta")
public class Oferta extends Persistente {


    @Column(name = "nombre", nullable = false)
    private String nombre;


    @Column(name = "sub_titulo")
    private String subTitulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "puntos_necesarios")
    private Double puntosNecesarios;

    @Column(name = "imagen")//solo guardemos la url de la imagen
    private String imagen; //identificador de la imagen

    @ManyToOne
    @JoinColumn(name = "rubro_id",referencedColumnName = "id")
    private Rubro rubro;

    @Column(name = "fecha_creacion", columnDefinition = "DATETIME")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime fechaDeCreacion;

    @OneToMany(mappedBy = "oferta")
    private List<Canje> canjes = new ArrayList<>();

    public Boolean sePuedeRealizar(Double puntos){
        return puntos >= this.puntosNecesarios;
    }

    public void agregarCanje(Canje canje){
        this.canjes.add(canje);
    }
}
