package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Stuff;
import lombok.Data;

@Data
public class UpdateStuffCommand extends CommandDto {
    Stuff stuff;
    public UpdateStuffCommand(Stuff stuff) {
        this.command = Command.UPDATE_STUFF;
        this.stuff = stuff;
    }
}
