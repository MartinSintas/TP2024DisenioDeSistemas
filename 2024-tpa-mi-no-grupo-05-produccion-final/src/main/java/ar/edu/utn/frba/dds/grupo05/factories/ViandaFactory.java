package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.ViandaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.ViandaOutputDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ViandaFactory {

    public static Vianda crearViandaDesdeDTO(ViandaInputDTO inputDTO) {
        Vianda vianda = new Vianda();
        vianda.setComidaDetalle(inputDTO.getComidaDetalle());
        vianda.setFechaCaducidad(inputDTO.getFechaCaducidad());
        vianda.setPesoVianda(inputDTO.getPesoVianda());
        vianda.setCalorias(inputDTO.getCalorias());
        vianda.setFueEntregada(inputDTO.getFueEntregada());
        return vianda;
    }

    public static ViandaOutputDTO convertirViandaEnDTO(Vianda vianda) {
        ViandaOutputDTO outputDTO = new ViandaOutputDTO();
        outputDTO.setId(vianda.getId());
        outputDTO.setComidaDetalle(vianda.getComidaDetalle());
        outputDTO.setFechaCaducidad(vianda.getFechaCaducidad());
        outputDTO.setPesoVianda(vianda.getPesoVianda());
        outputDTO.setCalorias(vianda.getCalorias());
        outputDTO.setFueEntregada(vianda.getFueEntregada());
        return outputDTO;
    }
    public static List<ViandaOutputDTO> convertirViandasEnDTOs(List<Vianda> viandas) {
        return viandas.stream()
            .map(ViandaFactory::convertirViandaEnDTO)
            .collect(Collectors.toList());
    }

    public static void actualizarViandaDesdeDTO(Vianda vianda, ViandaInputDTO inputDTO) {
        vianda.setComidaDetalle(inputDTO.getComidaDetalle());
        vianda.setFechaCaducidad(inputDTO.getFechaCaducidad());
        vianda.setPesoVianda(inputDTO.getPesoVianda());
        vianda.setCalorias(inputDTO.getCalorias());
        vianda.setFueEntregada(inputDTO.getFueEntregada());
    }
}
