package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import lombok.Data;
@Data
public class GetDetailedResolutionCommand extends CommandDto {
    public GetDetailedResolutionCommand(){
        this.command = Command.GET_DETAILED_RESOLUTIONS;
    }
}
