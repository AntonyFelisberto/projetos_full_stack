package com.example.projetofuncionarios.services;

import com.example.projetofuncionarios.exception.UserNotFoundException;
import com.example.projetofuncionarios.model.Funcionarios;
import com.example.projetofuncionarios.repo.FuncionariosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionariosServicos {
    private final FuncionariosRepo funcionariosRepo;

    @Autowired
    public FuncionariosServicos(FuncionariosRepo funcionariosRepo){
        this.funcionariosRepo = funcionariosRepo;
    }

    public Funcionarios adicionarFuncionarios(Funcionarios funcionarios){
        funcionarios.setCodigoEmpregado(UUID.randomUUID().toString());
        return funcionariosRepo.save(funcionarios);
    }

    public List<Funcionarios> encontrarTodosOsFuncionarios() {
        return funcionariosRepo.findAll();
    }

    public Funcionarios atualizarFuncionarios(Funcionarios funcionarios) {
        return funcionariosRepo.save(funcionarios);
    }

    public Funcionarios encontrarTodosFuncionariosPorId(Long id) {
        return funcionariosRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id "+id+" was not found"));
    }

    public void deleteFuncionarios(Long id) {
        funcionariosRepo.deleteById(id);
    }
}
