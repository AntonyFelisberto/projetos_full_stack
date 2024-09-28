package com.projetooficina.sistemaoficinaback.security;

import com.projetooficina.sistemaoficinaback.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserSpringSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Optional<Usuario> usuarioModelOptional;

    public UserSpringSecurity(Optional<Usuario> usuarioModelOptional) {
        this.usuarioModelOptional = usuarioModelOptional;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuarioModelOptional.orElse(new Usuario()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuarioModelOptional.orElse(new Usuario()).getNome();
    }

    public Long getId(){
        return usuarioModelOptional.orElse(new Usuario()).getIdUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarioModelOptional.orElse(new Usuario()).isAtivo();
    }

}
