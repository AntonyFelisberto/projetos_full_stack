package com.projetooficina.sistemaoficinaback.controller;

import com.projetooficina.sistemaoficinaback.dto.CredenciaisDTO;
import com.projetooficina.sistemaoficinaback.model.Usuario;
import com.projetooficina.sistemaoficinaback.services.UsuarioServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> getAllUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioServices.encontrarUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/trazerIdUsuario")
    public ResponseEntity<Long> getUsuarioId(@Valid @RequestBody CredenciaisDTO usuario){
        Long idUsuario = usuarioServices.pegarIdUsuario(usuario, true);
        return new ResponseEntity<>(idUsuario, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioServices.adicionarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario){
        Usuario usuarios = usuarioServices.atualizarUsuario(usuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        usuarioServices.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
