package com.bsuir.green.common.command;

import com.bsuir.green.common.utils.Helper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginCommand extends CommandDto {
    private String name;
    private String password;

    public LoginCommand(String name, String password) {
        this.command = Command.LOGIN;
        this.name = name;
        try {
            this.password = Helper.getInstance().getPasswordHash(password);//хэшим пароль
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }
}

