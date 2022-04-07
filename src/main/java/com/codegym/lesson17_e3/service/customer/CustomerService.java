package com.codegym.lesson17_e3.service.customer;

import com.codegym.lesson17_e3.model.Customer;
import com.codegym.lesson17_e3.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository customerRepo;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepo.deleteById(id);
    }
}
