package com.projetooficina.sistemaoficinaback.services;

import com.projetooficina.sistemaoficinaback.model.Usuario;
import com.projetooficina.sistemaoficinaback.repository.UsuarioRepository;
import com.projetooficina.sistemaoficinaback.security.UserSpringSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalheUsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByNome(username);

        if(usuario.isPresent()){
            return new UserSpringSecurity(usuario);
        }else {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
    }

}
