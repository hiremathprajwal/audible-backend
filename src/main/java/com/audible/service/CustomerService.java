package com.audible.service;

import java.util.List;

import com.audible.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO registerCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Integer customerId);

    CustomerDTO getCustomerByEmail(String email);

    CustomerDTO login(String email, String password);

    List<CustomerDTO> getAllCustomers();

    String getEmailByUsername(String username);
    void changePassword(Integer customerId,String oldPassword, String newPassword);
    void forgotPassword(String email, String  newPassword);
}
