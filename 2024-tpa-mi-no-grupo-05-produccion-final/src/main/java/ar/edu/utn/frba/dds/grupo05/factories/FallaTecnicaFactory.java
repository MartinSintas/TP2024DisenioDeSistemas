package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.FallaTecnicaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.FallaTecnicaOutputDTO;

public class FallaTecnicaFactory {

    public static FallaTecnica crearAPartirDe(FallaTecnicaInputDTO dto) {
        FallaTecnica fallaTecnica = new FallaTecnica(dto.getFechaReporteDelIncidente(),
                                                     dto.getDescripcion(),
                                                     dto.getFoto());

        return fallaTecnica;
    }

    public static FallaTecnicaOutputDTO crearAPartirDe(FallaTecnica fallaTecnica) {
        FallaTecnicaOutputDTO dto = FallaTecnicaOutputDTO
                                    .builder()
                                    .id(fallaTecnica.getId())
                                    .idHeladera(fallaTecnica.getHeladera().getId())
                                    .idColaborador(fallaTecnica.getColaborador().getId())
                                    .fechaReporteDelIncidente(fallaTecnica.getFechaReporteDelIncidente())
                                    .descripcion(fallaTecnica.getDescripcion())
                                    .foto(fallaTecnica.getFoto())
                                    .build();
        return dto;
    }
}
