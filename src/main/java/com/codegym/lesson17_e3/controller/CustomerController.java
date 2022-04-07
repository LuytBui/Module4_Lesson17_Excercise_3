package com.codegym.lesson17_e3.controller;

import com.codegym.lesson17_e3.model.Customer;
import com.codegym.lesson17_e3.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute ("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created");
        return modelAndView;
    }

    @GetMapping()
    public ModelAndView showAllCustomer(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditPage(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent())
            return new ModelAndView("error-404");
        return new ModelAndView ("/customer/edit", "customer", customer.get());
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeletePage(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent())
            return new ModelAndView("error-404");
        return new ModelAndView("/customer/delete", "customer", customer.get());
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent())
            return new ModelAndView("error-404");
        customerService.delete(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete", "customer", customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer deleted");
        return modelAndView;
    }
}
