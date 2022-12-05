package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Resolution;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetDetailByResolutionCommand extends CommandDto{
     ArrayList<Resolution> resolutions;
    public GetDetailByResolutionCommand(ArrayList<Resolution> resolutions){
        this.command = Command.GET_DETAILS_BY_RESOLUTION;
        this.resolutions = resolutions;
    }
}
