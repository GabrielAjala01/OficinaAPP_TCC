package com.tcc.oficina_app.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
public class OrcamentoDTO {
    private Integer idOrcamento;
    private String situacao;
    private BigDecimal valorTotal;
    private Integer idCliente;
    private String placaVeiculo;
    private List<ItemServicoDTO> itens;
}