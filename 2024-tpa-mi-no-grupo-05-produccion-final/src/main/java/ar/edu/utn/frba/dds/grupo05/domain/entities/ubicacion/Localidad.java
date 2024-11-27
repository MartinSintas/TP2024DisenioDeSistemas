package ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "localidad")
public class Localidad extends Persistente {

    @Getter
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "localidad")
    @Getter
    private List<Heladera> heladeras = new ArrayList<>();

    @OneToMany(mappedBy = "localidad")
    private List<Tecnico> tecnicos = new ArrayList<>();


    public Localidad(String nombre) {
        this.nombre = nombre;
        this.heladeras = new ArrayList<>();
    }

    public Localidad() {

    }

    private void agregarHeladera(Heladera heladera) {
        heladeras.add(heladera);
    }

    public List<Heladera> heladerasActivas() {
        return this.heladeras.stream().filter(Heladera::estaActiva).toList();
    }

    public List<Heladera> heladerasActivasConEspacio(Integer cantidad) {
        return this.getHeladeras().stream().filter(h -> h.puedeRecibirViandas(cantidad)).toList();
    }

    public String getNombreLocalidad() {
        return nombre;
    }

    public void addTecnico(Tecnico tecnico) {
        tecnicos.add(tecnico);
    }
}
