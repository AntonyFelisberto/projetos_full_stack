package com.antony.helpdesk.controller;

import com.antony.helpdesk.dto.ClientDTO;
import com.antony.helpdesk.model.Client;
import com.antony.helpdesk.services.ClientServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientResources {

    private ClientServices clientServices;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        Client client = clientServices.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(client));
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> clients = clientServices.findAll();
        List<ClientDTO> clientsDto = clients.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientsDto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDto){
        Client client = clientServices.create(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getPersonId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO clientDto){
        Client client = clientServices.update(id,clientDto);
        return ResponseEntity.ok().body(new ClientDTO(client));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){
        clientServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
