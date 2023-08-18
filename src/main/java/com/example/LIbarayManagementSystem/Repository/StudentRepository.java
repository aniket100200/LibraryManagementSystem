package com.example.LIbarayManagementSystem.Repository;

import com.example.LIbarayManagementSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
