package com.example.LIbarayManagementSystem.Repository;

import com.example.LIbarayManagementSystem.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer>
{
}
