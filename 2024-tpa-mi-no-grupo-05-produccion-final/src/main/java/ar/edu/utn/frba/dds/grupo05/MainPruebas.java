package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.AperturaHeladeraBrokerOutputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.IAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.AperturaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.IAperturaHeladeraService;

import java.time.LocalDateTime;

public class MainPruebas {
    public static void main(String[] args) {
        IAperturaHeladeraRepository aperturaHeladeraRepository = null;
        IAperturaHeladeraService aperturaHeladeraService = ServiceLocator.getInstance(AperturaHeladeraService.class);
        AperturaHeladeraBrokerOutputDTO out = new AperturaHeladeraBrokerOutputDTO();
        out.setCodigoTarjeta("1234");
        out.setHoraLimite(LocalDateTime.now().plusHours(3));
        out.setIdAperturaHeladera(1L);
        out.setIdHeladera(1L);
        aperturaHeladeraService.enviarAperturaHeladera(out);
    }
}
