package com.example.projetofuncionarios.repo;

import com.example.projetofuncionarios.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionariosRepo extends JpaRepository<Funcionarios,Long> {
    void deleteById(Long id);

    Optional<Funcionarios> findById(Long id);
}
