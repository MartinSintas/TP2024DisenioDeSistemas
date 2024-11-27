package ar.edu.utn.frba.dds.grupo05.services.canje;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeOutputDTO;

import java.util.List;

public interface ICanjeService {
    CanjeOutputDTO evaluarCanjeDeOferta(CanjeDTO canjeDTO, Usuario usuario);
    Double obtenerPuntos(Usuario usuario);
    List<Canje> buscarCanjesPorUsuario(Usuario usuario);
    public List<Canje> obtenerCanjesRealizadosSobreOfertasDel(Usuario usuario);
}
