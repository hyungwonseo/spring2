package dw.wholesale_company.repository;

import dw.wholesale_company.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByOrderDate(LocalDate orderDate);
    @Query("SELECT c.city, SUM(od.orderQuantity * od.unitPrice) AS totalOrderAmount " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "JOIN o.customer c " +
            "GROUP BY c.city " +
            "ORDER BY totalOrderAmount DESC")
    List<Object[]> getTopCitiesByTotalOrderAmount();

    @Query("SELECT YEAR(o.orderDate), COUNT(o)" +
            "FROM Order o " +
            "WHERE o.customer.customerId IN ( " +
            "   SELECT c.customerId " +
            "   FROM Customer c " +
            "   WHERE c.city = :city " +
            ") " +
            "GROUP BY YEAR(o.orderDate)")
    List<Object[]> getOrderCountByYearForCity(@Param("city") String city);

    @Query("SELECT o FROM Order o WHERE YEAR(o.orderDate) = :year")
    List<Order> findByOrderYear(@Param("year") int year);

    @Query("SELECT o FROM Order o WHERE YEAR(o.orderDate) = :year")
    List<Order> findByYear(@Param("year") int year);

    List<Order> findByOrderId(String orderId);

    @Query("SELECT o FROM Order o WHERE EXTRACT(YEAR FROM o.orderDate) = :year")
    List<Order> findByOrderDateYear(@Param("year") int year);
}
