package com.marketcontrol.Super.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoteRecordDto(@NotNull UUID idProduct, @NotNull LocalDate expirationDate, @NotNull @Positive Integer quantidade, BigDecimal precoCusto) {
}
