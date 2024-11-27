
package ar.edu.utn.frba.dds.grupo05.config;

import ar.edu.utn.frba.dds.grupo05.controllers.*;
import ar.edu.utn.frba.dds.grupo05.exceptions.BrokerPropertiesNotFoundException;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;
import ar.edu.utn.frba.dds.grupo05.repositories.IPermisosRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ITecnicoRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.RepositorioModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.RepositorioIncidente;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDinero;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas.RepositorioCanje;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas.RepositorioOferta;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDonacionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.formularios.RepositorioFormulario;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioAlerta;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioSensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioSuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.implementaciones.PermisosRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.IRepositorioPersonasJuridicas;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioAperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioTarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.*;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.*;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioAperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioTarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.repositories.tecnico.RepositorioVisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.tecnico.TecnicosRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.usuario.RepositorioUsuario;
import ar.edu.utn.frba.dds.grupo05.services.DistribucionViandaService;
import ar.edu.utn.frba.dds.grupo05.services.IDonacionViandaService;
import ar.edu.utn.frba.dds.grupo05.services.ISuscripcionService;
import ar.edu.utn.frba.dds.grupo05.services.SuscripcionService;
import ar.edu.utn.frba.dds.grupo05.services.VisitaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.AperturaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.canje.CanjeService;
import ar.edu.utn.frba.dds.grupo05.services.canje.OfertaService;
import ar.edu.utn.frba.dds.grupo05.services.donaciondinero.DonacionDineroService;
import ar.edu.utn.frba.dds.grupo05.services.donaciondinero.IDonacionDineroService;
import ar.edu.utn.frba.dds.grupo05.services.donacionvianda.DonacionViandaService;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IncidenteService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.HeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.ModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.localidadService.imp.LocalidadService;
import ar.edu.utn.frba.dds.grupo05.services.personasJuridicasServices.PersonaJuridicaService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.IPersonaHumanaService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import ar.edu.utn.frba.dds.grupo05.services.personavulnerableservices.PersonaVulnerableService;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.impl.BuscadorTecnico;
import ar.edu.utn.frba.dds.grupo05.services.reportes.IReporteService;
import ar.edu.utn.frba.dds.grupo05.services.reportes.ReporteService;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.impl.TecnicoService;
import ar.edu.utn.frba.dds.grupo05.services.usuario.IUsuarioService;
import ar.edu.utn.frba.dds.grupo05.services.usuario.UsuarioService;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;
import ar.edu.utn.frba.dds.grupo05.services.viandasservice.ViandaService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.Broker;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.validadores.Validador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ServiceLocator {
    private final static Map<String, Object> instances = new HashMap<>();
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceLocator.class);

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        String componentName = clazz.getName();
        if (!instances.containsKey(clazz.getName())) {
            if (componentName.equals(TecnicosController.class.getName())) {
                instances.put(clazz.getName(), new TecnicosController(
                        getInstance(TecnicoService.class),
                        getInstance(LocalidadService.class)));
            } else if (componentName.equals(ModeloHeladeraController.class.getName())) {
                instances.put(clazz.getName(), new ModeloHeladeraController(
                        getInstance(RepositorioModeloHeladera.class),
                        getInstance(RepositorioFormulario.class),
                        getInstance(UsuarioService.class),
                        getInstance(ModeloHeladeraService.class)
                ));
            } else if (componentName.equals(ModeloHeladeraService.class.getName())) {
                instances.put(clazz.getName(), new ModeloHeladeraService(
                        getInstance(RepositorioModeloHeladera.class)
                ));
            } else if (componentName.equals(RepositorioModeloHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioModeloHeladera());
            } else if (componentName.equals(TecnicoService.class.getName())) {
                instances.put(clazz.getName(), new TecnicoService(
                        getInstance(ITecnicoRepository.class),
                        getInstance(VerificadorDePermisos.class),
                        getInstance(TecnicosRepository.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(ITecnicoRepository.class.getName())) {
                instances.put(clazz.getName(), new TecnicosRepository());
            } else if (componentName.equals(VerificadorDePermisos.class.getName())) {
                instances.put(clazz.getName(), new VerificadorDePermisos(
                        getInstance(IPermisosRepository.class))
                );
            } else if (componentName.equals(PersonaJuridicaController.class.getName())) {
                instances.put(clazz.getName(), new PersonaJuridicaController(
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(RepositorioRubro.class),
                        getInstance(IUsuarioService.class)
                ));
            } else if (componentName.equals(RepositorioPersonaJuridica.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaJuridica());
            } else if (componentName.equals(PersonaJuridicaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaJuridicaService(
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(IRepositorioPersonasJuridicas.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaJuridica());
            } else if (componentName.equals(IPermisosRepository.class.getName())) {
                instances.put(clazz.getName(), new PermisosRepository());

            } else if (componentName.equals(PersonasHumanasController.class.getName())) {
                instances.put(clazz.getName(), new PersonasHumanasController(
                        getInstance(PersonaHumanaService.class),
                        getInstance(RepositorioFormulario.class)
                ));
            } else if (componentName.equals(PersonaHumanaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaHumanaService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioFormulario.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(ViandaService.class.getName())) {
                instances.put(clazz.getName(), new ViandaService(
                        getInstance(RepositorioVianda.class),
                        getInstance(VerificadorDePermisos.class)
                ));
            } else if (componentName.equals(RepositorioFormulario.class.getName())) {
                instances.put(clazz.getName(), new RepositorioFormulario());
            } else if (componentName.equals(EnviadorWhatsapp.class.getName())) {
                instances.put(clazz.getName(), new EnviadorWhatsapp());
            } else if (componentName.equals(EnviadorEmail.class.getName())) {
                instances.put(clazz.getName(), new EnviadorEmail());
            } else if (componentName.equals(UsuarioService.class.getName())) {
                instances.put(clazz.getName(), new UsuarioService(
                        getInstance(RepositorioUsuario.class),
                        getInstance(Validador.class)
                ));
            } else if (componentName.equals(EnviadorTelegram.class.getName())) {
                try {
                    instances.put(clazz.getName(), new EnviadorTelegram());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (componentName.equals(RepositorioUsuario.class.getName())) {
                instances.put(clazz.getName(), new RepositorioUsuario());
            } else if (componentName.equals(IUsuarioService.class.getName())) {
                instances.put(clazz.getName(), new UsuarioService(
                        getInstance(RepositorioUsuario.class),
                        getInstance(Validador.class)
                ));
            } else if (componentName.equals(RepositorioPersonaHumana.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaHumana());
            } else if (componentName.equals(Validador.class.getName())) {
                instances.put(clazz.getName(), Validador.nist80063());
            } else if (componentName.equals(SessionController.class.getName())) {
                instances.put(clazz.getName(), new SessionController(
                        ServiceLocator.getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(IndexController.class.getName())) {
                instances.put(clazz.getName(), new IndexController());
            } else if (componentName.equals(PersonaVulnerableController.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableController(
                        getInstance(PersonaVulnerableService.class),
                        getInstance(PersonaHumanaService.class)
                ));
            } else if (componentName.equals(PersonaVulnerableService.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableService(
                        getInstance(RepositorioPersonaVulnerable.class),
                        getInstance(VerificadorDePermisos.class)
                ));
            } else if (componentName.equals(DonacionDineroController.class.getName())) {
                instances.put(clazz.getName(), new DonacionDineroController(
                        getInstance(IDonacionDineroService.class))
                );
            } else if (componentName.equals(RepositorioPersonaVulnerable.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaVulnerable());
            } else if (componentName.equals(IDonacionDineroService.class.getName())) {
                instances.put(clazz.getName(), new DonacionDineroService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(RepositorioDonacionDineroProgramada.class),
                        getInstance(RepositorioDonacionDinero.class)
                ));
            } else if (componentName.equals(RepositorioDonacionDineroProgramada.class.getName())) {
                instances.put(clazz.getName(), new RepositorioDonacionDineroProgramada());
            } else if (componentName.equals(RepositorioDonacionDinero.class.getName())) {
                instances.put(clazz.getName(), new RepositorioDonacionDinero());
            } else if (componentName.equals(HeladeraController.class.getName())) {
                instances.put(clazz.getName(), new HeladeraController(
                        getInstance(IHeladeraService.class),
                        getInstance(ModeloHeladeraService.class),
                        getInstance(IncidenteService.class),
                        getInstance(VisitaHeladeraService.class),
                        getInstance(TecnicoService.class)
                ));
            } else if (componentName.equals(IHeladeraService.class.getName())) {
                instances.put(clazz.getName(), new HeladeraService(
                        getInstance(RepositorioHeladera.class),
                        getInstance(RepositorioPersonaJuridica.class)
                ));
            } else if (componentName.equals(RepositorioHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioHeladera());
            } else if (componentName.equals(SuscripcionController.class.getName())) {
                instances.put(clazz.getName(), new SuscripcionController(
                        getInstance(ISuscripcionService.class)
                ));
            } else if (componentName.equals(ISuscripcionService.class.getName())) {
                instances.put(clazz.getName(), new SuscripcionService(
                        getInstance(RepositorioSuscripcionHeladera.class),
                        getInstance(IPersonaHumanaService.class),
                        getInstance(IHeladeraService.class)
                ));
            } else if (componentName.equals(RepositorioSuscripcionHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioSuscripcionHeladera());
            } else if (componentName.equals(IPersonaHumanaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaHumanaService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioFormulario.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(DonacionViandasController.class.getName())) {
                instances.put(clazz.getName(), new DonacionViandasController(
                        getInstance(IDonacionViandaService.class)
                ));
            } else if (componentName.equals(BrokerProperties.class.getName())) {
                BrokerProperties brokerProperties = new BrokerProperties();
                try (InputStream input = ServiceLocator.class.getClassLoader().getResourceAsStream("config.Broker.properties")) {
                    if (input == null) {
                        throw new BrokerPropertiesNotFoundException();
                    }
                    brokerProperties.load(input);
                } catch (IOException e) {
                    LOGGER.error("Error al cargar el archivo de configuración de broker: " + e.getMessage());
                }
                instances.put(clazz.getName(), brokerProperties);
            } else if (componentName.equals(Broker.class.getName())) {
                instances.put(clazz.getName(), new Broker(
                        getInstance(BrokerProperties.class)
                ));
            } else if (componentName.equals(RepositorioAlerta.class.getName())) {
                instances.put(clazz.getName(), new RepositorioAlerta());
            } else if (componentName.equals(RepositorioSensorTemperatura.class.getName())) {
                instances.put(clazz.getName(), new RepositorioSensorTemperatura());
            } else if (componentName.equals(IncidenteService.class.getName())) {
                instances.put(clazz.getName(), new IncidenteService(
                        getInstance(RepositorioIncidente.class),
                        getInstance(BuscadorTecnico.class)
                ));
            } else if (componentName.equals(BuscadorTecnico.class.getName())) {
                instances.put(clazz.getName(), new BuscadorTecnico());
            } else if (componentName.equals(RepositorioIncidente.class.getName())) {
                instances.put(clazz.getName(), new RepositorioIncidente());
            } else if (componentName.equals(TecnicoService.class.getName())) {
        instances.put(clazz.getName(), new TecnicoService(
                getInstance(ITecnicoRepository.class),
                getInstance(VerificadorDePermisos.class),
                getInstance(TecnicosRepository.class),
                getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(ITecnicoRepository.class.getName())) {
        instances.put(clazz.getName(), new TecnicosRepository());
      } else if (componentName.equals(VerificadorDePermisos.class.getName())) {
        instances.put(clazz.getName(), new VerificadorDePermisos(
                getInstance(IPermisosRepository.class))
        );
      } else if (componentName.equals(IPermisosRepository.class.getName())) {
        instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(IPermisosRepository.class.getName())) {
        instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(PersonaJuridicaController.class.getName())) {
        instances.put(clazz.getName(), new PersonaJuridicaController(
                getInstance(RepositorioPersonaJuridica.class),
                getInstance(RepositorioRubro.class),
                getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(RepositorioPersonaJuridica.class.getName())) {
        instances.put(clazz.getName(), new RepositorioPersonaJuridica());
      } else if (componentName.equals(PersonaJuridicaService.class.getName())) {
        instances.put(clazz.getName(), new PersonaJuridicaService(
            getInstance(RepositorioPersonaJuridica.class),
            getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(IRepositorioPersonasJuridicas.class.getName())) {
        instances.put(clazz.getName(), new RepositorioPersonaJuridica());
      } else if (componentName.equals(IPermisosRepository.class.getName())) {
        instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(PersonasHumanasController.class.getName())) {
        instances.put(clazz.getName(), new PersonasHumanasController(
                getInstance(PersonaHumanaService.class),
                getInstance(RepositorioFormulario.class)
        ));
      } else if (componentName.equals(PersonaHumanaService.class.getName())) {
        instances.put(clazz.getName(), new PersonaHumanaService(
                getInstance(RepositorioPersonaHumana.class),
                getInstance(RepositorioFormulario.class),
                getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(IPermisosRepository.class.getName())) {
        instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(ViandaService.class.getName())) {
        instances.put(clazz.getName(), new ViandaService(
                getInstance(RepositorioVianda.class),
                getInstance(VerificadorDePermisos.class)
        ));
      } else if (componentName.equals(RepositorioFormulario.class.getName())) {
        instances.put(clazz.getName(), new RepositorioFormulario());
      } else if (componentName.equals(EnviadorWhatsapp.class.getName())) {
        instances.put(clazz.getName(), new EnviadorWhatsapp());
      } else if (componentName.equals(EnviadorEmail.class.getName())) {
        instances.put(clazz.getName(), new EnviadorEmail());
      } else if (componentName.equals(EnviadorTelegram.class.getName())) {
        try {
          instances.put(clazz.getName(), new EnviadorTelegram());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      else if (componentName.equals(DistribucionViandasController.class.getName())) {
        instances.put(clazz.getName(), new DistribucionViandasController(
            getInstance(DistribucionViandaService.class),
            getInstance(IHeladeraService.class)));
      } else if (componentName.equals(DistribucionViandaService.class.getName())) {
        instances.put(clazz.getName(), new DistribucionViandaService(
            getInstance(RepositorioDistribucionVianda.class),
            getInstance(IPersonaHumanaService.class)));
      } else if (componentName.equals(DistribucionViandaService.class.getName())) {
        instances.put(clazz.getName(), new DistribucionViandaService(
                getInstance(RepositorioDistribucionVianda.class),
                getInstance(IPersonaHumanaService.class)
        ));
      } else if (componentName.equals(RepositorioDistribucionVianda.class.getName())) {
        instances.put(clazz.getName(), new RepositorioDistribucionVianda());
      }
      else if (componentName.equals(RepositorioUsuario.class.getName())) {
        instances.put(clazz.getName(), new RepositorioUsuario());
      } else if (componentName.equals(UsuarioService.class.getName())) {
        instances.put(clazz.getName(), new UsuarioService(
                getInstance(RepositorioUsuario.class),
                getInstance(Validador.class)
        ));
      } else if (componentName.equals(RepositorioPersonaHumana.class.getName())) {
        instances.put(clazz.getName(), new RepositorioPersonaHumana());
      } else if (componentName.equals(Validador.class.getName())) {
        instances.put(clazz.getName(), Validador.nist80063());
      } else if (componentName.equals(SessionController.class.getName())) {
        instances.put(clazz.getName(), new SessionController(
                ServiceLocator.getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(IndexController.class.getName())) {
        instances.put(clazz.getName(), new IndexController());
      } else if (componentName.equals((IPermisosRepository.class.getName()))){
            instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(PersonaVulnerableController.class.getName())){
            instances.put(clazz.getName(), new PersonaVulnerableController(
                    getInstance(PersonaVulnerableService.class),
                    getInstance(PersonaHumanaService.class)));
      } else if (componentName.equals(PersonaVulnerableService.class.getName())){
            instances.put(clazz.getName(), new PersonaVulnerableService(
                    getInstance(RepositorioPersonaVulnerable.class),
                    getInstance(VerificadorDePermisos.class)
            ));
      } else if (componentName.equals((IPermisosRepository.class.getName()))) {
        instances.put(clazz.getName(), new PermisosRepository());
      } else if (componentName.equals(PersonaVulnerableController.class.getName())) {
        instances.put(clazz.getName(), new PersonaVulnerableController(
                getInstance(PersonaVulnerableService.class),
                getInstance(PersonaHumanaService.class)));
      } else if (componentName.equals(PersonaVulnerableService.class.getName())) {
        instances.put(clazz.getName(), new PersonaVulnerableService(
                getInstance(RepositorioPersonaVulnerable.class),
                getInstance(VerificadorDePermisos.class)
        ));
      } else if (componentName.equals(DonacionDineroController.class.getName())) {
        instances.put(clazz.getName(), new DonacionDineroController(
                getInstance(IDonacionDineroService.class))
        );
      } else if (componentName.equals(RepositorioPersonaVulnerable.class.getName())) {
        instances.put(clazz.getName(), new RepositorioPersonaVulnerable());
      } else if (componentName.equals(IDonacionDineroService.class.getName())) {
        instances.put(clazz.getName(), new DonacionDineroService(
                getInstance(RepositorioPersonaHumana.class),
                getInstance(RepositorioPersonaJuridica.class),
                getInstance(RepositorioDonacionDineroProgramada.class),
                getInstance(RepositorioDonacionDinero.class)
        ));
      } else if (componentName.equals(RepositorioDonacionDineroProgramada.class.getName())) {
        instances.put(clazz.getName(), new RepositorioDonacionDineroProgramada());
      } else if (componentName.equals(RepositorioDonacionDinero.class.getName())) {
        instances.put(clazz.getName(), new RepositorioDonacionDinero());
      } else if (componentName.equals(PersonaVulnerableController.class.getName())) {
        instances.put(clazz.getName(), new PersonaVulnerableController(
                getInstance(PersonaVulnerableService.class),
                getInstance(PersonaHumanaService.class)
        ));
      } else if (componentName.equals(PersonaVulnerableService.class.getName())) {
        instances.put(clazz.getName(), new PersonaVulnerableService(
                getInstance(RepositorioPersonaVulnerable.class),
                getInstance(VerificadorDePermisos.class)
        ));
      } else if (componentName.equals(VisitaHeladeraService.class.getName())) {
                instances.put(clazz.getName(), new VisitaHeladeraService(
                        getInstance(RepositorioVisitaHeladera.class)
                ));
      } else if (componentName.equals(RepositorioVisitaHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioVisitaHeladera());
      } else if (componentName.equals(IHeladeraService.class.getName())) {
        instances.put(clazz.getName(), new HeladeraService(
                getInstance(RepositorioHeladera.class),
                getInstance(RepositorioPersonaJuridica.class)
        ));
      } else if (componentName.equals(RepositorioHeladera.class.getName())) {
        instances.put(clazz.getName(), new RepositorioHeladera());
      } else if (componentName.equals(SuscripcionController.class.getName())) {
        instances.put(clazz.getName(), new SuscripcionController(
                getInstance(ISuscripcionService.class)
        ));
      } else if (componentName.equals(ISuscripcionService.class.getName())) {
        instances.put(clazz.getName(), new SuscripcionService(
                getInstance(RepositorioSuscripcionHeladera.class),
                getInstance(IPersonaHumanaService.class),
                getInstance(IHeladeraService.class)
        ));
      } else if (componentName.equals(RepositorioSuscripcionHeladera.class.getName())) {
        instances.put(clazz.getName(), new RepositorioSuscripcionHeladera());
      } else if (componentName.equals(IPersonaHumanaService.class.getName())) {
        instances.put(clazz.getName(), new PersonaHumanaService(
                getInstance(RepositorioPersonaHumana.class),
                getInstance(RepositorioFormulario.class),
                getInstance(UsuarioService.class)
        ));
      } else if (componentName.equals(DonacionViandasController.class.getName())) {
        instances.put(clazz.getName(), new DonacionViandasController(
                getInstance(IDonacionViandaService.class)
        ));
      } else if (componentName.equals(IDonacionViandaService.class.getName())) {
        instances.put(clazz.getName(), new DonacionViandaService(
                getInstance(RepositorioHeladera.class),
                getInstance(RepositorioDonacionVianda.class),
                getInstance(IPersonaHumanaService.class),
                getInstance(AperturaHeladeraService.class),
                getInstance(RepositorioTarjetaColaborador.class)
        ));
      } else if (componentName.equals(RepositorioDonacionVianda.class.getName())) {
        instances.put(clazz.getName(), new RepositorioDonacionVianda());
      } else if (componentName.equals(BrokerProperties.class.getName())) {
        BrokerProperties brokerProperties = new BrokerProperties();
        try (InputStream input = ServiceLocator.class.getClassLoader().getResourceAsStream("config.Broker.properties")) {
          if (input == null) {
            throw new BrokerPropertiesNotFoundException();
          }
          brokerProperties.load(input);
        } catch (IOException e) {
          LOGGER.error("Error al cargar el archivo de configuración de broker: " + e.getMessage());
        }
        instances.put(clazz.getName(), brokerProperties);
      } else if (componentName.equals(Broker.class.getName())) {
        instances.put(clazz.getName(), new Broker(
                getInstance(BrokerProperties.class)
        ));
      } else if (componentName.equals(AperturaHeladeraService.class.getName())) {
        instances.put(clazz.getName(), new AperturaHeladeraService(
                getInstance(RepositorioAperturaHeladera.class),
                getInstance(RepositorioTarjetaColaborador.class),
                getInstance(Broker.class),
                getInstance(BrokerProperties.class)
        ));
      } else if (componentName.equals(RepositorioTarjetaColaborador.class.getName())) {
        instances.put(clazz.getName(), new RepositorioTarjetaColaborador());
      } else if (componentName.equals(RepositorioAperturaHeladera.class.getName())) {
        instances.put(clazz.getName(), new RepositorioAperturaHeladera());
      } else if (componentName.equals(CanjeController.class.getName())) {
            instances.put(clazz.getName(), new CanjeController(
                    getInstance(CanjeService.class),
                    getInstance(RepositorioPersonaHumana.class),
                    getInstance(OfertaService.class),
                    getInstance(RepositorioPersonaJuridica.class)
            ));
      } else if(componentName.equals(CanjeService.class.getName())) {
        instances.put(clazz.getName(), new CanjeService(
                getInstance(RepositorioPersonaHumana.class),
                getInstance(RepositorioOferta.class),
                getInstance(RepositorioCanje.class),
                getInstance(RepositorioPersonaJuridica.class),
                getInstance(OfertaService.class)
        ));
      } else if(componentName.equals(OfertaService.class.getName())) {
        instances.put(clazz.getName(), new OfertaService(
                getInstance(RepositorioOferta.class),
                getInstance(RepositorioRubro.class),
                getInstance(RepositorioPersonaJuridica.class)
        ));
      } else if (componentName.equals(RepositorioRubro.class.getName())) {
        instances.put(clazz.getName(), new RepositorioRubro());
      } else if(componentName.equals(RepositorioOferta.class.getName())) {
        instances.put(clazz.getName(), new RepositorioOferta());
      } else if(componentName.equals(RepositorioCanje.class.getName())) {
        instances.put(clazz.getName(), new RepositorioCanje());
      } else if(componentName.equals(OfertaController.class.getName())) {
        instances.put(clazz.getName(), new OfertaController(
                getInstance(OfertaService.class)
        ));
      } else if (componentName.equals(ReportesController.class.getName())) {
        instances.put(clazz.getName(), new ReportesController(
                getInstance(IReporteService.class)
        ));
      } else if (componentName.equals(IReporteService.class.getName())) {
        instances.put(clazz.getName(), new ReporteService(
                getInstance(Properties.class)
        ));
      } else if (componentName.equals(Properties.class.getName())) {
        Properties properties = new Properties();
        try (InputStream input = ServiceLocator.class.getClassLoader().getResourceAsStream("app.properties")) {
          if (input == null) {
            throw new RuntimeException(); // Crear excepcion custom
          }
          properties.load(input);
        } catch (IOException e) {
          LOGGER.error("Error al cargar el archivo de properties: " + e.getMessage());
        }
        instances.put(clazz.getName(), properties);
      }
    }
      if (componentName.equals(PersonasHumanasController.class.getName())) {
        instances.put(clazz.getName(), new PersonasHumanasController(
            getInstance(PersonaHumanaService.class),
            getInstance(RepositorioFormulario.class)
        ));
      }
        if (!instances.containsKey(clazz.getName())) {
            if (componentName.equals(TecnicosController.class.getName())) {
                instances.put(clazz.getName(), new TecnicosController(
                        getInstance(TecnicoService.class),
                        getInstance(LocalidadService.class)));
            } else if (componentName.equals(TecnicoService.class.getName())) {
                instances.put(clazz.getName(), new TecnicoService(
                        getInstance(ITecnicoRepository.class),
                        getInstance(VerificadorDePermisos.class),
                        getInstance(TecnicosRepository.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(ITecnicoRepository.class.getName())) {
                instances.put(clazz.getName(), new TecnicosRepository());
            } else if (componentName.equals(VerificadorDePermisos.class.getName())) {
                instances.put(clazz.getName(), new VerificadorDePermisos(
                        getInstance(IPermisosRepository.class))
                );
            } else if (componentName.equals(IPermisosRepository.class.getName())) {
                instances.put(clazz.getName(), new PermisosRepository());
            } else if (componentName.equals(IPermisosRepository.class.getName())) {
                instances.put(clazz.getName(), new PermisosRepository());
            } else if (componentName.equals(PersonaJuridicaController.class.getName())) {
                instances.put(clazz.getName(), new PersonaJuridicaController(
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(RepositorioRubro.class),
                        getInstance(UsuarioService.class)));
            } else if (componentName.equals(PersonaJuridicaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaJuridicaService(
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(IUsuarioService.class)
                ));
            } else if (componentName.equals(IRepositorioPersonasJuridicas.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaJuridica());
            } else if (componentName.equals(IPermisosRepository.class.getName())) {
                instances.put(clazz.getName(), new PermisosRepository());

            } else if (componentName.equals(PersonasHumanasController.class.getName())) {
                instances.put(clazz.getName(), new PersonasHumanasController(
                        getInstance(PersonaHumanaService.class),
                        getInstance(RepositorioFormulario.class)
                ));
            }else if (componentName.equals(CargaMasivaController.class.getName())){
                instances.put(clazz.getName(), new CargaMasivaController(
                   getInstance(PersonaHumanaService.class)
                ));
            } else if (componentName.equals(PersonaHumanaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaHumanaService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioFormulario.class),
                        getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(IPermisosRepository.class.getName())) {
                instances.put(clazz.getName(), new PermisosRepository());
            } else if (componentName.equals(DonacionViandasController.class.getName())) {
                instances.put(clazz.getName(), new DonacionViandasController(
                        getInstance(IDonacionViandaService.class)));
            } else if (componentName.equals(ViandaService.class.getName())) {
                instances.put(clazz.getName(), new ViandaService(
                        getInstance(RepositorioVianda.class),
                        getInstance(VerificadorDePermisos.class)
                ));
            } else if (componentName.equals(RepositorioFormulario.class.getName())) {
                instances.put(clazz.getName(), new RepositorioFormulario());
            } else if (componentName.equals(EnviadorWhatsapp.class.getName())) {
                instances.put(clazz.getName(), new EnviadorWhatsapp());
            } else if (componentName.equals(EnviadorEmail.class.getName())) {
                instances.put(clazz.getName(), new EnviadorEmail());
            } else if (componentName.equals(EnviadorTelegram.class.getName())) {
                try {
                    instances.put(clazz.getName(), new EnviadorTelegram());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (componentName.equals(RepositorioUsuario.class.getName())) {
                instances.put(clazz.getName(), new RepositorioUsuario());
            } else if (componentName.equals(UsuarioService.class.getName())) {
                instances.put(clazz.getName(), new UsuarioService(
                        getInstance(RepositorioUsuario.class),
                        getInstance(Validador.class)
                ));
            } else if (componentName.equals(RepositorioPersonaHumana.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaHumana());
            } else if (componentName.equals(Validador.class.getName())) {
                instances.put(clazz.getName(), Validador.nist80063());
            } else if (componentName.equals(SessionController.class.getName())) {
                instances.put(clazz.getName(), new SessionController(
                        ServiceLocator.getInstance(UsuarioService.class)
                ));
            } else if (componentName.equals(IndexController.class.getName())) {
                instances.put(clazz.getName(), new IndexController());
            } else if (componentName.equals((IPermisosRepository.class.getName()))) {
                instances.put(clazz.getName(), new PermisosRepository());
            } else if (componentName.equals(PersonaVulnerableController.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableController(
                        getInstance(PersonaVulnerableService.class),
                        getInstance(PersonaHumanaService.class)));
            } else if (componentName.equals(PersonaVulnerableService.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableService(
                        getInstance(RepositorioPersonaVulnerable.class),
                        getInstance(VerificadorDePermisos.class)
                ));
            } else if (componentName.equals(DonacionDineroController.class.getName())) {
                instances.put(clazz.getName(), new DonacionDineroController(
                        getInstance(IDonacionDineroService.class))
                );
            } else if (componentName.equals(RepositorioPersonaVulnerable.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaVulnerable());
            } else if (componentName.equals(IDonacionDineroService.class.getName())) {
                instances.put(clazz.getName(), new DonacionDineroService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioPersonaJuridica.class),
                        getInstance(RepositorioDonacionDineroProgramada.class),
                        getInstance(RepositorioDonacionDinero.class)
                ));
            } else if (componentName.equals(RepositorioDonacionDineroProgramada.class.getName())) {
                instances.put(clazz.getName(), new RepositorioDonacionDineroProgramada());
            } else if (componentName.equals(RepositorioDonacionDinero.class.getName())) {
                instances.put(clazz.getName(), new RepositorioDonacionDinero());
            } else if (componentName.equals(PersonaVulnerableController.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableController(
                        getInstance(PersonaVulnerableService.class),
                        getInstance(PersonaHumanaService.class)
                ));
            } else if (componentName.equals(PersonaVulnerableService.class.getName())) {
                instances.put(clazz.getName(), new PersonaVulnerableService(
                        getInstance(RepositorioPersonaVulnerable.class),
                        getInstance(VerificadorDePermisos.class)
                ));
            } else if (componentName.equals(RepositorioPersonaVulnerable.class.getName())) {
                instances.put(clazz.getName(), new RepositorioPersonaVulnerable());
            } else if (componentName.equals(IHeladeraService.class.getName())) {
                instances.put(clazz.getName(), new HeladeraService(
                        getInstance(RepositorioHeladera.class),
                        getInstance(RepositorioPersonaJuridica.class)
                ));
            } else if (componentName.equals(RepositorioHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioHeladera());
            } else if (componentName.equals(SuscripcionController.class.getName())) {
                instances.put(clazz.getName(), new SuscripcionController(
                        getInstance(ISuscripcionService.class)
                ));
            } else if (componentName.equals(ISuscripcionService.class.getName())) {
                instances.put(clazz.getName(), new SuscripcionService(
                        getInstance(RepositorioSuscripcionHeladera.class),
                        getInstance(IPersonaHumanaService.class),
                        getInstance(IHeladeraService.class)
                ));
            } else if (componentName.equals(RepositorioSuscripcionHeladera.class.getName())) {
                instances.put(clazz.getName(), new RepositorioSuscripcionHeladera());
            } else if (componentName.equals(IPersonaHumanaService.class.getName())) {
                instances.put(clazz.getName(), new PersonaHumanaService(
                        getInstance(RepositorioPersonaHumana.class),
                        getInstance(RepositorioFormulario.class),
                        getInstance(UsuarioService.class)
                ));
            }
        }


        return (T) instances.get(clazz.getName());
    }
}
