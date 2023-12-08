package com.example.loans.service;

import com.example.loans.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber
     */
    void  createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber
     * @return Loan details based on the provided mobile Number
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto
     * @return boolean indicating if loans updation is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber
     * @return  boolean indicating if loans deletion is successful or not
     */
    boolean deleteLoan(String mobileNumber);;

}
