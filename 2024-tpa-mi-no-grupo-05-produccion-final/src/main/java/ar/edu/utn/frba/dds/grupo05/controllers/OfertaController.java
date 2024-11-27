package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.services.canje.CanjeService;
import ar.edu.utn.frba.dds.grupo05.services.canje.OfertaService;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OfertaController extends RoleBasedController{
    private OfertaService ofertaService;

    public void formulario(Context context) {
        List<Rubro> rubros = ofertaService.obtenerRubros();

        Map<String, Object> model = this.getModel(context);
        model.put("rubros", rubros);

        context.render("views/colaboraciones/formularios/formulario-oferta-canjeable.hbs", model);
    }

    public void createOferta(Context context) throws IOException {
        String nombreOferta = context.formParam("nombreOferta");
        String subTitulo = context.formParam("subTitulo");
        String descripcion = context.formParam("descripcion");
        String rubroIdStr = context.formParam("rubro");
        String puntosNecesariosStr = context.formParam("puntosNecesarios");
        UploadedFile imagen = context.uploadedFile("imagen");

        String fileName = null;

        if(imagen != null) {
            fileName = imagen.filename();
            InputStream content = imagen.content();
            String urlImagen = "imagenes/" + fileName;
            Files.copy(content, Paths.get(urlImagen), StandardCopyOption.REPLACE_EXISTING);
        }

        Long rubroId = rubroIdStr != null ? Long.parseLong(rubroIdStr) : null;
        Double puntosNecesarios = puntosNecesariosStr != null ? Double.parseDouble(puntosNecesariosStr) : null;

        Usuario usuario = context.sessionAttribute("usuario");

        OfertaDTO ofertaDTO = OfertaDTO.builder()
                .nombre(nombreOferta)
                .subTitulo(subTitulo)
                .descripcion(descripcion)
                .rubroId(rubroId)
                .puntosNecesarios(puntosNecesarios)
                .imagen(fileName)
                .build();

        OfertaOutputDTO output = ofertaService.createOferta(ofertaDTO, usuario);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Map<String, Object> model = this.getModel(context);
        model.put("oferta", output);
        model.put("fecha", output.getFechaDeCreacion().format(formatter));

        context.render("views/colaboraciones/formularios/estados/creacion-oferta-exitosa.hbs", model);
    }

    public void ofertasDisponibles(Context context) {
        Usuario usuario = context.sessionAttribute("usuario");

        List<OfertaOutputDTO> ofertas = ofertaService.obtenerOfertasDisponibles(usuario);
        //ofertas.sort(Comparator.comparing(OfertaOutputDTO::getFechaDeCreacion).reversed());

        Map<String, Object> model = this.getModel(context);
        model.put("ofertas", ofertas);

        context.render("views/canjes/ofertas-disponibles.hbs", model);
    }

    public void getImagen(Context context) {
        String imagen = context.pathParam("imagen");
        Path path = Paths.get("imagenes/" + imagen);
        try {
            context.result(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
