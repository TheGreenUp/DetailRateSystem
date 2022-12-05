package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Resolution;
import lombok.Data;

@Data
public class MakeResolutionCommand  extends  CommandDto{
    Resolution resolution;
    public MakeResolutionCommand(Resolution resolution) {
        this.command = Command.CREATE_RESOLUTION;
        this.resolution = resolution;

    }
}
