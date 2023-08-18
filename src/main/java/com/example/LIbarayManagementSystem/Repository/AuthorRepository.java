package com.example.LIbarayManagementSystem.Repository;

import com.example.LIbarayManagementSystem.Models.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>
{
}
