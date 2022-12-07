package com.bsuir.green.common.command.listCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import lombok.Data;

@Data
public class RequestListCommand  extends CommandDto {
    public RequestListCommand() {
        this.command = Command.REQUEST_LIST;
    }

}
