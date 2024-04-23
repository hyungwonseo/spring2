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
@Table(name="부서")
public class Department {
    @Id
    @Column(name="부서번호")
    private String departId;
    @Column(name="부서명")
    private String departName;
}
