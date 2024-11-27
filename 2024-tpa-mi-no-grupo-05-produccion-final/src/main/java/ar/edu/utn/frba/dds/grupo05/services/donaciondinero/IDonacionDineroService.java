package ar.edu.utn.frba.dds.grupo05.services.donaciondinero;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionDineroDTO;

public interface IDonacionDineroService {
  DonacionDineroDTO donarDinero(DonacionDineroDTO donacionDineroDTO, Usuario usuario);
}
