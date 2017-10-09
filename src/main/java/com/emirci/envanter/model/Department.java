package com.emirci.envanter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
@Table(name = "XAttDepartment")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DepartmentId", unique = true)
    private Long departmentId;

    @Column(name = "AuthorizedUserId")
    private String authorizedUserId;

    @Column(name = "DepartmentName")
    private String departmentName;

    @OneToMany(mappedBy = "departments", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Inventory> inventoryes;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Set<Inventory> getInventoryes() {
        return inventoryes;
    }

    public void setInventoryes(Set<Inventory> inventoryes) {
        this.inventoryes = inventoryes;
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
