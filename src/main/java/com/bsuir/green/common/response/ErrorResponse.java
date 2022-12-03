package com.bsuir.green.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse implements Response {

    private String errorMessage;
}
