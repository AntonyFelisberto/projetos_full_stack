package com.antony.helpdesk.controller;

import com.antony.helpdesk.dto.TechnicalDTO;
import com.antony.helpdesk.model.Technical;
import com.antony.helpdesk.services.TechnicalServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technical")
@AllArgsConstructor
public class TechnicalResources {

    private TechnicalServices technicalServices;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id){
        Technical technical = technicalServices.findById(id);
        return ResponseEntity.ok().body(new TechnicalDTO(technical));
    }

    @GetMapping()
    public ResponseEntity<List<TechnicalDTO>> findAll(){
        List<Technical> technicals = technicalServices.findAll();
        List<TechnicalDTO> technical = technicals.stream().map(x -> new TechnicalDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(technical);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TechnicalDTO> create(@Valid @RequestBody TechnicalDTO technicalDto){
        Technical technical = technicalServices.create(technicalDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(technical.getPersonId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicalDTO technicalDTO){
        Technical technical = technicalServices.update(id,technicalDTO);
        return ResponseEntity.ok().body(new TechnicalDTO(technical));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> delete(@PathVariable Integer id){
        technicalServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
