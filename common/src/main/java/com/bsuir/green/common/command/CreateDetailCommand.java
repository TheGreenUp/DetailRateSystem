package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Detail;
import lombok.Data;

@Data
public class CreateDetailCommand  extends CommandDto{
    Detail detail;
    public CreateDetailCommand(Detail detail) {
        this.command = Command.CREATE_DETAIL;
        this.detail = detail;

    }
}
