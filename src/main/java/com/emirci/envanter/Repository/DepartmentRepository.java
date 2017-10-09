package com.emirci.envanter.Repository;

import com.emirci.envanter.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serdaremirci on 9/20/17.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
    List<Department> findAll();
}
