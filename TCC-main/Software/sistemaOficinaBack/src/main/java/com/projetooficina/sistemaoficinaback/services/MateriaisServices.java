package com.projetooficina.sistemaoficinaback.services;

import com.projetooficina.sistemaoficinaback.exception.DataIntegrityViolation;
import com.projetooficina.sistemaoficinaback.exception.ObjectNotFoundException;
import com.projetooficina.sistemaoficinaback.model.Materiais;
import com.projetooficina.sistemaoficinaback.repository.MateriaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MateriaisServices {

    private final MateriaisRepository materiaisRepository;

    public Materiais adicionarMateriais(Materiais materiais){
        verificarSeCodProdutoExiste(materiais.getCodProduto());
        materiais.setIdMaterial(null);
        return materiaisRepository.save(materiais);
    }

    public List<Materiais> encontrarTodosOsmateriais(){
        return materiaisRepository.findAll();
    }

    public Materiais encontrarTodosMateriaisPorId(Long id){
        return materiaisRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuario do id "+id+"não foi encontrado"));
    }

    public void deleteMateriais(Long id){
        materiaisRepository.deleteById(id);
    }

    public Materiais atualizarMateriais(Materiais materiais){
        Materiais materiaisAtualizado = encontrarTodosMateriaisPorId(materiais.getIdMaterial());

        if(!materiaisAtualizado.getCodProduto().equals(materiais.getCodProduto())){
            verificarSeCodProdutoExiste(materiais.getCodProduto());
        }
        materiaisAtualizado.setUnidade(materiais.getUnidade());
        materiaisAtualizado.setCodProduto(materiais.getCodProduto());
        materiaisAtualizado.setPrecoUnidade(materiais.getPrecoUnidade());
        materiaisAtualizado.setDescriminizacao(materiais.getDescriminizacao());
        materiaisAtualizado.setQuantidade(materiais.getQuantidade());
        return materiaisRepository.save(materiaisAtualizado);
    }

    public void atualizarMateriaisOrcamento(List<Materiais> materiais){
        for(int posicaoMaterial=0; posicaoMaterial<materiais.size(); posicaoMaterial++){
            Materiais materiaisAtualizado = encontrarTodosMateriaisPorId(materiais.get(posicaoMaterial).getIdMaterial());
            materiaisAtualizado.setQuantidade(materiaisAtualizado.getQuantidade() - materiais.get(posicaoMaterial).getQuantidade());
            materiaisRepository.save(materiaisAtualizado);
        }
    }

    public void verificarSeCodProdutoExiste(Integer codigoProduto){
        Optional materialJaExiste = materiaisRepository.findByCodProduto(codigoProduto);
        if(materialJaExiste.isPresent()){
            throw new DataIntegrityViolation("Atenção ja existe um material com esse código cadastrado no banco");
        }
    }

}
