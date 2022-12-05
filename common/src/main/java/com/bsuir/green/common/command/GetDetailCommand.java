package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Detail;
import lombok.Data;

@Data
public class GetDetailCommand extends CommandDto{
    Detail detail;
    public GetDetailCommand(Detail detail) {
        this.command = Command.GET_DETAIL;
        this.detail = detail;
    }
}
