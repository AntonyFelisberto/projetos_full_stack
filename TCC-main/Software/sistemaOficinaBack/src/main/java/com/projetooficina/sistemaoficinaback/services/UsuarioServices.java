package com.projetooficina.sistemaoficinaback.services;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.projetooficina.sistemaoficinaback.dto.CredenciaisDTO;
import com.projetooficina.sistemaoficinaback.exception.DataIntegrityViolation;
import com.projetooficina.sistemaoficinaback.exception.ObjectNotFoundException;
import com.projetooficina.sistemaoficinaback.model.Usuario;
import com.projetooficina.sistemaoficinaback.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder encoder;

    private final MessageService messageService;

    public Usuario adicionarUsuario(Usuario usuario){
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        boolean validarSeUsuarioNaoExiste = encontrarClientePorCpfERg(usuario);

        if(validarSeUsuarioNaoExiste && phoneNumberUtil.isPossibleNumber(usuario.getTelefone(), "BR")) {
            throw new DataIntegrityViolation("Telefone incorreto ou usuario ja cadastrado no sistema");
        }

        messageService.cadastrarNumeroAws(usuario.getTelefone());
        usuario.setIdUsuario(null);
        usuario.setAtivo(true);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setCodigoEmpregado(UUID.randomUUID().toString());
        return usuarioRepository.save(usuario);

    }

    public boolean encontrarClientePorCpfERg(Usuario usuarioFind){

        Optional<Usuario> usuarioRg = usuarioRepository.findByRg(usuarioFind.getRg());
        Optional<Usuario> usuarioCpf = usuarioRepository.findByCpf(usuarioFind.getCpf());

        if(usuarioRg.isPresent() || usuarioCpf.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public Usuario atualizarUsuario(Usuario usuario){
        Usuario usuarioAtualizado = encontrarUsuarioPorId(usuario.getIdUsuario());
        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioAtualizado.setRg(usuario.getRg());
        usuarioAtualizado.setCpf(usuario.getCpf());
        usuarioAtualizado.setEmail(usuario.getEmail());
        usuarioAtualizado.setTelefone(usuario.getTelefone());
        usuarioAtualizado.setEndereco(usuario.getEndereco());
        usuarioAtualizado.setCargo(usuario.getCargo());
        usuarioAtualizado.setAtivo(usuario.isAtivo());
        usuarioAtualizado.setCargaHoraria(usuario.getCargaHoraria());
        usuarioAtualizado.setCronogramas(usuario.getCronogramas());

        return usuarioRepository.save(usuarioAtualizado);
    }

    public Usuario encontrarUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuario do id "+id+"não foi encontrado"));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Long pegarIdUsuario(CredenciaisDTO usuario, boolean ativo){
        Usuario validacao = usuarioRepository.findByNomeAndAtivo(usuario.getNome(),ativo);
        if(validarUsuario(usuario,validacao)){
            return validacao.getIdUsuario();
        }else {
            return null;
        }
    }

    private boolean validarUsuario(CredenciaisDTO atual, Usuario validar){
        boolean valid;
        if (validar == null){
            valid = false;
        }else{
            valid = encoder.matches(atual.getSenha(), validar.getSenha());
        }
        return valid;
    }

}
