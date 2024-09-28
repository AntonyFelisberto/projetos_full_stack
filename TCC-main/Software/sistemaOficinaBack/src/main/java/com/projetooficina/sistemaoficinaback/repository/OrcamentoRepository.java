package com.projetooficina.sistemaoficinaback.repository;

import com.projetooficina.sistemaoficinaback.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrcamentoRepository extends JpaRepository<Orcamento,Long> {

    @Query("SELECT MAX (o.idOrcamento) FROM Orcamento o")
    Long findMaxId();

}
