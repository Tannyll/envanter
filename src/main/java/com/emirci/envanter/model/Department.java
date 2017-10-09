package com.emirci.envanter.model;

import javax.persistence.*;

/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
@Table(name = "XAttDepartment")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DepartmentId", unique = true)
    private Long departmentId;

    @Column(name = "AuthorizedUserId")
    private String authorizedUserId;

    @Column(name = "DepartmentName")
    private String departmentName;

    @OneToOne(mappedBy = "departments")
    @PrimaryKeyJoinColumn
    private Inventory inventory;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getAuthorizedUserId() {
        return authorizedUserId;
    }

    public void setAuthorizedUserId(String authorizedUserId) {
        this.authorizedUserId = authorizedUserId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() {

    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                '}';
    }
}
