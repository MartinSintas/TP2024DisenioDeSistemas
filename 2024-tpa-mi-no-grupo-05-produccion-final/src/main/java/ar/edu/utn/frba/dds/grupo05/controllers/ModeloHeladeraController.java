package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.RepositorioModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.formularios.RepositorioFormulario;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.ModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.usuario.UsuarioService;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloHeladeraController extends RoleBasedController implements ICrudViewsHandler {

    ModeloHeladeraService modeloService;
    RepositorioModeloHeladera repositorioModeloHeladera;
    //RepositorioFormulario repositorioFormulario;
    UsuarioService usuarioService;

    public <T> ModeloHeladeraController(
            RepositorioModeloHeladera repositorioModeloHeladerainstance,
            RepositorioFormulario repositorioFormularios,
            UsuarioService usuarioServices,
            ModeloHeladeraService modeloServices) {
        this.repositorioModeloHeladera = repositorioModeloHeladerainstance;
        //this.repositorioFormulario = repositorioFormularios;
        this.usuarioService = usuarioServices;
        this.modeloService = modeloServices;

    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) throws InterruptedException {

    /*if (!esPersonaJuridica(context)) {

      context.status(403);
      context.render("views/estados/error-permiso-denegado.hbs");
      return;
    }*/

        String detalle = context.formParam("detalle");
        String temperaturaMaxima = context.formParam("temperaturaMaxima");
        String temperaturaMinima = context.formParam("temperaturaMinima");
        assert temperaturaMaxima != null;
        assert temperaturaMinima != null;
        Double tempMax = Double.parseDouble(temperaturaMaxima);
        Double tempMin = Double.parseDouble(temperaturaMinima);

        ModeloHeladeraDTO modeloHeladeraDTO = ModeloHeladeraDTO
                .builder()
                .detalle(detalle)
                .temperaturaMaxima(tempMax)
                .temperaturaMinima(tempMin)
                .build();

        //Usuario usuario = context.sessionAttribute("usuario");
        this.modeloService.create(modeloHeladeraDTO);

        Map<String, Object> model = new HashMap<>();
        model.put("detalle", modeloHeladeraDTO.getDetalle());
        model.put("temperaturaMaxima", modeloHeladeraDTO.getTemperaturaMaxima());
        model.put("temperaturaMinima", modeloHeladeraDTO.getTemperaturaMinima());

        this.registroExito(context);
    }

    public void formulario(Context context){
        context.render("views/heladeras/registro-modelo-heladeras.hbs");
    }

    public void registroExito(Context context) throws InterruptedException {
        String titulo = "Registro exitoso";
        String mensaje = "Registro de modelo de heladera realizado con éxito. ¡Gracias por registrarte!";
        context.render("views/estados/modelosuccess.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
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
