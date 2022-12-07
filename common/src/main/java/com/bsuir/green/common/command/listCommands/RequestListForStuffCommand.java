package com.bsuir.green.common.command.listCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.model.Stuff;
import lombok.Data;

@Data
public class RequestListForStuffCommand extends CommandDto {
    Stuff stuff;
    public RequestListForStuffCommand(Stuff stuff) {
        this.command = Command.REQUEST_LIST_FOR_STUFF;
        this.stuff = stuff;
    }
}
