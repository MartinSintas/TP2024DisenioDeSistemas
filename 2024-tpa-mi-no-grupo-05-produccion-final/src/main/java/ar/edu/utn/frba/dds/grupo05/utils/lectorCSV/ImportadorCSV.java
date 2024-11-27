package ar.edu.utn.frba.dds.grupo05.utils.lectorCSV;



import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;

import java.util.ArrayList;
import java.util.List;

public class ImportadorCSV {

    public List<PersonaHumana> importarPersonasHumanas(String archivoCSV) {
        List<LineaCSV> lineas = new LectorCSV().leerArchivoCSV(archivoCSV);
        List<PersonaHumana> personasHumanas = new ArrayList<>();

        for (LineaCSV linea : lineas) {
            // Validar que la forma de colaboración sea una válida
            if (isFormaColaboracionValida(linea.getFormaColaboracion())) {
                PersonaHumana nuevaPersonaHumana = new ParserCSV().instanciarPersonaHumana(linea);
                personasHumanas.add(nuevaPersonaHumana);
            }
        }
        return personasHumanas;
    }

    private boolean isFormaColaboracionValida(String formaColaboracion) {
        return formaColaboracion.equals("DINERO") ||
                formaColaboracion.equals("DONACION_VIANDAS") ||
                formaColaboracion.equals("REDISTRIBUCION_VIANDAS") ||
                formaColaboracion.equals("ENTREGA_TARJETAS");
    }
}
