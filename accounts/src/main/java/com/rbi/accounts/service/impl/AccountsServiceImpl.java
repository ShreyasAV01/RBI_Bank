package com.rbi.accounts.service.impl;

import com.rbi.accounts.constants.AccountsConstants;
import com.rbi.accounts.dto.AccountsDto;
import com.rbi.accounts.dto.CustomerDto;
import com.rbi.accounts.entity.Accounts;
import com.rbi.accounts.entity.Customer;
import com.rbi.accounts.exception.CustomerAlreadyExistsException;
import com.rbi.accounts.exception.ResourceNotFoundException;
import com.rbi.accounts.mapper.AccountsMapper;
import com.rbi.accounts.mapper.CustomerMapper;
import com.rbi.accounts.repository.AccountsRepository;
import com.rbi.accounts.repository.CustomerRepository;
import com.rbi.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"+customerDto.getMobileNumber());
        }
        customer.setCreatedBy("Shreyas");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public Boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Accounts accounts = null;
        if (accountsDto != null) {
            accounts = accountsRepository.findById(accountsDto.getAccountNumber()).
                    orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", String.valueOf(accountsDto.getAccountNumber())));
        }
        AccountsMapper.mapToAccounts(accountsDto, accounts);
        accounts = accountsRepository.save(accounts);
        Long customerId = accounts.getCustomerId();
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new ResourceNotFoundException("Customer","Customerid",customerId.toString()));
        CustomerMapper.mapToCustomer(customerDto,customer);
        customerRepository.save(customer);
        isUpdated= true;

        return isUpdated;
    }

    @Override
    public Boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     *
     * @param customer
     * @return
     */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber =1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedBy("Shreyas");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }
}
