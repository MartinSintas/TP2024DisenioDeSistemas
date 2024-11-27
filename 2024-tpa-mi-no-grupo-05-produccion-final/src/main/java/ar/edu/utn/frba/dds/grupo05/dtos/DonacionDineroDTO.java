package ar.edu.utn.frba.dds.grupo05.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DonacionDineroDTO {
  public Double monto;
  public Boolean recurrente;
  public Integer diasParaRepetir;
}
