package com.antony.helpdesk.services;

import com.antony.helpdesk.dto.CallDTO;
import com.antony.helpdesk.enums.Priority;
import com.antony.helpdesk.enums.Status;
import com.antony.helpdesk.exceptions.NotFoundException;
import com.antony.helpdesk.model.Call;
import com.antony.helpdesk.model.Client;
import com.antony.helpdesk.model.Technical;
import com.antony.helpdesk.repositories.CallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CallService {


    private CallRepository callRepository;

    private TechnicalServices technicalServices;

    private ClientServices clientServices;

    public Call findById(Integer id) {
        Optional<Call> chamado = callRepository.findById(id);
        return chamado.orElseThrow(() -> new NotFoundException("Chamado não encontrado com id: " + id));
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }

    public Call create(CallDTO callDto) {
        return callRepository.save(createNewCall(callDto));
    }

    public Call update(Integer id, CallDTO callDTO) {
        callDTO.setId(id);
        Call call = findById(id);
        call = createNewCall(callDTO);
        return callRepository.save(call);
    }

    private Call createNewCall(CallDTO callDto) {
        Technical technical = technicalServices.findById(callDto.getTecnico());
        Client client = clientServices.findById(callDto.getCliente());
        Call call = new Call();
        if(callDto.getId() != null){
            call.setId(callDto.getId());
        }
        if(callDto.getStatus().equals(2)){
            call.setDateClosed(LocalDate.now());
        }
        call.setCliente(client);
        call.setTecnico(technical);
        call.setPriority(Priority.toEnum(callDto.getPriority()));
        call.setStatus(Status.toEnum(callDto.getStatus()));
        call.setObservations(callDto.getObservations());
        call.setTitle(callDto.getTitle());
        return call;
    }

}
