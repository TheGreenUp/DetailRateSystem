package com.bsuir.green.common.command.createCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.model.Stuff;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddStuffCommand extends CommandDto {
    private final Stuff stuff;

    public AddStuffCommand(Stuff stuff) {
        this.command = Command.ADD_STUFF;
        this.stuff = stuff;
    }
}
