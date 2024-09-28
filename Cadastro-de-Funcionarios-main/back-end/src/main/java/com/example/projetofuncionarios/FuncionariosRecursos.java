package com.example.projetofuncionarios;

import com.example.projetofuncionarios.model.Funcionarios;
import com.example.projetofuncionarios.services.FuncionariosServicos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosRecursos {
    private final FuncionariosServicos funcionariosServicos;

    public FuncionariosRecursos(FuncionariosServicos funcionariosServicos) {
        this.funcionariosServicos = funcionariosServicos;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Funcionarios>> getAllFuncionarios() {
        List<Funcionarios> funcionarios = funcionariosServicos.encontrarTodosOsFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Funcionarios> getAllFuncionarios(@PathVariable("id") Long id){
        Funcionarios funcionarios = funcionariosServicos.encontrarTodosFuncionariosPorId(id);
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Funcionarios> addFuncionarios(@RequestBody Funcionarios funcionarios){
        Funcionarios novoFuncionario = funcionariosServicos.adicionarFuncionarios(funcionarios);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Funcionarios> updateFuncionarios(@RequestBody Funcionarios funcionarios){
        Funcionarios funcionario = funcionariosServicos.atualizarFuncionarios(funcionarios);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFuncionarios(@PathVariable("id") Long id) {
        funcionariosServicos.deleteFuncionarios(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
