package com.codegym.lesson17_e3.repository;

import com.codegym.lesson17_e3.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
