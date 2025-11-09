package com.marketcontrol.Super.repositories;

import com.marketcontrol.Super.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository  extends JpaRepository<ProductModel, UUID> {
}
