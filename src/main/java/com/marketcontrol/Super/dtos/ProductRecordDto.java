package com.marketcontrol.Super.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(@NotBlank String name, @NotBlank String cod_barras, @NotNull String categoria) {
}
