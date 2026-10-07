package com.phalguni.inventory.repository;
import com.phalguni.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepository extends JpaRepository<Supplier, Long> {}
