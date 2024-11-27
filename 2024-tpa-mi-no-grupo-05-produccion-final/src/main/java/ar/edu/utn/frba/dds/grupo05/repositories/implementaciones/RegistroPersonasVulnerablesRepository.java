package ar.edu.utn.frba.dds.grupo05.repositories.implementaciones;


import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro.RegistroPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.repositories.IRegistroPersonaVulnerableRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistroPersonasVulnerablesRepository implements IRegistroPersonaVulnerableRepository {
    private List<RegistroPersonaVulnerable> registrosPersonasVulnerables;

    public RegistroPersonasVulnerablesRepository(){
      this.registrosPersonasVulnerables = new ArrayList<>();
    }

    @Override
    public List<RegistroPersonaVulnerable> buscarTodos(){
      return this.registrosPersonasVulnerables;
    }
    @Override
    public void guardar(RegistroPersonaVulnerable registro){
              this.registrosPersonasVulnerables.add(registro);
    }
  }
  