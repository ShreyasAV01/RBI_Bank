package com.rbi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts",description = "schema to hold Account information")
public class AccountsDto {

    @Schema(description = "Account number of RBI")
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
    private long accountNumber;

    @Schema(description = "RBI branch address")
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;

    @Schema(description = "Account type of RBI",example = "Savings")
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;
}
