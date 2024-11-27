package ar.edu.utn.frba.dds.grupo05.services.personashumanasservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Enunciado;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Formulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.TipoFormulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.TarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.EstadoTarjeta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.UsuarioInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.CargaArchivoCsvInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaHumanaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaHumanaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.PersonaHumanaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.UsuarioNoVinculadoAPersonaException;
import ar.edu.utn.frba.dds.grupo05.factories.PersonaHumanaFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.formularios.RepositorioFormulario;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.services.usuario.IUsuarioService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaHumanaService implements IPersonaHumanaService {

    private final RepositorioPersonaHumana personaHumanaRepository;
    private final RepositorioFormulario formularioRepository;
    private final IUsuarioService usuarioService;

    public PersonaHumanaService(RepositorioPersonaHumana personaHumanaRepository,
                                RepositorioFormulario formularioRepository,
                                IUsuarioService usuarioService) {
        this.personaHumanaRepository = personaHumanaRepository;
        this.formularioRepository = formularioRepository;
        this.usuarioService = usuarioService;
    }



    @Override
    public void darDeAlta(PersonaHumanaInputDTO personaHumanaInputDTO) {

        PersonaHumana nuevaPersonaHumana = PersonaHumanaFactory.crearPersonaDesdeDTO(personaHumanaInputDTO);

        TarjetaColaborador tarjetaColaborador = new TarjetaColaborador();
        // Codigo random de 11 caracteres
        String number = generateRandomNumberString(11);
        tarjetaColaborador.setCodigoTarjeta(number);
        tarjetaColaborador.setEstadoTarjeta(EstadoTarjeta.ACTIVA);
        tarjetaColaborador.setOwner(nuevaPersonaHumana);

        UsuarioInputDTO usuarioDTO = UsuarioInputDTO
                .builder()
                .contrasenia(personaHumanaInputDTO.getContrasenia())
                .nombreDeUsuario(personaHumanaInputDTO.getUsuario())
                .rol(TipoRol.PERSONA_HUMANA)
                .build();

        Usuario usuario = this.usuarioService.darAltaUsuario(usuarioDTO);

        nuevaPersonaHumana.setUsuario(usuario);
        nuevaPersonaHumana.setTarjeta(tarjetaColaborador);
        this.personaHumanaRepository.guardar(nuevaPersonaHumana);
    }

    public void procesarColaboracionesMasivas(List<CargaArchivoCsvInputDTO> registros) {
        // Procesa cada registro del CSV utilizando el factory
        for (CargaArchivoCsvInputDTO registro : registros) {
            try {
                System.out.println("Procesando registro: " + registro);
                this.darDeAlta(registro);
                System.out.println("Registro procesado correctamente: " + registro);
            } catch (Exception e) {
                System.err.println("Error procesando registro: " + registro);
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void darDeAlta(CargaArchivoCsvInputDTO cargaArchivoCsvInputDTO){
        PersonaHumana nuevaPersonaCargaMasiva = PersonaHumanaFactory.crearCargaMasivaDesdeDTO(cargaArchivoCsvInputDTO);
        personaHumanaRepository.guardar(nuevaPersonaCargaMasiva);  // Persistir el nuevo objeto
    }

    @Override
    public PersonaHumana obtenerPorUsuario(Usuario usuario) {
        Optional<PersonaHumana> posiblePersona = this.personaHumanaRepository.buscarPorUsuario(usuario);

        return posiblePersona.orElseThrow(() ->
                new PersonaHumanaNotFoundException(
                        "No se encontró la persona humana con el usuario " +
                                usuario.getNombreDeUsuario()
                )
        );
    }

    public List<PersonaHumana> obtenerTodas() {
        List<PersonaHumana> personas = this.personaHumanaRepository.buscarTodas();

        if (personas.isEmpty()) {
            throw new PersonaHumanaNotFoundException("No se encontraron personas humanas registradas.");
        }

        return personas;
    }

    @Override
    public PersonaHumanaOutputDTO modificar(Long id, PersonaHumanaInputDTO personaHumanaInputDTO, Usuario usuario) {
    //    verificadorDePermisos.verificarSiUsuarioPuede("MODIFICAR_PERSONA_HUMANA", usuario);

    //    Optional<PersonaHumana> posiblePersonaHumana = this.personaHumanaRepository.buscarPorId(id);

    //    PersonaHumana personaHumanaAModificar = posiblePersonaHumana.get();

    //    this.personaHumanaRepository.actualizar(personaHumanaAModificar);

    //    return PersonaHumanaFactory.convertirPersonaEnDTO(personaHumanaAModificar);
        return null;
    }

    @Override
    public void darDeBaja(Long id, Usuario usuario) {
    //    verificadorDePermisos.verificarSiUsuarioPuede("DAR_DE_BAJA_PERSONA_HUMANA", usuario);

    //    Optional<PersonaHumana> posiblePersonaHumana = this.personaHumanaRepository.buscarPorId(id);


    //    PersonaHumana personaHumanaAEliminar = posiblePersonaHumana.get();

    //    this.personaHumanaRepository.eliminar(personaHumanaAEliminar);
    }

    @Override
    public List<Enunciado> obtenerEnunciadosDinamicos() {
        Optional<Formulario> posiblesFormularios = this.formularioRepository.buscarPorTipoFormulario(TipoFormulario.PERSONA_HUMANA);
        if (posiblesFormularios.isEmpty()) {
            return new ArrayList<>();
        }
        return  posiblesFormularios.get().getEnunciados();
    }


    private String generateRandomNumberString(int length) {
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (numbers.length() * Math.random());
            sb.append(numbers.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void aumentarPuntos(Double puntos, Usuario usuario){
        PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
                UsuarioNoVinculadoAPersonaException::new
        );

        Double puntosPersona = personaHumana.getPuntosAcumulados();
        Double puntosFinales = puntosPersona + puntos;
        personaHumana.setPuntosAcumulados(puntosFinales);

        this.personaHumanaRepository.actualizar(personaHumana);
    }
}
