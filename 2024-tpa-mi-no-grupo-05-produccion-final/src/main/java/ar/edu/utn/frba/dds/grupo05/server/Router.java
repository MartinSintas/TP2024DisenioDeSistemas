package ar.edu.utn.frba.dds.grupo05.server;

import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.controllers.*;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import io.javalin.Javalin;

public class Router {
  public static void init(Javalin app) {

    app.get("/", ServiceLocator.getInstance(IndexController.class)::index);
    app.get("/registro", ServiceLocator.getInstance(IndexController.class)::register);

    // Redistribuciones
    app.get("/redistribucion-viandas", ServiceLocator.getInstance(DistribucionViandasController.class)::create,
            TipoRol.PERSONA_HUMANA);
    app.post("/redistribucion-viandas", ServiceLocator.getInstance(DistribucionViandasController.class)::save,
            TipoRol.PERSONA_HUMANA);

    // Viandas
    app.get("/donacion-vianda", ServiceLocator.getInstance(DonacionViandasController.class)::create,
            TipoRol.PERSONA_HUMANA);
    app.post("/donacion-vianda", ServiceLocator.getInstance(DonacionViandasController.class)::save,
            TipoRol.PERSONA_HUMANA);

    // Donaciones de dinero
    app.get("/donacion-dinero", ServiceLocator.getInstance(DonacionDineroController.class)::formulario,
            TipoRol.PERSONA_HUMANA,
            TipoRol.PERSONA_JURIDICA
    );
    app.post("/donacion-dinero", ServiceLocator.getInstance(DonacionDineroController.class)::hacerDonacion,
            TipoRol.PERSONA_HUMANA,
            TipoRol.PERSONA_JURIDICA
    );

    // Heladeras

    app.get("/registro/modelo-heladera", ServiceLocator.getInstance(ModeloHeladeraController.class)::formulario);
    app.post("/registro/modelo-heladera", ServiceLocator.getInstance(ModeloHeladeraController.class)::create);
    app.get("/registro/heladera", ServiceLocator.getInstance(HeladeraController.class)::formulario);
    app.post("/registro/heladera", ServiceLocator.getInstance(HeladeraController.class)::create);
    app.get("/registro-exito-heladera", ServiceLocator.getInstance(HeladeraController.class)::registroExito);
    app.get("/mapa-heladeras", ServiceLocator.getInstance(HeladeraController.class)::index,
            TipoRol.PERSONA_JURIDICA, TipoRol.PERSONA_HUMANA, TipoRol.ADMINISTRADOR, TipoRol.TECNICO);
    app.get("/mapa-incidentes", ServiceLocator.getInstance(HeladeraController.class)::indexIncidentes,
            TipoRol.TECNICO, TipoRol.ADMINISTRADOR);
    app.post("/mapa-incidentes", ServiceLocator.getInstance(HeladeraController.class)::createIncidente,
            TipoRol.ADMINISTRADOR, TipoRol.TECNICO);


    // Suscripciones

    app.get("/registro-personas",ServiceLocator.getInstance(PersonasHumanasController.class)::index);
    app.post("/suscribirse-heladera", ServiceLocator.getInstance(SuscripcionController.class)::crear,
            TipoRol.PERSONA_HUMANA);

    // Ofertas
    app.get("/canjear-ofertas", ServiceLocator.getInstance(CanjeController.class)::formulario,
            TipoRol.PERSONA_HUMANA,
            TipoRol.PERSONA_JURIDICA);
    app.post("/canjear-ofertas", ServiceLocator.getInstance(CanjeController.class)::canjearOferta,
            TipoRol.PERSONA_HUMANA,
            TipoRol.PERSONA_JURIDICA);

    app.get("/crear-oferta", ServiceLocator.getInstance(OfertaController.class)::formulario,
            TipoRol.PERSONA_JURIDICA);
    app.post("/crear-oferta", ServiceLocator.getInstance(OfertaController.class)::createOferta,
            TipoRol.PERSONA_JURIDICA);

    app.get("/canjes-realizados", ServiceLocator.getInstance(CanjeController.class)::index,
            TipoRol.PERSONA_HUMANA,
            TipoRol.PERSONA_JURIDICA);

    app.get("/ofertas-disponibles", ServiceLocator.getInstance(OfertaController.class)::ofertasDisponibles,
            TipoRol.PERSONA_JURIDICA);
    app.get("/ofertas-canjeadas", ServiceLocator.getInstance(CanjeController.class)::ofertasCanjeadas,
            TipoRol.PERSONA_JURIDICA);

    //Imagenes
    app.get("/imagenes/{imagen}", ServiceLocator.getInstance(OfertaController.class)::getImagen);

    // Tecnicos
    app.get("/registro/tecnico", ServiceLocator.getInstance(TecnicosController.class)::formulario);
    app.post("/registro/tecnico", ServiceLocator.getInstance(TecnicosController.class)::save);
    app.get("/registro-exito-tecnico", ServiceLocator.getInstance(TecnicosController.class)::registroExito);



    app.get("/carga-archivo-colaboraciones", ServiceLocator.getInstance(CargaMasivaController.class)::create);
    app.post("/carga-archivo-colaboraciones", ServiceLocator.getInstance(CargaMasivaController.class)::save);

    app.get("/registro/persona-humana", ServiceLocator.getInstance(PersonasHumanasController.class)::create);
    app.post("/registro/persona-humana", ServiceLocator.getInstance(PersonasHumanasController.class)::save);
    app.get("/registro-exito", ServiceLocator.getInstance(PersonasHumanasController.class)::registroExito);
    app.get("/iniciar-sesion", ServiceLocator.getInstance(SessionController.class)::iniciarSesionPage);
    app.post("/iniciar-sesion", ServiceLocator.getInstance(SessionController.class)::createSession);
    app.get("/cerrar-sesion", ServiceLocator.getInstance(SessionController.class)::destroySession);

    app.get("/personas-vulnerables/registrar",
            ServiceLocator.getInstance(PersonaVulnerableController.class)::formulario, TipoRol.PERSONA_HUMANA);
    app.post("/personas-vulnerables/registrar",
            ServiceLocator.getInstance(PersonaVulnerableController.class)::save, TipoRol.PERSONA_HUMANA);
    app.get("/registro-exito-persona-vulnerable",
            ServiceLocator.getInstance(PersonaVulnerableController.class)::registroExito);

    //visita heladera
    app.get("/visitas-heladeras", ServiceLocator.getInstance(HeladeraController.class)::indexVisitaHealdera);

    // Personas
    app.get("/registro/persona-juridica", ServiceLocator.getInstance(PersonaJuridicaController.class)::create);
    app.post("/registro/persona-juridica", ServiceLocator.getInstance(PersonaJuridicaController.class)::save);


    app.get("/reportes", ServiceLocator.getInstance(ReportesController.class)::listarReportes);
    app.get("/reportes/descargar/{nombreReporte}",
            ServiceLocator.getInstance(ReportesController.class)::descargarReporte);

  }
}
