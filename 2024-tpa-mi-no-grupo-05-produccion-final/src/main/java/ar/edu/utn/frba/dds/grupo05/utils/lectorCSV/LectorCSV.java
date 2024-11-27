package ar.edu.utn.frba.dds.grupo05.utils.lectorCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    public List<LineaCSV> leerArchivoCSV(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            List<LineaCSV> lineas = new ArrayList<>();
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(";");

                // Parsear la fecha y la cantidad
                String fechaColaboracion = datos[5].trim();
                Integer cantidad = Integer.parseInt(datos[7].trim());

                LineaCSV nuevaLinea = new LineaCSV(
                        datos[0].trim(), // Tipo Documento
                        datos[1].trim(), // Número Documento
                        datos[2].trim(), // Nombre
                        datos[3].trim(), // Apellido
                        datos[4].trim(), // Email
                        fechaColaboracion,
                        datos[6].trim(), // Forma Colaboración
                        cantidad
                );

                lineas.add(nuevaLinea);
            }
            return lineas;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}