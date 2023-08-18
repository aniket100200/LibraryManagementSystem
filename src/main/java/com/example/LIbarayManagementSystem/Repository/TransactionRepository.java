package com.example.LIbarayManagementSystem.Repository;

import com.example.LIbarayManagementSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>
{
}
