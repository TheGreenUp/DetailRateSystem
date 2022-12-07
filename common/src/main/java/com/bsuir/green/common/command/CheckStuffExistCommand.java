package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Stuff;
import lombok.Data;

@Data
public class CheckStuffExistCommand extends CommandDto{
    Stuff stuff;
    public CheckStuffExistCommand(Stuff stuff) {
        this.command = Command.CHECK_STUFF_EXIST;
        this.stuff = stuff;
    }
}
