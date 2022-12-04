package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Stuff;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserCommand extends CommandDto {

    private final Stuff stuff;

    public UpdateUserCommand(Stuff stuff) {
        this.command = Command.UPDATE_USER;
        this.stuff = stuff;
    }
}
