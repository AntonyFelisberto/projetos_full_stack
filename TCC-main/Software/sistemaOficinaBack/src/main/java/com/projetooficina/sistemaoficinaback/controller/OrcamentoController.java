package com.projetooficina.sistemaoficinaback.controller;

import com.projetooficina.sistemaoficinaback.dto.DadosOrcamentoDTO;
import com.projetooficina.sistemaoficinaback.dto.OrcamentoDTO;
import com.projetooficina.sistemaoficinaback.model.Materiais;
import com.projetooficina.sistemaoficinaback.model.Orcamento;
import com.projetooficina.sistemaoficinaback.services.ClienteServices;
import com.projetooficina.sistemaoficinaback.services.MateriaisServices;
import com.projetooficina.sistemaoficinaback.services.OrcamentoServices;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orcamento")
@AllArgsConstructor
public class OrcamentoController {

    private final OrcamentoServices orcamentoServices;
    private final MateriaisServices materiaisServices;
    private final ClienteServices clienteServices;

    @GetMapping("/all")
    public ResponseEntity<List<OrcamentoDTO>> getAllOrcamento() {
        List<Orcamento> orcamentos = orcamentoServices.encontrarTodosOsOrcamento();
        List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream().map(x -> new OrcamentoDTO(x)).toList();
        return new ResponseEntity<>(orcamentoDTOS, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Long> pegarIdMaxDoBanco(){
        return ResponseEntity.ok().body(orcamentoServices.pegarIdMaximo());
    }

    @GetMapping("/find/{idOrcamento}")
    public HttpEntity<byte[]> retornarPdfParaBaixar(@PathVariable("idOrcamento") Long idOrcamento){
        Orcamento orcamento = orcamentoServices.encontrarOrcamentoPorId(idOrcamento);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Orçamento");
        header.setContentLength(orcamento.getPdf().length);
        return new HttpEntity<byte[]>(orcamento.getPdf(), header);
    }

    @PostMapping("/reenviar/{idOrcamento}")
    public ResponseEntity<String> reenviarOrcamento(@PathVariable Long idOrcamento){
        orcamentoServices.enviarPorId(idOrcamento);
        return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote("Envio feito com Sucesso"));
    }

    @PostMapping("/add/{idUsuario}")
    @ResponseBody
    public ResponseEntity<String> addOrcamento(@PathVariable Long idUsuario,@Valid @RequestBody DadosOrcamentoDTO orcamentoDTO){

        Set<Long> idMateriais = orcamentoDTO.getMateriaisUsuario().stream().sorted(Comparator.comparing(Materiais::getIdMaterial)).map(Materiais::getIdMaterial).collect(Collectors.toSet());

        List<Materiais> materiaisCliente = new ArrayList<>();

        for(Long id:idMateriais){
            List<Materiais> filtrarMateriaisRepetidos = orcamentoDTO.getMateriaisUsuario().stream().filter(teste -> teste.getIdMaterial().equals(id)).toList();
            orcamentoDTO.getMateriaisUsuario().removeAll(filtrarMateriaisRepetidos);
            filtrarMateriaisRepetidos.get(0).setQuantidade(filtrarMateriaisRepetidos.size());
            orcamentoDTO.getMateriaisUsuario().add(filtrarMateriaisRepetidos.get(0));
        }

        orcamentoDTO.setCliente(clienteServices.encontrarClientePorEmailENome(orcamentoDTO.getCliente()));
        if(orcamentoDTO.getMateriaisUsuario().size()>0){
            materiaisServices.atualizarMateriaisOrcamento(orcamentoDTO.getMateriaisUsuario());
        }

        for(int materialCliente=0; materialCliente<orcamentoDTO.getMateriaisCliente().size(); materialCliente++){

            if(orcamentoDTO.getMateriaisCliente().get(materialCliente).getPrecoUnidade() != null &&
                    orcamentoDTO.getMateriaisCliente().get(materialCliente).getUnidade() != null &&
                    orcamentoDTO.getMateriaisCliente().get(materialCliente).getQuantidade() != null &&
                    orcamentoDTO.getMateriaisCliente().get(materialCliente).getCodProduto() != null &&
                    (!orcamentoDTO.getMateriaisCliente().get(materialCliente).getDescriminizacao().isBlank() &&
                            !orcamentoDTO.getMateriaisCliente().get(materialCliente).getDescriminizacao().isEmpty() &&
                            !orcamentoDTO.getMateriaisCliente().get(materialCliente).getDescriminizacao().equals(""))){
                materiaisCliente.add(orcamentoDTO.getMateriaisCliente().get(materialCliente));
            }
        }

        if(materiaisCliente.size()>0){
            orcamentoDTO.getMateriaisCliente().removeAll(orcamentoDTO.getMateriaisCliente());

            for(int materialCliente=0; materialCliente<materiaisCliente.size(); materialCliente++){
                orcamentoDTO.getMateriaisCliente().add(materiaisCliente.get(materialCliente));
            }
        }else{
            orcamentoDTO.getMateriaisCliente().removeAll(orcamentoDTO.getMateriaisCliente());
        }

        orcamentoServices.adicionarOrcamento(idUsuario,orcamentoDTO);

        return ResponseEntity.status(HttpStatus.OK).body(JSONObject.quote("Envio feito com Sucesso,material atualizado e cliente cadastrado"));
    }

    @DeleteMapping("/delete/{identity}")
    public ResponseEntity<?> deleteOrcamento(@PathVariable("identity") Long identity) {
        orcamentoServices.deleteOrcamento(identity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
