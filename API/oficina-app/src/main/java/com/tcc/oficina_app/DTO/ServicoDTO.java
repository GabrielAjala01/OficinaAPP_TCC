package com.tcc.oficina_app.DTO;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ServicoDTO {
    private Long idServico;
    private String nome;
    private String descricao;
    private BigDecimal valor;


    private List<Long> idsSubServicos;
}
