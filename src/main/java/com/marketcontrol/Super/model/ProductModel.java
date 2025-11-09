package com.marketcontrol.Super.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Data @Entity @Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    @Column(nullable = false)
    private String name;
    @Column(name = "codigo_barras", unique = true)
    private double cod_barras;
    private long categoria_id;

}
