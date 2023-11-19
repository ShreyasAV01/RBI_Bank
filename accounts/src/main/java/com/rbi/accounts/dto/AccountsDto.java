package com.rbi.accounts.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {

    private long accountNumber;

    private String branchAddress;

    private String accountType;
}
