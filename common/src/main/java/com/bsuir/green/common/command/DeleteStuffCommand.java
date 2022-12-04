package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Stuff;
import lombok.Data;

@Data
public class DeleteStuffCommand extends CommandDto {
    Stuff stuff;
    public DeleteStuffCommand(Stuff stuff){
        this.command = Command.DELETE_STUFF;
        this.stuff = stuff;
    }
}
