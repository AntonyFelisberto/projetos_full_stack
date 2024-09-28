package com.projetooficina.sistemaoficinaback.repository;

import com.projetooficina.sistemaoficinaback.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {

    public Optional<Cliente> findByEmailAndNome(String email, String nome);

}
