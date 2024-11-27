package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OfertaOutputDTO {
    public String nombre;
    public String subTitulo;
    public String descripcion;
    public Double puntosNecesarios;
    public String imagen;
    public Rubro rubro;
    public LocalDateTime fechaDeCreacion;
    @Getter
    public List<Canje> canjes;
}
