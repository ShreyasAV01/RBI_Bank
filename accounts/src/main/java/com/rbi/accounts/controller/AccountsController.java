package com.rbi.accounts.controller;

import com.rbi.accounts.constants.AccountsConstants;
import com.rbi.accounts.dto.CustomerDto;
import com.rbi.accounts.dto.ErrorResponseDto;
import com.rbi.accounts.dto.ResponseDto;
import com.rbi.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "REST API for Accounts",description = "REST API for CREATE, FETCH, DELETE, UPDATE")
@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    /**
     *
     * @param customerDto
     * @return
     */
    @Operation(summary = "Create Account REST API",
                description = "REST API to create new Customer and Account in RBI")
    @ApiResponse(responseCode = "201",description = "HTTP status CREATED")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch account REST API",
                description = "REST API to fetch Customer and Account details from RBI")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "HTTP status OK"),
                    @ApiResponse(responseCode = "500",description = "HTTP status Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update account details REST API",
                description = "REST API to update customer and Account details based on Account number in RBI")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "HTTP status OK"),
                    @ApiResponse(responseCode = "417",description = "Expectation failed"),
                    @ApiResponse(responseCode = "500",description = "HTTP status Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        Boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
    }
}

    @Operation(summary = "Delete account and customer details REST API",
                description = "REST API to delete the customer and Account details based on mobile number in RBI")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "HTTP status OK"),
            @ApiResponse(responseCode = "417",description = "Expectation failed"),
            @ApiResponse(responseCode = "500",description = "HTTP status Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        Boolean isUpdated = iAccountsService.deleteAccount(mobileNumber);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }



}
