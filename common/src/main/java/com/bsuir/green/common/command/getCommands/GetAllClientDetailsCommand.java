package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.model.Client;
import lombok.Data;

@Data
public class GetAllClientDetailsCommand extends CommandDto {
    Client client;
    public GetAllClientDetailsCommand(Client client) {
        this.command = Command.GET_ALL_CLIENTS_DETAILS;
        this.client = client;
    }
}
