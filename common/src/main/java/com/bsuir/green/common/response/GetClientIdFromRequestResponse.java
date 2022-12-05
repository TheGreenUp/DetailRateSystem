package com.bsuir.green.common.response;

import com.bsuir.green.common.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetClientIdFromRequestResponse implements Response{
    Client client;
}
