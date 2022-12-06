package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.model.Client;
import lombok.Data;

@Data
public class GetResolutionCommand extends CommandDto {
    Client client;
    public GetResolutionCommand(Client client){
        this.command = Command.GET_RESOLUTION;
        this.client = client;
    }
}
