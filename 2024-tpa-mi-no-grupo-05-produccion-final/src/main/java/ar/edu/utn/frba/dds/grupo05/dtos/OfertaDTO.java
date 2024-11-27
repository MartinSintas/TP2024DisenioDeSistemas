package ar.edu.utn.frba.dds.grupo05.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaDTO {
    public String nombre;
    public String subTitulo;
    public String descripcion;
    public Double puntosNecesarios;
    public String imagen;
    public Long rubroId;
}
