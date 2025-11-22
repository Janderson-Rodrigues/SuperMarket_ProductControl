package com.marketcontrol.Super.services;

import com.marketcontrol.Super.dtos.ProductRecordDto;
import com.marketcontrol.Super.model.ProductModel;
import com.marketcontrol.Super.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public ProductModel save(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setCategoria(productModel.getCategoria().toUpperCase());
        productModel.setName(productModel.getName().toUpperCase());

        return productRepository.save(productModel);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findByid(UUID id) {
        return productRepository.findById(id);
    }

    public ProductModel update(UUID id, ProductRecordDto productRecordDto) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        productModel.setCategoria(productModel.getCategoria().toUpperCase());
        productModel.setName(productModel.getName().toUpperCase());
        return productRepository.save(productModel);
    }

    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
