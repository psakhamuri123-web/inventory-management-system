package com.phalguni.inventory.controller;

import com.phalguni.inventory.entity.Category;
import com.phalguni.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/categories") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repo;
    @GetMapping public List<Category> all(){ return repo.findAll(); }
    @GetMapping("/{id}") public Category one(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }
    @PostMapping public Category create(@RequestBody Category c){ c.setId(null); return repo.save(c); }
    @PutMapping("/{id}") public Category update(@PathVariable Long id,@RequestBody Category c){
        Category old=repo.findById(id).orElseThrow(); old.setName(c.getName()); old.setDescription(c.getDescription()); return repo.save(old);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
