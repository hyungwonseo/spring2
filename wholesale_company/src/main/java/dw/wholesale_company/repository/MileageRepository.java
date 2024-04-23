package dw.wholesale_company.repository;

import dw.wholesale_company.model.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageRepository extends JpaRepository<Mileage, String> {
}
