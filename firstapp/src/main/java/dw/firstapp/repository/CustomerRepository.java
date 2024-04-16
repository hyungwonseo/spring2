package dw.firstapp.repository;

import dw.firstapp.model.고객;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<고객, String> {
}
