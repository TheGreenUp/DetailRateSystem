package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import lombok.Data;

@Data
public class GetClientIdFromRequestCommand extends CommandDto {
    int requestId;
    public GetClientIdFromRequestCommand(int requestId){
        this.command = Command.GET_CLIENT_ID_BY_REQUEST;
        this.requestId = requestId;
    }
}
