package com.projetooficina.sistemaoficinaback.controller;

import com.projetooficina.sistemaoficinaback.model.Cronograma;
import com.projetooficina.sistemaoficinaback.services.CronogramaServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cronograma")
@AllArgsConstructor
public class CronogramaController {

    private final CronogramaServices cronogramaServices;

    @GetMapping("/all")
    public ResponseEntity<List<Cronograma>> getAllCronograma() {
        List<Cronograma> cronograma = cronogramaServices.encontrarTodosOsCronograma();
        return new ResponseEntity<>(cronograma, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Cronograma>> getAllCronogramaById(@PathVariable("id") Long id){
        List<Cronograma> cronogramas = cronogramaServices.encontrarTodosCronogramaPorIdUsuario(id);
        return new ResponseEntity<>(cronogramas, HttpStatus.OK);
    }

    @PostMapping("/add/{idUsuario}")
    public ResponseEntity<Cronograma> addCronograma(@Valid @RequestBody Cronograma cronograma, @PathVariable Long idUsuario){
        Cronograma novoCronograma = cronogramaServices.adicionarCronograma(cronograma,idUsuario);
        return new ResponseEntity<>(novoCronograma, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cronograma> updateCronograma(@Valid @RequestBody Cronograma cronograma){
        Cronograma cronogramas = cronogramaServices.atualizarCronograma(cronograma);
        return new ResponseEntity<>(cronogramas, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCronograma(@PathVariable("id") Long id) {
        cronogramaServices.deleteCronograma(id);
        return ResponseEntity.noContent().build();
    }

}
