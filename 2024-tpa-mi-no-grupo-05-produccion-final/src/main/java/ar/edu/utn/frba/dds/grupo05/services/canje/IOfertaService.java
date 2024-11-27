package ar.edu.utn.frba.dds.grupo05.services.canje;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;

import java.util.List;

public interface IOfertaService {
    List<Oferta> obtenerOfertas(Usuario usuario);
}
