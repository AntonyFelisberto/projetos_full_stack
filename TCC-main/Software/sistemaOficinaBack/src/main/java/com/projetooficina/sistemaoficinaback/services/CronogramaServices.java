package com.projetooficina.sistemaoficinaback.services;

import com.projetooficina.sistemaoficinaback.exception.ObjectNotFoundException;
import com.projetooficina.sistemaoficinaback.model.Cronograma;
import com.projetooficina.sistemaoficinaback.model.Usuario;
import com.projetooficina.sistemaoficinaback.repository.CronogramaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CronogramaServices {

    private final CronogramaRepository cronogramaRepository;
    private final UsuarioServices usuarioServices;

    public Cronograma adicionarCronograma(Cronograma cronograma, Long idUsuario){
        Usuario usuario = usuarioServices.encontrarUsuarioPorId(idUsuario);
        cronograma.setIdCronograma(null);
        cronograma.setUsuario(usuario);
        Cronograma cronogramaSalvo = cronogramaRepository.save(cronograma);
        usuario.getCronogramas().add(cronogramaSalvo);
        usuarioServices.atualizarUsuario(usuario);
        return cronogramaSalvo;
    }

    public Cronograma atualizarCronograma(Cronograma cronograma){
        Cronograma novoCronograma = encontrarCronogramaPorId(cronograma.getIdCronograma());
        novoCronograma.setDescricao(cronograma.getDescricao());
        novoCronograma.setDataInicio(cronograma.getDataInicio());
        novoCronograma.setDataFim(cronograma.getDataFim());
        return cronogramaRepository.save(novoCronograma);
    }

    public List<Cronograma> encontrarTodosOsCronograma(){
        return cronogramaRepository.findAll();
    }

    public void deleteCronograma(Long id){
        cronogramaRepository.deleteById(id);
    }

    public Cronograma encontrarCronogramaPorId(Long id){
        return cronogramaRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuario do id "+id+"não foi encontrado"));
    }

    public List<Cronograma> encontrarTodosCronogramaPorIdUsuario(Long id) {
        List<Cronograma> cronogramas = cronogramaRepository.findByIdUser(id);
        return cronogramas;
    }

    public List<Cronograma> encontrarTodosCronogramaPorHora(Date inicio, Date fim) {
        List<Cronograma> cronogramas = cronogramaRepository.findAllWithCreationDateTime(inicio,fim);
        return cronogramas;
    }

}
