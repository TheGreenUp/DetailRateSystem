package com.bsuir.green.common.response;

import com.bsuir.green.common.model.Client;
import com.bsuir.green.common.model.Stuff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse implements Response {
    Stuff stuff;
    Client client;
}
