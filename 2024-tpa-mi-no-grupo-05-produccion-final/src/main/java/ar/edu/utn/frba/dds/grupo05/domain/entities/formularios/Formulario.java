package ar.edu.utn.frba.dds.grupo05.domain.entities.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "formulario")
public class Formulario extends Persistente {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "formulario_id",referencedColumnName = "id")
    @Column(name = "enunciados")
    private List<Enunciado> enunciados;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_formulario", nullable = false, unique = true)
    private TipoFormulario tipoFormulario;

}
