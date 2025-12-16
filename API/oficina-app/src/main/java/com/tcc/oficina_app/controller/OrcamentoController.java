package com.tcc.oficina_app.controller;


import com.tcc.oficina_app.DTO.ItemServicoDTO;
import com.tcc.oficina_app.DTO.OrcamentoDTO;
import com.tcc.oficina_app.model.Orcamento;
import com.tcc.oficina_app.services.OrcamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orcamento")
@RequiredArgsConstructor
public class OrcamentoController {
    @Autowired
    private final OrcamentoService orcamentoService;

    private OrcamentoDTO toDTO(Orcamento orcamento) {
        OrcamentoDTO dto = new OrcamentoDTO();
        dto.setIdOrcamento(orcamento.getIdOrcamento());
        dto.setSituacao(orcamento.getSituacao().name());
        dto.setValorTotal(orcamento.getValorTotal());
        dto.setIdCliente(orcamento.getCliente().getIdCliente());
        dto.setPlacaVeiculo(orcamento.getVeiculo().getPlaca());

        dto.setItens(orcamento.getItensServico().stream().map(item -> {
            ItemServicoDTO itemdto = new ItemServicoDTO();
            itemdto.setIdItemServico(item.getId());
            itemdto.setIdServico(item.getServico().getId());
            itemdto.setNomeServico(item.getServico().getNome());
            itemdto.setQtdHoras(item.getQtdHoras());
            itemdto.setDesconto(item.getDesconto());
            return itemdto;
        }).collect(Collectors.toList()));
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<OrcamentoDTO>> listarTodos() {
        List<OrcamentoDTO> lista = orcamentoService.listarTodos()
                .stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrcamentoDTO> buscarPorId(@PathVariable Integer id) {
        return orcamentoService.buscarPorId(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrcamentoDTO> criar(@RequestParam Integer idCliente, @RequestParam String placa) {
        try {
            Orcamento orcamento = orcamentoService.criarNovoOrcamento(idCliente, placa);
            return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(orcamento));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<OrcamentoDTO> adicionarItem(@PathVariable Integer id,
                                                      @RequestBody ItemServicoDTO itemDto) {
        try {
            Orcamento orcamento = orcamentoService.adicionarServico(
                    id,
                    itemDto.getIdServico(),
                    itemDto.getQtdHoras(),
                    itemDto.getDesconto()
            );
            return ResponseEntity.ok(toDTO(orcamento));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}/itens/{idItem}")
    public ResponseEntity<OrcamentoDTO> removerItem(@PathVariable Integer id, @PathVariable Long idItem) {
        try {
            Orcamento orcamento = orcamentoService.removerItemServico(id, idItem);
            return ResponseEntity.ok(toDTO(orcamento));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            orcamentoService.deletarOrcamento(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
