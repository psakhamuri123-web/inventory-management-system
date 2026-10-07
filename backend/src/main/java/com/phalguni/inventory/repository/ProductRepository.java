package com.phalguni.inventory.repository;
import com.phalguni.inventory.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where lower(p.name) like lower(concat('%', :q, '%')) or lower(p.sku) like lower(concat('%', :q, '%'))")
    List<Product> search(@Param("q") String q);
    List<Product> findByQuantityLessThanEqual(Integer level);
}
