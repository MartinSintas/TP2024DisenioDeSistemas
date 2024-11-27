package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CanjeOutputDTO {
    private Double puntosUtilizados;
    private Oferta oferta;
    private String fecha;
    private PersonaJuridica personaJuridica;
    private PersonaHumana personaHumana;
}
