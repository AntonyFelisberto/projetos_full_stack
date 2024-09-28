package com.projetooficina.sistemaoficinaback.repository;

import com.projetooficina.sistemaoficinaback.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByNomeAndAtivo(String nome, boolean ativo);

    public Optional<Usuario> findByNome(String nome);

    public Optional<Usuario> findByRg(String rg);

    Optional<Usuario> findByCpf(String cpf);

}
