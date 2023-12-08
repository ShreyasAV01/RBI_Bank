package com.example.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data

public class LoansDto {

    @NotEmpty(message = "MobileNumber cannot be null/empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be null/empty")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be null/empty")
    private String loanType;

    @Positive(message = "Total loan amount must be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero ")
    private int outstandingAmount;

}
