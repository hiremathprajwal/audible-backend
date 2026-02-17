package com.audible.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.audible.dto.CustomerDTO;

import com.audible.service.CustomerService;



import jakarta.validation.Valid;



@RestController

@RequestMapping("/api/customers")

//@CrossOrigin(origins = "http://localhost:3000")



public class CustomerController {



    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")



    public ResponseEntity<CustomerDTO> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO registeredCustomer = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerDTO> login(@RequestBody CustomerDTO loginRequest) {
        CustomerDTO customer = customerService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }



    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerId) {
        CustomerDTO customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }



    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        CustomerDTO customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);



    }



    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        customerService.changePassword(request.getCustomerId(),request.getNewPassword(),request.getOldPassword());
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);

    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        customerService.forgotPassword(request.getEmail(), request.getNewPassword());
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }


    public static class ChangePasswordRequest {

        private Integer customerId;
        private String newPassword;
        private String oldPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    public static class ForgotPasswordRequest {

        private String email;
        private String newPassword;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<String>getEmail(@PathVariable String username){

        String email= customerService.getEmailByUsername(username);
        return new ResponseEntity<>(email,HttpStatus.OK);

    }
}