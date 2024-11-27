package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoJuridico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class PersonaJuridicaOutputDTO {
    private String razonSocial; // Nombre de la entidad
    private TipoJuridico tipo; // Tipo de entidad
    private Rubro rubro; // Rubro al que pertenece
    private List<MedioDeContacto> mediosDeContacto; // Medio de contacto
    private Long id; // ID de la entidad
  }

