package com.projetooficina.sistemaoficinaback.services;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.projetooficina.sistemaoficinaback.exception.DataIntegrityViolation;
import com.projetooficina.sistemaoficinaback.model.Cliente;
import com.projetooficina.sistemaoficinaback.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteServices {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServices(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente adicionarCliente(Cliente cliente){
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        if(!phoneNumberUtil.isPossibleNumber(cliente.getCelular(), "BR")){
            throw new DataIntegrityViolation("Celular invalido");
        }
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }

    public Cliente encontrarClientePorEmailENome(Cliente clienteFind){
        Optional<Cliente> cliente = clienteRepository.findByEmailAndNome(clienteFind.getEmail() , clienteFind.getNome());
        if(cliente.isPresent()){
            return atualizar(cliente,clienteFind);
        }else{
            return adicionarCliente(clienteFind);
        }
    }

    private Cliente atualizar(Optional<Cliente> cliente,Cliente clienteAtualizado) {

        clienteAtualizado.setIdCliente(cliente.get().getIdCliente());
        clienteAtualizado.setPlaca(cliente.get().getPlaca());
        clienteAtualizado.setNome(cliente.get().getNome());
        clienteAtualizado.setEndereco(cliente.get().getEndereco());
        clienteAtualizado.setCelular(cliente.get().getCelular());
        clienteAtualizado.setVeiculo(cliente.get().getVeiculo());
        clienteAtualizado.setEmail(cliente.get().getEmail());
        clienteAtualizado.setCidade(cliente.get().getCidade());
        clienteAtualizado.setEstado(cliente.get().getEstado());
        clienteAtualizado.setTelefone(cliente.get().getTelefone());

        return clienteRepository.save(clienteAtualizado);

    }

}
