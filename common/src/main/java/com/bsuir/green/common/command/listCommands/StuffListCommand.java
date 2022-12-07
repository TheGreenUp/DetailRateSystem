package com.bsuir.green.common.command.listCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;

public class StuffListCommand extends CommandDto {
    public StuffListCommand() {
        this.command = Command.STUFF_LIST;
    }
}
