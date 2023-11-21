package com.rbi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",description = "schema to hold Customer information")
public class CustomerDto {

    @Schema(description = "Customer name",example = "Shreyas V")
    @NotEmpty(message = "name cannot be null or empty")
    @Size(min = 5 ,max = 30, message = "The length of customer name should be between 5 and 30")
    private String name;

    @Schema(description = "customer email id",example = "shreyasv01@gmail.com")
    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(description = "customer mobile number",example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
