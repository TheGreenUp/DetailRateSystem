package com.bsuir.green.common.command;

import lombok.Data;

@Data
public class RequestListCommand  extends CommandDto{
    public RequestListCommand() {
        this.command = Command.REQUEST_LIST;
    }

}
