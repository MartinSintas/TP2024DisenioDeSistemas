package ar.edu.utn.frba.dds.grupo05.services.heladera;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.factories.ModeloHeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.RepositorioModeloHeladera;
import java.util.List;

public class ModeloHeladeraService implements IModeloHeladeraService {

    RepositorioModeloHeladera repositorioModeloHeladera;

    public <T> ModeloHeladeraService(RepositorioModeloHeladera repositorioModeloHeladeras) {
        this.repositorioModeloHeladera = repositorioModeloHeladeras;
    }

    /*RepositorioFormulario repositorioFormulario;
    UsuarioService usuarioService;
    public <T> ModeloHeladeraService(RepositorioModeloHeladera repositorioModeloHeladerainstance,
                                     RepositorioFormulario repositorioFormularios,
                                     UsuarioService usuarioServices) {
        this.repositorioModeloHeladera = repositorioModeloHeladerainstance;
        this.repositorioFormulario = repositorioFormularios;
        this.usuarioService = usuarioServices;

    }*/

    @Override
    public void create(ModeloHeladeraDTO modeloHeladeraDTO) {
        try {
            ModeloHeladera nuevoModeloHeladera = ModeloHeladeraFactory.fromDTO(modeloHeladeraDTO);
            this.repositorioModeloHeladera.guardarModeloHeladera(nuevoModeloHeladera);
        } catch (Exception e) {
            System.err.println("Error al crear la heladera: " + e.getMessage());
            throw new RuntimeException("Error al crear heladera: " + modeloHeladeraDTO.getDetalle(), e);
        }
    }

    @Override
    public List<Object> obtenerModelos() {
        return this.repositorioModeloHeladera.buscarTodos("ModeloHeladera");
    }
}