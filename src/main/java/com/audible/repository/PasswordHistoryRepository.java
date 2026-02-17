package com.audible.repository;

import java.util.List;

import com.audible.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audible.entity.PasswordHistory;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Integer> {
    List<PasswordHistory> findByCustomerCustomerIdOrderByPasswordHistoryIdDesc(Integer customerId);
    List<PasswordHistory> findTop3ByCustomerOrderByPasswordHistoryIdDesc(Customer customer);
}
