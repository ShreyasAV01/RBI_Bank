package com.example.loans.service;

import com.example.loans.constants.LoansConstants;
import com.example.loans.dto.LoansDto;
import com.example.loans.entity.Loans;
import com.example.loans.exception.LoanAlreadyExistsException;
import com.example.loans.exception.ResourceNotfoundException;
import com.example.loans.mapper.LoansMapper;
import com.example.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService{

    private LoansRepository loansRepository;

    /**
     * @param mobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan  = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
    return newLoan;
    }

    /**
     * @param mobileNumber
     * @return Loan details based on the provided mobile Number
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotfoundException("Loan","mobileNumber",mobileNumber) );
        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    /**
     * @param loansDto
     * @return boolean indicating if loans updation is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(()
                -> new ResourceNotfoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }


    /**
     * @param mobileNumber
     * @return boolean indicating if loans deletion is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotfoundException("loan", "mobileNumber", mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
