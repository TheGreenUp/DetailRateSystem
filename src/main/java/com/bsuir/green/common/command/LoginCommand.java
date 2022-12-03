package com.bsuir.green.common.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginCommand extends CommandDto {
    private String name;
    private String password;

    public LoginCommand(String name, String password) {
        this.command = Command.LOGIN;
        this.name = name;
        this.password = password;
    }
}

