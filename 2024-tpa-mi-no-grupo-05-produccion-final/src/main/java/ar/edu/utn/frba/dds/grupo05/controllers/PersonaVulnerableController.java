package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaVulnerableInputDTO;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import ar.edu.utn.frba.dds.grupo05.services.personavulnerableservices.IPersonaVulnerableService;
import ar.edu.utn.frba.dds.grupo05.services.personavulnerableservices.PersonaVulnerableService;
import org.jetbrains.annotations.Nullable;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class PersonaVulnerableController  extends RoleBasedController{

    private IPersonaVulnerableService personaVulnerableService;
    private PersonaHumanaService personaHumanaService;
    public PersonaVulnerableController(IPersonaVulnerableService personaVulnerableService,
                                       PersonaHumanaService personaHumanaServices) {
        this.personaVulnerableService = personaVulnerableService;
        this.personaHumanaService = personaHumanaServices;
    }


    public void index(Context context) {

    }


    public void show(Context context) {

    }


    public void formulario(Context context) {
        context.render("views/colaboraciones/formularios/formulario-registro-persona-vulnerable.hbs");
    }

    public void save(Context context) {

        if (!esPersonaHumana(context)) {

            context.status(403);
            context.render("views/estados/error-permiso-denegado.hbs");
            return;
        }



        String nombre = context.formParam("nombre");
        String fechaNacimiento = context.formParam("birthDate");
        String situacionVivienda = context.formParam("housingStatus");
        String direccion = context.formParam("address");
        String latitud = context.formParam("latitude");
        String longitud = context.formParam("longitude");
        String tipoDocumento = context.formParam("documentType");
        String nroDocumento = context.formParam("documentNumber");
        String tieneMenoresACargo = context.formParam("hasMinors");
        String cantidadMenores = context.formParam("minorsCount");
        String numeroTarjeta = context.formParam("cardNumber");

        PersonaVulnerableInputDTO personaVulnerableInputDTO = PersonaVulnerableInputDTO
                .builder()
                .nombre(nombre)
                .fechaNacimiento(fechaNacimiento != null ? LocalDate.parse(fechaNacimiento).atStartOfDay() : null)
                .situacionCalle("homeless".equals(situacionVivienda))
                .domicilio(direccion)
                .latitud(latitud)
                .longitud(longitud)
                .tipoDocumento(tipoDocumento)
                .nroDocumento(nroDocumento)
                .numeroTarjeta(numeroTarjeta)
                .build();

        this.personaVulnerableService.darDeAlta(personaVulnerableInputDTO);

        if ("yes".equals(tieneMenoresACargo) && cantidadMenores != null) {
            int cantidad = Integer.parseInt(cantidadMenores);

            for (int i = 0; i < cantidad; i++) {
                PersonaVulnerableInputDTO menorDTO = PersonaVulnerableInputDTO
                        .builder()
                        .nombre("Menor " + (i + 1))
                        .fechaNacimiento(LocalDate.now().minusYears(10).atStartOfDay())
                        .situacionCalle(true)
                        .domicilio(direccion)
                        .latitud(latitud)
                        .longitud(longitud)
                        .tipoDocumento("none")
                        .nroDocumento("none")
                        .numeroTarjeta(null)
                        .build();

                this.personaVulnerableService.darDeAlta(menorDTO);
            }
        }
        Usuario user = context.sessionAttribute("usuario");
        Double puntos = 250.0;
        this.personaHumanaService.aumentarPuntos(puntos, user);

        Map<String, Object> model = new HashMap<>();
        model.put("nombre", personaVulnerableInputDTO.getNombre());
        model.put("tipoDocumento", personaVulnerableInputDTO.getTipoDocumento());
        model.put("nroDocumento", personaVulnerableInputDTO.getNroDocumento());
        model.put("fechaNacimiento", personaVulnerableInputDTO.getFechaNacimiento());
        model.put("domicilio", personaVulnerableInputDTO.getDomicilio());

        context.render("views/colaboraciones/formularios/estados/registro-persona-vulnerable-exitosa.hbs", model);
    }



    public void registroExito(Context context) {
        String titulo = "Registro exitoso";
        String mensaje = "Registro como persona vulnerable realizado con éxito. ¡Gracias por registrarte!";
        context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
    }


    public void edit(Context context) {

    }


    public void update(Context context) {

    }



    public void delete(Context context) {

    }
}
