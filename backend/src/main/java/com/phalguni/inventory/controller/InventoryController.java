package com.phalguni.inventory.controller;

import com.phalguni.inventory.dto.StockRequest;
import com.phalguni.inventory.entity.*;
import com.phalguni.inventory.repository.*;
import com.phalguni.inventory.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController @RequestMapping("/api/inventory") @RequiredArgsConstructor
public class InventoryController {
    private final ProductRepository products;
    private final InventoryTransactionRepository transactions;

    @GetMapping("/transactions")
    public List<InventoryTransaction> transactions(){ return transactions.findTop50ByOrderByTransactionDateDesc(); }

    @PostMapping("/transaction")
    @Transactional
    public InventoryTransaction transaction(@RequestBody StockRequest request) {
        if (request.quantity() == null || request.quantity() <= 0) throw new IllegalArgumentException("Quantity must be positive");
        Product p = products.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        int current = p.getQuantity();
        int newQty;
        if (request.type() == TransactionType.STOCK_IN) newQty = current + request.quantity();
        else if (request.type() == TransactionType.STOCK_OUT) {
            if (request.quantity() > current) throw new IllegalArgumentException("Insufficient stock");
            newQty = current - request.quantity();
        } else {
            newQty = request.quantity();
        }
        p.setQuantity(newQty);
        products.save(p);

        return transactions.save(InventoryTransaction.builder()
                .product(p).type(request.type()).quantity(request.quantity())
                .reference(request.reference()).notes(request.notes())
                .transactionDate(LocalDateTime.now()).build());
    }
}
