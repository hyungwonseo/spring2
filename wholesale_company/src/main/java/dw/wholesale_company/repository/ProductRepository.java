package dw.wholesale_company.repository;

import dw.wholesale_company.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
