package com.phalguni.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="inventory_transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InventoryTransaction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    private Product product;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TransactionType type;
    @Column(nullable=false)
    private Integer quantity;
    private String reference;
    private String notes;
    @Column(nullable=false)
    private LocalDateTime transactionDate;
}
