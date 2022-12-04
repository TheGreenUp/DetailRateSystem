package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterCommand extends CommandDto {
    private Client client;
    public RegisterCommand(Client client) {
        this.command = Command.REGISTER;
        this.client = client;
    }
}
