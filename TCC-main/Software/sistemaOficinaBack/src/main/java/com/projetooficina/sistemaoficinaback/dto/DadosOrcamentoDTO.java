package com.projetooficina.sistemaoficinaback.dto;

import com.projetooficina.sistemaoficinaback.model.Cliente;
import com.projetooficina.sistemaoficinaback.model.Materiais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosOrcamentoDTO {

    private Cliente cliente;
    private List<Materiais> materiaisCliente;
    private List<Materiais> materiaisUsuario;
    private Long numeroOrcamento;
    private Double valorTotal;

}
