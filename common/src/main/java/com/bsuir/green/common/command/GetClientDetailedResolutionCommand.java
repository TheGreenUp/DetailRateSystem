package com.bsuir.green.common.command;

import lombok.Data;

import static com.bsuir.green.common.command.Command.GET_ALL_CLIENT_DETAILED_RESOLUTIONS;
@Data
public class GetClientDetailedResolutionCommand extends CommandDto{
    int id;
    public GetClientDetailedResolutionCommand(int id){
        this.command = GET_ALL_CLIENT_DETAILED_RESOLUTIONS;
        this.id = id;
    }
}
