package com.projetooficina.sistemaoficinaback.repository;

import com.projetooficina.sistemaoficinaback.model.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CronogramaRepository extends JpaRepository <Cronograma, Long> {

    @Query("SELECT c FROM Cronograma c WHERE c.usuario.idUsuario = :idUser")
    List<Cronograma> findByIdUser(@Param("idUser") Long idUser);

    @Query("SELECT c FROM Cronograma c where c.dataInicio >= :dataInicio and c.dataInicio <= :dataFim")
    List<Cronograma> findAllWithCreationDateTime(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

}
