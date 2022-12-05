package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Client;
import lombok.Data;

@Data
public class GetResolutionCommand extends CommandDto{
    Client client;
    public GetResolutionCommand(Client client){
        this.command = Command.GET_RESOLUTION;
        this.client = client;
    }
}
