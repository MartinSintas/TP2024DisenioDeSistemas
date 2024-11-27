package ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.impl;

import ar.edu.utn.frba.dds.grupo05.converters.TipoContactoConverter;
import ar.edu.utn.frba.dds.grupo05.converters.TipoDocumentoConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.UsuarioInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.TecnicoOutputDTO;
import ar.edu.utn.frba.dds.grupo05.factories.TecnicoFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.ITecnicoRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.tecnico.TecnicosRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ubicacion.RepositorioLocalidad;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.ITecnicoService;
import ar.edu.utn.frba.dds.grupo05.exceptions.TecnicoNoEncontrado;
import ar.edu.utn.frba.dds.grupo05.services.usuario.UsuarioService;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;

import java.util.Optional;

public class TecnicoService implements ITecnicoService {
    public ITecnicoRepository tecnicosRepository;
    private VerificadorDePermisos verificadorDePermisos;
    private TecnicosRepository repository;
    private RepositorioLocalidad repositorioLocalidad = new RepositorioLocalidad();
    private UsuarioService usuarioService;

    public TecnicoService(ITecnicoRepository tecnicosRepository,
                          VerificadorDePermisos verificadorDePermisos,
                          TecnicosRepository repository,
                          UsuarioService usuarioServices){
        this.tecnicosRepository = tecnicosRepository;
        this.verificadorDePermisos = verificadorDePermisos;
        this.repository = repository;
        this.usuarioService = usuarioServices;
    }

    public static void asignarValoresATecnico(TecnicoInputDTO tecnicoInputDTO, Optional<Tecnico> tecnicos){
        Tecnico tecnico = tecnicos.get();

        tecnico.setNombre(tecnicoInputDTO.getNombre());
        tecnico.setApellido(tecnicoInputDTO.getApellido());
        tecnico.setUsuario(tecnicoInputDTO.getUsuario());
        tecnico.setContrasenia(tecnicoInputDTO.getContrasenia());
        tecnico.setTipoDocumento(TipoDocumentoConverter.convertir(tecnicoInputDTO.getTipoDocumento()));
        tecnico.setCuil(tecnicoInputDTO.getCuil());
        tecnico.setearMedioDeContacto(
                TipoContactoConverter.convertir(tecnicoInputDTO.getTipoContacto()),
                tecnicoInputDTO.getValorContacto());
     }

    @Override
    public void darDeAlta(TecnicoInputDTO tecnicoInputDTO) {
        try {
            UsuarioInputDTO usuarioDTO = UsuarioInputDTO
                    .builder()
                    .contrasenia(tecnicoInputDTO.getContrasenia())
                    .nombreDeUsuario(tecnicoInputDTO.getUsuario())
                    .rol(TipoRol.TECNICO)
                    .build();
            Usuario usuario = this.usuarioService.darAltaUsuario(usuarioDTO);
            Localidad localidad = new Localidad();

            try {
                localidad = this.repositorioLocalidad.buscarPorNombre(tecnicoInputDTO.getLocalidad()).get(0);
            } catch (IndexOutOfBoundsException e) {
                localidad.setNombre(tecnicoInputDTO.getLocalidad());
                // Guardar la nueva localidad en el repositorio
                System.out.println("Nueva localidad creada: " + localidad.getNombre());
            } catch (Exception e) {
                System.err.println("Ocurrió un error inesperado: " + e.getMessage());
                e.printStackTrace(); // Opcional, para registrar detalles del error.
            }

            Tecnico nuevoTecnico = TecnicoFactory.asignarValoresATecnico(tecnicoInputDTO);
            nuevoTecnico.setLocalidad(localidad);
            nuevoTecnico.setLatitud(tecnicoInputDTO.getLatitud());
            nuevoTecnico.setLongitud(tecnicoInputDTO.getLongitud());
            localidad.addTecnico(nuevoTecnico);
            nuevoTecnico.setearMedioDeContacto(TipoContactoConverter.convertir(tecnicoInputDTO.getTipoContacto()), tecnicoInputDTO.getValorContacto());
            this.tecnicosRepository.guardarTecnico(nuevoTecnico);
        } catch (Exception e) {
            System.err.println("Error al guardar el técnico: " + e.getMessage());
            throw new RuntimeException("Error al dar de alta el técnico: " + tecnicoInputDTO.getNombre(), e);
        }
    }


    @Override
    public TecnicoOutputDTO darDeAlta(TecnicoInputDTO tecnicoInputDTO, Usuario usuario){

        verificadorDePermisos.verificarSiUsuarioPuede("DAR_DE_ALTA_A_TECNICO", usuario);

        Tecnico nuevoTecnico = TecnicoFactory.asignarValoresATecnico(tecnicoInputDTO);
        this.tecnicosRepository.guardarTecnico(nuevoTecnico);

        return TecnicoFactory.convertirTecnicoEnOutputDTO(nuevoTecnico);
    }

    @Override
    public TecnicoOutputDTO modificar(Long id, TecnicoInputDTO tecnicoInputDTO, Usuario usuario){

        verificadorDePermisos.verificarSiUsuarioPuede("MODIFICAR_TECNICOS", usuario);

        Optional<Tecnico> tecnico = this.tecnicosRepository.buscarPorId(id);

        if(tecnico.isEmpty()){
            throw new TecnicoNoEncontrado("No se encontro el tecnico con id: " + id + " para modificar.");
        }


        asignarValoresATecnico(tecnicoInputDTO, tecnico);
        this.tecnicosRepository.actualizarTecnico(tecnico);

        return TecnicoFactory.convertirTecnicoEnOutputDTO(tecnico.get());
    }
    @Override
    public void darDeBaja(Long id, Usuario usuario){
        verificadorDePermisos.verificarSiUsuarioPuede("DAR_DE_BAJA_A_TECNICO", usuario);

        Optional<Tecnico> posibleTecnico = this.tecnicosRepository.buscarPorId(id);
        if(posibleTecnico.isEmpty()){
            throw new TecnicoNoEncontrado();
        }

        this.tecnicosRepository.eliminarTecnico(posibleTecnico);
    }

    public Optional<Tecnico> buscarPorId(Long id) {
        return this.tecnicosRepository.buscarPorId(id);
    }

}
