package ar.edu.utn.frba.dds.grupo05.utils.lectorCSV;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.utils.lectorCSV.LineaCSV;


public class ParserCSV {

    public PersonaHumana instanciarPersonaHumana(LineaCSV linea) {
        return new PersonaHumana(
                linea.getTipoDocumento(),
                linea.getNumeroDocumento(),
                linea.getNombre(),
                linea.getApellido(),
                linea.getEmail(),
                linea.getFechaColaboracion(),
                linea.getFormaColaboracion(),
                linea.getCantidad()
        );
    }
}
