package ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes;

import ar.edu.utn.frba.dds.grupo05.repositories.personas.IPersonaHumanaRepository;

public class ViandasPorColaborador implements Reporte{

    private String titulo = "Viandas por Colaborador";
    private String parrafosGenerados = "";
    private String nombreDeArchivo = "ViandasPorColaborador";
    private IPersonaHumanaRepository personaHumanaRepository;

    public ViandasPorColaborador(IPersonaHumanaRepository personaHumanaRepository) {
        this.personaHumanaRepository = personaHumanaRepository;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getParrafos() {
        this.generarParrafos();
        return this.parrafosGenerados;
    }

    private void generarParrafos() {
        this.parrafosGenerados = "";
        if(personaHumanaRepository.getAll().isEmpty())
            this.parrafosGenerados = "No hay colaboradores registrados";
        else {
            this.personaHumanaRepository.getAll().forEach(personaHumana -> {
                parrafosGenerados += "Documento colaborador: " + personaHumana.getNroDocumento() + " || Cantidad de viandas donadas: " + personaHumana.cantidadViandasDonadas() + "\n";
            });
        }
    }

    @Override
    public String getNombreDeArchivo() {
        return this.nombreDeArchivo;
    }
}