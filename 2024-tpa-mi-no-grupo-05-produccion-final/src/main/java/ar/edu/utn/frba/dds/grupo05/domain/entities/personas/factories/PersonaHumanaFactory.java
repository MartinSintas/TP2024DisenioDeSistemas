package ar.edu.utn.frba.dds.grupo05.domain.entities.personas.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.utils.lectorCSV.LineaCSV;

public class PersonaHumanaFactory {
    public static PersonaHumana crearAPartirDe(LineaCSV lineaArchivoColaboraciones) {
        TipoDocumento tipoDocumento = TipoDocumento.valueOf(lineaArchivoColaboraciones.getTipoDocumento());
        MedioDeContacto medioDeContacto = new MedioDeContacto(

        );

        medioDeContacto.setEnviador(new EnviadorEmail());
        medioDeContacto.setDetalle(lineaArchivoColaboraciones.getEmail());
        medioDeContacto.setTipo(TipoContacto.EMAIL);

        PersonaHumana personaHumana = new PersonaHumana();
        personaHumana.setNombre(lineaArchivoColaboraciones.getNombre());
        personaHumana.setApellido(lineaArchivoColaboraciones.getApellido());
        personaHumana.setTipoDocumento(tipoDocumento);
        personaHumana.setNroDocumento(lineaArchivoColaboraciones.getNumeroDocumento());
        personaHumana.agregarMedioDeContacto(medioDeContacto);


        return personaHumana;
    }
}
