package ar.edu.utn.frba.dds.grupo05.services.canje;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.AccessDeniedException;
import ar.edu.utn.frba.dds.grupo05.exceptions.RubroNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.UsuarioNoVinculadoAPersonaException;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas.RepositorioOferta;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioRubro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OfertaService implements IOfertaService {
    private RepositorioOferta repositorioOferta;
    private RepositorioRubro repositorioRubro;
    private RepositorioPersonaJuridica repositorioPersonaJuridica;

    public OfertaService(RepositorioOferta repositorioOferta, RepositorioRubro repositorioRubro, RepositorioPersonaJuridica repositorioPersonaJuridica) {
        this.repositorioOferta = repositorioOferta;
        this.repositorioRubro = repositorioRubro;
        this.repositorioPersonaJuridica = repositorioPersonaJuridica;
    }

    @Override
    public List<Oferta> obtenerOfertas(Usuario usuario) {
        List<Oferta> ofertas = this.repositorioOferta.buscarOfertas();

        if (usuario.getRol().equals(TipoRol.PERSONA_JURIDICA)) {
            PersonaJuridica personaJuridica = this.repositorioPersonaJuridica.buscarPorUsuario(usuario)
                    .orElseThrow(UsuarioNoVinculadoAPersonaException::new);

            return ofertas.stream()
                    .filter(oferta -> personaJuridica.getOfertasPuntos().stream()
                            .noneMatch(ofertaPunto -> ofertaPunto.getId().equals(oferta.getId())))
                    .toList();
        }

        return ofertas;
    }

    public List<Rubro> obtenerRubros() {
        return this.repositorioRubro.buscarRubros();
    }

    public OfertaOutputDTO createOferta(OfertaDTO ofertaDTO, Usuario usuario) {
        Optional<Rubro> posibleRubro = this.repositorioRubro.buscarPorId(ofertaDTO.rubroId);
        if (posibleRubro.isEmpty()) {
            throw new RubroNotFoundException("Rubro no encontrado");
        }

        if(usuario.getRol().equals(TipoRol.PERSONA_HUMANA)){
            throw new AccessDeniedException();
        }

        PersonaJuridica personaJuridica = this.repositorioPersonaJuridica.buscarPorUsuario(usuario)
                .orElseThrow(UsuarioNoVinculadoAPersonaException::new);

        Oferta oferta = Oferta.builder()
                .nombre(ofertaDTO.nombre)
                .subTitulo(ofertaDTO.subTitulo)
                .descripcion(ofertaDTO.descripcion)
                .puntosNecesarios(ofertaDTO.puntosNecesarios)
                .imagen(ofertaDTO.imagen)
                .rubro(posibleRubro.get())
                .fechaDeCreacion(LocalDateTime.now())
                .build();

        this.repositorioOferta.guardar(oferta);

        personaJuridica.agregarOferta(oferta);
        this.repositorioPersonaJuridica.actualizar(personaJuridica);

        OfertaOutputDTO output = OfertaOutputDTO.builder()
                .nombre(oferta.getNombre())
                .subTitulo(oferta.getSubTitulo())
                .descripcion(oferta.getDescripcion())
                .puntosNecesarios(oferta.getPuntosNecesarios())
                .imagen(oferta.getImagen())
                .rubro(oferta.getRubro())
                .fechaDeCreacion(LocalDateTime.now())
                .build();

        return output;
    }

    public List<OfertaOutputDTO> obtenerOfertasDisponibles(Usuario usuario) {
        if(usuario.getRol().equals(TipoRol.PERSONA_HUMANA)){
            throw new AccessDeniedException();
        }

        PersonaJuridica personaJuridica = this.repositorioPersonaJuridica.buscarPorUsuario(usuario)
                .orElseThrow(UsuarioNoVinculadoAPersonaException::new);

        return personaJuridica.getOfertasPuntos().stream()
                .map(oferta -> OfertaOutputDTO.builder()
                        .nombre(oferta.getNombre())
                        .subTitulo(oferta.getSubTitulo())
                        .descripcion(oferta.getDescripcion())
                        .puntosNecesarios(oferta.getPuntosNecesarios())
                        .imagen(oferta.getImagen())
                        .rubro(oferta.getRubro())
                        .fechaDeCreacion(oferta.getFechaDeCreacion())
                        .canjes(oferta.getCanjes())
                        .build())
                .toList();
    }
}
