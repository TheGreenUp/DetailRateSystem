package com.bsuir.green.common.response.getResponse;

import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetClientIdFromRequestResponse implements Response {
    Client client;
}
