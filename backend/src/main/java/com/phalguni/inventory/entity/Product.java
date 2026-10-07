package com.phalguni.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String sku;
    @Column(nullable=false) private String name;
    private String description;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal price;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false) private Integer reorderLevel;
    @ManyToOne(fetch=FetchType.EAGER) private Category category;
    @ManyToOne(fetch=FetchType.EAGER) private Supplier supplier;
}
