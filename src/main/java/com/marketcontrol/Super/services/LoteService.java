package com.marketcontrol.Super.services;

import com.marketcontrol.Super.dtos.LoteRecordDto;
import com.marketcontrol.Super.model.LoteModel;
import com.marketcontrol.Super.model.ProductModel;
import com.marketcontrol.Super.model.enums.StatusLote;
import com.marketcontrol.Super.repositories.LoteRepository;
import com.marketcontrol.Super.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LoteService {

     final LoteRepository loteRepository;
     final ProductRepository productRepository;

    public LoteService(LoteRepository loteRepository, ProductRepository productRepository) {
        this.loteRepository = loteRepository;
        this.productRepository = productRepository;
    }
    public LoteModel save(LoteRecordDto loteRecordDto){
        ProductModel product = productRepository.findById(loteRecordDto.idProduct())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado. Impossível criar lote."));
        var loteModel = new LoteModel();
        BeanUtils.copyProperties(loteRecordDto, loteModel);

        // Regras de inicialização
        loteModel.setQuantidadeInicial(loteRecordDto.quantidade());
        loteModel.setQuantidadeAtual(loteRecordDto.quantidade());
        loteModel.setStatusLote(StatusLote.ATIVO);

        // Amarra o produto
        loteModel.setProduto(product);

        return loteRepository.save(loteModel);
    }
    // --- 2. LEITURA GERAL ---
    public List<LoteModel> findAll() {
        return loteRepository.findAll();
    }

    // --- 3. INTELIGÊNCIA DO NEGÓCIO (O Diferencial) ---

    /**
     * Retorna lotes que vão vencer nos próximos X dias.
     * Exemplo: Se hoje é dia 01 e diasParaVencer = 7, busca entre dia 01 e 08.
     */
    public List<LoteModel> buscarLotesEmAlerta(int diasParaVencer) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataLimite = hoje.plusDays(diasParaVencer);

        // Usa o método personalizado que criamos no Repository
        return loteRepository.findByExpirationDateBetweenAndStatusLote(
                hoje,
                dataLimite,
                StatusLote.ATIVO // Só queremos ver o que ainda está na prateleira
        );
    }

    /**
     * Retorna lotes que JÁ venceram, mas ainda constam como ATIVO (erro de processo).
     */
    public List<LoteModel> buscarLotesVencidos() {
        LocalDate hoje = LocalDate.now();

        return loteRepository.findByExpirationDateBeforeAndStatusLote(
                hoje,
                StatusLote.ATIVO
        );
    }

    /**
     * Busca todos os lotes de um produto específico (Histórico do produto)
     */
    public List<LoteModel> buscarPorProduto(UUID produtoId) {
        return loteRepository.findByProduto_IdProduct(produtoId);
    }
}
