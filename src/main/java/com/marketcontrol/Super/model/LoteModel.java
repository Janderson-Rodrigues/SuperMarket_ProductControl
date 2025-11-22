package com.marketcontrol.Super.model;

import com.fasterxml.jackson.annotation.*;
import com.marketcontrol.Super.model.enums.StatusLote;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_lotes")
public class LoteModel {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idLote;

    @Column(name = "data_validade", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "qtd_inicial", nullable = false)
    private Integer quantidadeInicial;

    @Column(name = "qtd_atual", nullable = false)
    private Integer quantidadeAtual;

    @Column(name = "prc_custo")
    private BigDecimal precoCusto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusLote statusLote = StatusLote.ATIVO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "lotes"})
    @ToString.Exclude
    private ProductModel produto;

}
