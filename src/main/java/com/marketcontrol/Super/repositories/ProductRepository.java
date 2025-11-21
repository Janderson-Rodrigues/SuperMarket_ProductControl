package com.marketcontrol.Super.repositories;

import com.marketcontrol.Super.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository  extends JpaRepository<ProductModel, UUID> {
    boolean existsByCodBarras(String cod_Barras);
    Optional<ProductModel> findByCodBarras(String cod_Barras);
}
