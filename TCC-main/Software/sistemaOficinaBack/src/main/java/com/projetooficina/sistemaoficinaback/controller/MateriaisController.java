package com.projetooficina.sistemaoficinaback.controller;

import com.projetooficina.sistemaoficinaback.model.Materiais;
import com.projetooficina.sistemaoficinaback.services.MateriaisServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materiais")
@AllArgsConstructor
public class MateriaisController {

    private final MateriaisServices materiaisServices;

    @GetMapping("/all")
    public ResponseEntity<List<Materiais>> getAllMateriais() {
        List<Materiais> materiais = materiaisServices.encontrarTodosOsmateriais();
        return new ResponseEntity<>(materiais, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Materiais> addMateriais(@Valid @RequestBody Materiais materiais){
        Materiais novoMateriais = materiaisServices.adicionarMateriais(materiais);
        return new ResponseEntity<>(novoMateriais, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMateriais(@PathVariable("id") Long id) {
        materiaisServices.deleteMateriais(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Materiais> updateMateriais(@Valid @RequestBody Materiais materiais){
        materiaisServices.atualizarMateriais(materiais);
        return new ResponseEntity<>(materiais,HttpStatus.OK);
    }

}
