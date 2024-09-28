package com.antony.helpdesk.controller;

import com.antony.helpdesk.dto.CallDTO;
import com.antony.helpdesk.model.Call;
import com.antony.helpdesk.services.CallService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/calls")
@AllArgsConstructor
public class CallController {

    private CallService callService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CallDTO> findById(@PathVariable Integer id){
        Call call = callService.findById(id);
        return ResponseEntity.ok().body(new CallDTO(call));
    }

    @GetMapping()
    public ResponseEntity<List<CallDTO>> findAll(){
        List<Call> call = callService.findAll();
        List<CallDTO> callDTOs = call.stream().map(calls -> new CallDTO(calls)).collect(Collectors.toList());
        return ResponseEntity.ok().body(callDTOs);
    }

    @PostMapping
    public ResponseEntity<CallDTO> create(@Valid @RequestBody CallDTO callDto){
        Call call = callService.create(callDto);
        URI url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(call.getId()).toUri();
        return ResponseEntity.created(url).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CallDTO> update(@PathVariable Integer id,@Valid @RequestBody CallDTO callDTO){
        Call call = callService.update(id,callDTO);
        return ResponseEntity.ok().body(new CallDTO(call));
    }

}
