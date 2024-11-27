package ar.edu.utn.frba.dds.grupo05.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PrettyProperties {
  private static PrettyProperties instance = null;
  private Properties prop;
  private Logger LOGGER = LoggerFactory.getLogger(PrettyProperties.class);


  public static PrettyProperties getInstance() {
    if(instance == null) {
      instance = new PrettyProperties();
    }
    return instance;
  }

  private PrettyProperties() {
    this.prop = new Properties();
    this.readProperties();
  }

  private void readProperties() {
    try {
      InputStream input = new FileInputStream("config.properties");
      this.prop.load(input);
    }
    catch (IOException ex) {
      LOGGER.error("Error al leer el archivo de propiedades", ex);
    }
  }

  public String propertyFromName(String name) {
    return this.prop.getProperty(name, null);
  }
}