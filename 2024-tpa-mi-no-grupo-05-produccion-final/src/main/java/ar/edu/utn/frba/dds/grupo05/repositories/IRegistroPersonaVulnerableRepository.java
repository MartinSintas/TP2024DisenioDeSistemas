package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro.RegistroPersonaVulnerable;

import java.util.List;

  public interface IRegistroPersonaVulnerableRepository {
    List<RegistroPersonaVulnerable> buscarTodos();

    void guardar(RegistroPersonaVulnerable registropersonaVulnerable);



  }

