package com.bsuir.green.common.command;

import lombok.Data;
@Data
public class GetDetailedResolutionCommand extends CommandDto{
    public GetDetailedResolutionCommand(){
        this.command = Command.GET_DETAILED_RESOLUTIONS;
    }
}
