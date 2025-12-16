package com.tcc.oficina_app.repository;

import com.tcc.oficina_app.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    Orcamento save(Orcamento orcamento);
}
