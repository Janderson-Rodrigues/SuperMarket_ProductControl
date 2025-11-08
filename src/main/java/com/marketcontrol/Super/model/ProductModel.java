package com.marketcontrol.Super.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data @Entity @Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private double codigo_barras;

}
