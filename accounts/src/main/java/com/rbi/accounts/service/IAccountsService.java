package com.rbi.accounts.service;

import com.rbi.accounts.dto.CustomerDto;

public interface IAccountsService {
    /**
     *
     * @param customerDto
     */

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    Boolean updateAccount(CustomerDto customerDto);
    Boolean deleteAccount(String mobileNumber);
}
