package com.phalguni.inventory.controller;

import com.phalguni.inventory.entity.Product;
import com.phalguni.inventory.repository.ProductRepository;
import com.phalguni.inventory.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repo;

    @GetMapping
    public List<Product> all(@RequestParam(required=false) String search) {
        return search == null || search.isBlank() ? repo.findAll() : repo.search(search);
    }
    @GetMapping("/low-stock")
    public List<Product> lowStock(){ return repo.findByQuantityLessThanEqual(10); }
    @GetMapping("/{id}")
    public Product one(@PathVariable Long id){ return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found")); }
    @PostMapping
    public Product create(@RequestBody Product p){ p.setId(null); return repo.save(p); }
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product p){
        Product old=one(id);
        old.setSku(p.getSku()); old.setName(p.getName()); old.setDescription(p.getDescription());
        old.setPrice(p.getPrice()); old.setQuantity(p.getQuantity()); old.setReorderLevel(p.getReorderLevel());
        old.setCategory(p.getCategory()); old.setSupplier(p.getSupplier());
        return repo.save(old);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
