package ar.edu.utn.frba.dds.grupo05.services.heladera;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;
import java.util.List;

public interface IModeloHeladeraService {
    void create(ModeloHeladeraDTO modeloHeladeraDTO);

    List<Object> obtenerModelos();
}
