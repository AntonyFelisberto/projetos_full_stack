package com.projetooficina.sistemaoficinaback.repository;

import com.projetooficina.sistemaoficinaback.model.Materiais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateriaisRepository extends JpaRepository<Materiais,Long> {

    public Optional<Materiais> findByCodProduto(Integer codProduto);

}
