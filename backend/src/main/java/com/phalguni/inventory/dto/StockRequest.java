package com.phalguni.inventory.dto;
import com.phalguni.inventory.entity.TransactionType;
public record StockRequest(Long productId, Integer quantity, TransactionType type, String reference, String notes) {}
