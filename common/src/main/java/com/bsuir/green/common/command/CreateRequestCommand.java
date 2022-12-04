package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRequestCommand extends CommandDto{
    private final Request request;

    public CreateRequestCommand(Request request) {
        this.command = Command.CREATE_REQUEST;
        this.request = request;
    }
}
