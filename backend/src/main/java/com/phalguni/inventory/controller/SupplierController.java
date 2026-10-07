package com.phalguni.inventory.controller;

import com.phalguni.inventory.entity.Supplier;
import com.phalguni.inventory.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/suppliers") @RequiredArgsConstructor
public class SupplierController {
    private final SupplierRepository repo;
    @GetMapping public List<Supplier> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Supplier one(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @PostMapping public Supplier create(@RequestBody Supplier s){ s.setId(null); return repo.save(s); }
    @PutMapping("/{id}") public Supplier update(@PathVariable Long id,@RequestBody Supplier s){
        Supplier old=repo.findById(id).orElseThrow();
        old.setName(s.getName()); old.setEmail(s.getEmail()); old.setPhone(s.getPhone()); old.setAddress(s.getAddress());
        return repo.save(old);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
