package ar.edu.utn.frba.dds.grupo05.controllers;


import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.CargaArchivoCsvInputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.AccessDeniedException;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;


import javax.persistence.EntityManager;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaMasivaController  implements ICrudViewsHandler {

    private PersonaHumanaService personaHumanaService;

    public CargaMasivaController(PersonaHumanaService personaHumanaService) {
        this.personaHumanaService = personaHumanaService;
    }

    @Override
    public void index(Context context) {
        /*Long usuarioId = context.sessionAttribute("usuario_id");
        if (usuarioId == null) {
            throw new AccessDeniedException();
        }
        // Mostrar vista para carga masiva
        Map<String, Object> model = new HashMap<>();
        context.render("cargaMasiva.hbs", model);*/
    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("views/colaboraciones/carga-archivo-csv.hbs", model);
    }

    @Override
    public void save(Context context) {
        Map<String, Object> model = new HashMap<>();

        try {
            // Manejamos la subida del archivo
            UploadedFile uploadedFile = context.uploadedFile("csvfile");

            if (uploadedFile != null) {
                String csvContent = new String(uploadedFile.content().readAllBytes(), StandardCharsets.UTF_8);
                String[] lines = csvContent.split("\r?\n");

                // Convertir las líneas CSV en una lista de CargaArchivoCsvInputDTO
                List<CargaArchivoCsvInputDTO> registros = parseCsvToDto(lines);

                // Guardar cada registro uno por uno
                for (CargaArchivoCsvInputDTO registro : registros) {
                    try {
                        // Procesar y guardar cada registro individualmente
                        personaHumanaService.darDeAlta(registro);
                    } catch (Exception e) {
                        // Manejar error por registro en caso de que falle la persistencia
                        System.err.println("Error procesando registro: " + registro);
                        e.printStackTrace();
                        model.put("errorMessage", "Error al procesar algunos registros.");
                        context.render("carga-masiva-error.hbs", model);  // Vista de error si hubo excepción en algún registro
                        return;  // Detenemos el procesamiento si ocurrió un error
                    }
                }

                model.put("happyMessage", "Archivo procesado y datos guardados correctamente.");
                context.render("views/colaboraciones/carga-masiva-exito.hbs", model);  // Vista de éxito

            } else {
                model.put("errorMessage", "No se ha subido ningún archivo.");
                context.render("carga-masiva-error.hbs", model);  // Vista de error si no se sube archivo
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("errorMessage", "Hubo un error al procesar el archivo.");
            context.render("carga-masiva-error.hbs", model);  // Vista de error si hubo excepción en el procesamiento
        }
    }

    private List<CargaArchivoCsvInputDTO> parseCsvToDto(String[] lines) {
        List<CargaArchivoCsvInputDTO> registros = new ArrayList<>();

        for (String line : lines) {
            // Dividimos la línea en campos CSV (asumimos que están separados por comas)
            String[] fields = line.split(";");

            // Crear un DTO basado en los campos (esto depende del formato exacto de tu CSV)
            CargaArchivoCsvInputDTO dto = new CargaArchivoCsvInputDTO();
            dto.setTipoDocumento(TipoDocumento.valueOf(fields[0]));  // Campo 0: Tipo de documento
            dto.setNroDocumento(fields[1]);  // Campo 1: Documento
            dto.setNombre(fields[2]);  // Campo 2: Nombre
            dto.setApellido(fields[3]);  // Campo 3: Apellido
            dto.setMail(fields[4]);  // Campo 4: Mail
            dto.setFechaColaboracion(LocalDate.parse(fields[5], DateTimeFormatter.ofPattern("dd/MM/yyyy")));  // Campo 5: Fecha de colaboración
            dto.setFormaDeColaborar(FormaDeColaborar.valueOf(fields[6]));  // Campo 6: Forma de colaboración
            dto.setCantidad(Integer.valueOf(fields[7]));  // Campo 7: Cantidad


            // Añadir DTO a la lista
            registros.add(dto);
        }

        return registros;
    }
    @Override
    public void show(Context context) {
        // TODO
    }


    @Override
    public void edit(Context context) {
        // TODO
    }

    @Override
    public void update(Context context) {
        // TODO
    }

    @Override
    public void delete(Context context) {
        // TODO
    }
}
