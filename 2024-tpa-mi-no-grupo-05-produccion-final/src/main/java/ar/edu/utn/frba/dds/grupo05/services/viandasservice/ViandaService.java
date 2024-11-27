package ar.edu.utn.frba.dds.grupo05.services.viandasservice;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.ViandaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.ViandaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.factories.ViandaFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioVianda;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;

import java.util.Optional;

public class ViandaService implements IViandaService {

    private RepositorioVianda viandaRepository;
    private VerificadorDePermisos verificadorDePermisos;

    public ViandaService(RepositorioVianda viandaRepository, VerificadorDePermisos verificadorDePermisos) {
        this.viandaRepository = viandaRepository;
        this.verificadorDePermisos = verificadorDePermisos;
    }

    @Override
    public ViandaOutputDTO darDeAlta(ViandaInputDTO viandaInputDTO, Usuario usuario) {
        verificadorDePermisos.verificarSiUsuarioPuede("DAR_DE_ALTA_VIANDA", usuario);
        Vianda nuevaVianda = ViandaFactory.crearViandaDesdeDTO(viandaInputDTO);
        this.viandaRepository.guardar(nuevaVianda);
        return ViandaFactory.convertirViandaEnDTO(nuevaVianda);
    }

    @Override
    public ViandaOutputDTO modificar(Long id, ViandaInputDTO viandaInputDTO, Usuario usuario) {
        verificadorDePermisos.verificarSiUsuarioPuede("MODIFICAR_VIANDA", usuario);
        Optional<Vianda> posibleVianda = this.viandaRepository.buscarPorId(id);
        if (posibleVianda.isEmpty()) {
            throw new RuntimeException("Vianda no encontrada"); // o lanzar una excepción más específica
        }
        Vianda viandaAModificar = posibleVianda.get();

        ViandaFactory.actualizarViandaDesdeDTO(viandaAModificar, viandaInputDTO);

        this.viandaRepository.actualizar(viandaAModificar);
        return ViandaFactory.convertirViandaEnDTO(viandaAModificar);
    }

    @Override
    public void darDeBaja(Long id, Usuario usuario) {
        verificadorDePermisos.verificarSiUsuarioPuede("DAR_DE_BAJA_VIANDA", usuario);
        Optional<Vianda> posibleVianda = this.viandaRepository.buscarPorId(id);
        if (posibleVianda.isEmpty()) {
            throw new RuntimeException("Vianda no encontrada");
        }
        Vianda viandaAEliminar = posibleVianda.get();
        this.viandaRepository.eliminar(viandaAEliminar);
    }
}
