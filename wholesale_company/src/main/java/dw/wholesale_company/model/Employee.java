package dw.wholesale_company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="사원")
public class Employee {
    @Id
    @Column(name="사원번호")
    private String employeeId;
    @Column(name="이름")
    private String name;
    @Column(name="영문이름")
    private String englishName;
    @Column(name="직위")
    private String position;
    @Column(name="성별")
    private String gender;
    @Column(name="생일")
    private LocalDate birthDate;
    @Column(name="입사일")
    private LocalDate hireDate;
    @Column(name="주소")
    private String address;
    @Column(name="도시")
    private String city;
    @Column(name="지역")
    private String area;
    @Column(name="집전화")
    private String telephoneNo;
    @Column(name="상사번호")
    private String managerId;
    @ManyToOne
    @JoinColumn(name = "부서번호")
    private Department department;
}
