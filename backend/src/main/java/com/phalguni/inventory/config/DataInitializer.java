package com.phalguni.inventory.config;

import com.phalguni.inventory.entity.*;
import com.phalguni.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final AppUserRepository users;
    private final CategoryRepository categories;
    private final SupplierRepository suppliers;
    private final ProductRepository products;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (users.count() == 0) {
            users.save(AppUser.builder().username("admin").password(encoder.encode("admin123")).role("ADMIN").build());
        }
        if (categories.count() == 0) {
            Category electronics = categories.save(Category.builder().name("Electronics").description("Electronic products").build());
            Category office = categories.save(Category.builder().name("Office").description("Office supplies").build());
            Supplier supplier = suppliers.save(Supplier.builder().name("Demo Supplier").email("supplier@example.com").phone("555-0100").address("Michigan, USA").build());

            products.save(Product.builder().sku("ELEC-1001").name("Wireless Keyboard")
                    .description("Bluetooth keyboard").price(new BigDecimal("49.99")).quantity(25)
                    .reorderLevel(10).category(electronics).supplier(supplier).build());
            products.save(Product.builder().sku("OFF-1001").name("Notebook")
                    .description("Hard-cover notebook").price(new BigDecimal("8.99")).quantity(8)
                    .reorderLevel(10).category(office).supplier(supplier).build());
        }
    }
}
