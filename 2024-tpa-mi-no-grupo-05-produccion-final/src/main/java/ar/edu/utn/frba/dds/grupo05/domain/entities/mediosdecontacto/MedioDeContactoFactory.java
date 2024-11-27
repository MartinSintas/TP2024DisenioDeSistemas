package ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto;

import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.MedioDeContactoDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.TipoDeContactoInexistenteException;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;

public class MedioDeContactoFactory {
  public static MedioDeContacto crearMedioDeContactoDesdeDTO(MedioDeContactoDTO medioDeContactoDTO) {
    MedioDeContacto medioDeContacto = new MedioDeContacto();
    if (medioDeContactoDTO.getTipo() == TipoContacto.WHATSAPP) {
      medioDeContacto.setEnviador(ServiceLocator.getInstance(EnviadorWhatsapp.class));
    } else if (medioDeContactoDTO.getTipo() == TipoContacto.TELEGRAM) {
      medioDeContacto.setEnviador(ServiceLocator.getInstance(EnviadorTelegram.class));
    } else if(medioDeContactoDTO.getTipo() == TipoContacto.EMAIL) {
      medioDeContacto.setEnviador(ServiceLocator.getInstance(EnviadorEmail.class));
    } else {
      throw new TipoDeContactoInexistenteException("Tipo de contacto inexistente.");
    }
    medioDeContacto.setDetalle(medioDeContactoDTO.getValor());
    medioDeContacto.setTipo(medioDeContactoDTO.getTipo());
    return medioDeContacto;
  }
}
