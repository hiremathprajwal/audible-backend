package com.audible.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.audible.dto.CustomerDTO;
import com.audible.entity.Customer;
import com.audible.entity.PasswordHistory;
import com.audible.exception.AudibleException;
import com.audible.repository.CustomerRepository;
import com.audible.repository.PasswordHistoryRepository;
import com.audible.service.CustomerService;

@Service

@Transactional

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordHistoryRepository passwordHistoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

        if (customerRepository.findByUsername(customerDTO.getUsername()).isPresent()) {

            throw new AudibleException("Service.Customer.USERNAME_EXISTS");

        }

        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {

            throw new AudibleException("Service.Customer.EMAIL_EXISTS");

        }

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        String hashedPassword = passwordEncoder.encode(customerDTO.getPassword());
        customer.setPassword(hashedPassword);
        Customer savedCustomer = customerRepository.save(customer);

        PasswordHistory history = new PasswordHistory();
        history.setCustomer(savedCustomer);
        history.setPasswordHash(hashedPassword);

        passwordHistoryRepository.save(history);

        return modelMapper.map(savedCustomer, CustomerDTO.class);

    }

    @Override

    public CustomerDTO getCustomerById(Integer customerId) {

        Customer customer = customerRepository.findById(customerId)

                .orElseThrow(() -> new AudibleException("Service.Customer.ID_NOT_FOUND"));

        return modelMapper.map(customer, CustomerDTO.class);

    }

    @Override

    public CustomerDTO getCustomerByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email)

                .orElseThrow(() -> new AudibleException("Service.Customer.EMAIL_INVALID"));

        return modelMapper.map(customer, CustomerDTO.class);

    }

    @Override

    public CustomerDTO login(String username, String password) {

        Customer customer = customerRepository.findByUsername(username)

                .orElseThrow(() -> new AudibleException("Service.Customer.USERNAME_INVALID"));

        if (!passwordEncoder.matches(password, customer.getPassword())) {

            throw new AudibleException("Service.Customer.INVALID_PASSWORD");

        }

        return modelMapper.map(customer, CustomerDTO.class);

    }

    @Override

    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll().stream()

                .map(c -> modelMapper.map(c, CustomerDTO.class))

                .toList();

    }

    @Override
    public void changePassword(Integer customerId, String newPassword, String oldPassword) {

        Customer customer = customerRepository.findById(customerId)

                .orElseThrow(() -> new AudibleException("Service.Customer.ID_NOT_FOUND"));

        if (!passwordEncoder.matches(oldPassword, customer.getPassword())) {

            throw new AudibleException("Service.Customer.CHANGEEPASSWORD");

        }

        List<PasswordHistory> lastPasswords = passwordHistoryRepository.findTop3ByCustomerOrderByPasswordHistoryIdDesc(customer);

        for (PasswordHistory ph : lastPasswords) {

            if (passwordEncoder.matches(newPassword, ph.getPasswordHash())) {

                throw new AudibleException("Service.Customer.PASSWORD_REUSE");

            }

        }

        if (passwordEncoder.matches(newPassword, customer.getPassword())) {

            throw new AudibleException("Service.Customer.PASSWORD_REUSE");

        }

        PasswordHistory history = new PasswordHistory();

        history.setCustomer(customer);

        history.setPasswordHash(customer.getPassword());

        passwordHistoryRepository.save(history);

        customer.setPassword(passwordEncoder.encode(newPassword));

        customerRepository.save(customer);

    }

    @Override

    public String getEmailByUsername(String username) {

        Customer c = customerRepository.findByUsername(username)

                .orElseThrow(() -> new AudibleException("Service.Customer.NOT_FOUND_BY_USERNAME"));

        return c.getEmail();

    }

    @Override
    public void forgotPassword(String email, String newPassword) {

        Customer customer = customerRepository.findByEmail(email)

                .orElseThrow(() -> new AudibleException("Service.Customer.EMAIL_NOT_FOUND"));

        List<PasswordHistory> historyList = passwordHistoryRepository

                .findByCustomerCustomerIdOrderByPasswordHistoryIdDesc(customer.getCustomerId());

        int checkCount = Math.min(historyList.size(), 3);

        for (int i = 0; i < checkCount; i++) {

            String oldHash = historyList.get(i).getPasswordHash();

            if (passwordEncoder.matches(newPassword, oldHash)) {

                throw new AudibleException("Service.Customer.PASSWORD_REUSE");

            }

        }

        if (passwordEncoder.matches(newPassword, customer.getPassword())) {

            throw new AudibleException("Service.Customer.PASSWORD_REUSE");

        }

        String newHash = passwordEncoder.encode(newPassword);

        customer.setPassword(newHash);

        customerRepository.save(customer);

        PasswordHistory history = new PasswordHistory();

        history.setCustomer(customer);

        history.setPasswordHash(newHash);

        passwordHistoryRepository.save(history);

    }

}