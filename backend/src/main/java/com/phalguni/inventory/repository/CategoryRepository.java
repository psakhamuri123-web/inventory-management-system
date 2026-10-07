package com.phalguni.inventory.repository;
import com.phalguni.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {}
