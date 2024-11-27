package ar.edu.utn.frba.dds.grupo05.services.aperturaheladera;

import ar.edu.utn.frba.dds.grupo05.dtos.inputs.AperturaHeladeraInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.AperturaHeladeraBrokerOutputDTO;

public interface IAperturaHeladeraService {
    void enviarAperturaHeladera(AperturaHeladeraBrokerOutputDTO out);
    void recibirRespuestaAperturaHeladera(AperturaHeladeraInputDTO in);
}
