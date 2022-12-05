package com.bsuir.green.common.command;

import lombok.Data;

@Data
public class UpdateRequestStatusCommand extends CommandDto {
    int requestId;
    public UpdateRequestStatusCommand(int requestId) {
        this.command = Command.UPDATE_REQUEST_STATUS;
        this.requestId = requestId;
    }

}
