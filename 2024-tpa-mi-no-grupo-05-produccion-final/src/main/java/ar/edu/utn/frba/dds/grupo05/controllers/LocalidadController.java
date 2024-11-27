package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.dtos.LocalidadDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.RepositorioModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.ubicacion.RepositorioLocalidad;
import ar.edu.utn.frba.dds.grupo05.services.heladera.ModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.localidadService.imp.LocalidadService;
import ar.edu.utn.frba.dds.grupo05.services.usuario.UsuarioService;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class LocalidadController extends RoleBasedController implements ICrudViewsHandler{

    private LocalidadService localidadService;
    private RepositorioLocalidad repositorioLocalidad;
    private UsuarioService usuarioService;

    public <T> LocalidadController(LocalidadService localidadServices,
                                 RepositorioLocalidad repositorioLocalidades,
                                 UsuarioService usuarioServices){
        this.localidadService = localidadServices;
        this.repositorioLocalidad = repositorioLocalidades;
        this.usuarioService = usuarioServices;
    }
    public void formulario(Context context){
        context.render("views/tecnicos/registrar-localidad.hbs");
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) throws InterruptedException {
        String nombreL = context.formParam("nombreL");
        String provinciaL = context.formParam("provinciaLoc");
        LocalidadDTO localidadDTO = LocalidadDTO
                .builder()
                .nombre(nombreL)
                .provincia(provinciaL)
                .build();
        this.localidadService.darDeAlta(localidadDTO);

        Map<String, Object> model = new HashMap<>();
        model.put("nombre", localidadDTO.getNombre());
        model.put("provincia", localidadDTO.getProvincia());

        this.registroExito(context);

    }

    public void registroExito(Context context) throws InterruptedException {
        String titulo = "Registro exitoso";
        String mensaje = "Registro de localidad realizado con éxito. ¡Gracias por registrarte!";
        context.render("views/estados/modelosuccessLocalidad.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
