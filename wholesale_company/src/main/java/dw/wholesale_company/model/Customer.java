package dw.wholesale_company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="고객")
public class Customer {
    @Id
    @Column(name="고객번호")
    private String customerId;
    @Column(name="고객회사명")
    private String customerName;
    @Column(name="담당자명")
    private String customerEmployee;
    @Column(name="담당자직위")
    private String employeeTitle;
    @Column(name="주소")
    private String address;
    @Column(name="도시")
    private String city;
    @Column(name="지역")
    private String area;
    @Column(name="전화번호")
    private String phoneNumber;
    @Column(name="마일리지")
    private int mileage;
}
