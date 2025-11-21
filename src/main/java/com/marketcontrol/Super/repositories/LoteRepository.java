package com.marketcontrol.Super.repositories;

import com.marketcontrol.Super.model.LoteModel;
import com.marketcontrol.Super.model.enums.StatusLote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoteRepository  extends JpaRepository<LoteModel, UUID> {

    List<LoteModel> findByDataValidadeBetweenAndStatus(LocalDate dataInicio, LocalDate dataFim, StatusLote status);

    List<LoteModel> findByDataValidadeBeforeAndStatus(LocalDate hoje, StatusLote status);

    List<LoteModel> findByProdutoId(UUID produtoId);
}