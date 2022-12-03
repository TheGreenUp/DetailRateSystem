package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Stuff;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddUserCommand extends CommandDto {

    private final Stuff stuff;

    public AddUserCommand(Stuff stuff) {
        this.command = Command.ADD_USER;
        this.stuff = stuff;
    }
}
