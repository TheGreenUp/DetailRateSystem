package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import com.bsuir.green.common.model.Detail;
import lombok.Data;

@Data
public class GetDetailCommand extends CommandDto {
    Detail detail;
    public GetDetailCommand(Detail detail) {
        this.command = Command.GET_DETAIL;
        this.detail = detail;
    }
}
