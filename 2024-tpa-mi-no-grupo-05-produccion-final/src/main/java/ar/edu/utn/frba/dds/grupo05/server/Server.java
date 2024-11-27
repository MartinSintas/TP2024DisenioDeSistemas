package ar.edu.utn.frba.dds.grupo05.server;

import ar.edu.utn.frba.dds.grupo05.server.handlers.AppHandlers;
import ar.edu.utn.frba.dds.grupo05.server.middleware.AuthMiddleware;
import ar.edu.utn.frba.dds.grupo05.utils.Initializer;
import ar.edu.utn.frba.dds.grupo05.utils.JavalinRenderer;
import ar.edu.utn.frba.dds.grupo05.utils.PrettyProperties;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;

public class Server {
  private static Javalin app = null;
  private static Logger LOGGER = LoggerFactory.getLogger(Server.class);

  public static Javalin app() {
    if (app == null)
      throw new RuntimeException("App no inicializada");
    return app;
  }

  public static void init() {
    if (app == null) {
      Integer port = Integer.parseInt(PrettyProperties.getInstance().propertyFromName("server_port"));
      app = Javalin.create(config()).start(port);

      AuthMiddleware.apply(app);
      AppHandlers.applyHandlers(app);
      Router.init(app);

      if (Boolean.parseBoolean(PrettyProperties.getInstance().propertyFromName("dev_mode"))) {
        Initializer.init();
      }
    }
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
      });

      config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
        Handlebars handlebars = new Handlebars();

        handlebars.registerHelper("json", (hbsContext, options) -> {
          if (hbsContext == null) {
            return "null";
          }
          Gson gson = new Gson();
          return gson.toJson(hbsContext);
        });

        Template template = null;
        try {
          template = handlebars.compile(
                  "templates/" + path.replace(".hbs", ""));
          return template.apply(model);
        } catch (IOException e) {
          LOGGER.error("Error al renderizar el archivo", e);
          context.status(HttpStatus.NOT_FOUND);
          return "No se encuentra la página indicada...";
        }
      }));
    };
  }
}
