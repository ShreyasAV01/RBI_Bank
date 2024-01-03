package com.rbi.accounts.controller;

import com.rbi.accounts.dto.CustomerDetailsDto;
import com.rbi.accounts.dto.ErrorResponseDto;
import com.rbi.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REST API for the customer of RBI",description = "REST API in RBI to fetch the Customer details")
@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService=iCustomerService;
    }
    @Operation(summary = "Fetch Customer details REST API",
            description = "REST API to fetch Customer details from RBI")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "HTTP status OK"),
            @ApiResponse(responseCode = "500",description = "HTTP status Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})",
                                                                           message = "mobile number must be 10 digit")
                                                                   String mobileNumber){
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
