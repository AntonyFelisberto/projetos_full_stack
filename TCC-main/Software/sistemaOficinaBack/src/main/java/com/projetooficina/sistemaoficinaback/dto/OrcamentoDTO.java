package com.projetooficina.sistemaoficinaback.dto;

import com.projetooficina.sistemaoficinaback.model.Orcamento;
import lombok.Data;

@Data
public class OrcamentoDTO {

    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String celular;
    private String veiculo;
    private Long idNota;

    public OrcamentoDTO(Orcamento orcamento){
        this.nome = orcamento.getCliente().getNome();
        this.email = orcamento.getCliente().getEmail();
        this.endereco = orcamento.getCliente().getEndereco();
        this.telefone = orcamento.getCliente().getTelefone();
        this.celular= orcamento.getCliente().getCelular();
        this.veiculo = orcamento.getCliente().getVeiculo();
        this.idNota = orcamento.getIdOrcamento();
    }

}
