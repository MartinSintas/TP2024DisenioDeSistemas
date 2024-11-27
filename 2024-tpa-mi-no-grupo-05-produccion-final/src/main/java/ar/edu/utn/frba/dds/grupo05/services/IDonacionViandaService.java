package ar.edu.utn.frba.dds.grupo05.services;

import ar.edu.utn.frba.dds.grupo05.dtos.DonacionViandaDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;

import java.util.List;

public interface IDonacionViandaService {
  List<HeladeraOutputDTO> obtenerPosiblesHeladeras();
  Integer obtenerTiempoParaApertura(Long heladeraId);
  void donarVianda(DonacionViandaDTO donacionViandaDTO);
}
