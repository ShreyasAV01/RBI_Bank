package com.rbi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
@Schema(name = "Response",description = "Schema to hold successful response information")
public class ResponseDto {

    @Schema(description = "status code in response")
    private String statusCode;

    @Schema(description = "status message in response")
    private String statusMsg;
}
