package com.phalguni.inventory.controller;

import com.phalguni.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/dashboard") @RequiredArgsConstructor
public class DashboardController {
    private final ProductRepository products;
    private final CategoryRepository categories;
    private final SupplierRepository suppliers;

    @GetMapping("/summary")
    public Map<String,Object> summary() {
        var all = products.findAll();
        long units = all.stream().mapToLong(p -> p.getQuantity()).sum();
        long low = all.stream().filter(p -> p.getQuantity() <= p.getReorderLevel()).count();
        return Map.of("products", all.size(), "categories", categories.count(),
                "suppliers", suppliers.count(), "totalUnits", units, "lowStock", low);
    }
}
