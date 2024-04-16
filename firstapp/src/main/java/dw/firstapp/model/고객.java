package dw.firstapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class 고객 {
    @Id
    @Column(name="고객번호")
    String customerId;
    @Column(name="고객회사명")
    String customerName;
    @Column(name="담당자명")
    String customerEmployee;
    @Column(name="담당자직위")
    String employeeTitle;
    @Column(name="주소")
    String address;
    @Column(name="도시")
    String city;
    @Column(name="지역")
    String area;
    @Column(name="전화번호")
    String phoneNumber;
    @Column(name="마일리지")
    int mileage;

    public 고객() {
    }

    public 고객(String customerId, String customerName, String customerEmployee, String employeeTitle, String address, String city, String area, String phoneNumber, int mileage) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmployee = customerEmployee;
        this.employeeTitle = employeeTitle;
        this.address = address;
        this.city = city;
        this.area = area;
        this.phoneNumber = phoneNumber;
        this.mileage = mileage;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmployee() {
        return customerEmployee;
    }

    public void setCustomerEmployee(String customerEmployee) {
        this.customerEmployee = customerEmployee;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
