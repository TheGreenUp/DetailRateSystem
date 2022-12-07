package com.bsuir.green.common.command.listCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;

public class SpecialistListCommand extends CommandDto {
    public SpecialistListCommand() {
        this.command = Command.SPECIALIST_LIST;
    }
}
