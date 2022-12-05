package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Client;
import lombok.Data;

@Data
public class GetDetailedClientResolutionCommand extends CommandDto {
    Client client;
    public GetDetailedClientResolutionCommand(Client client) {
        this.command = Command.GET_DETAILED_CLIENT_RESOLUTIONS;
        this.client = client;

    }
}
