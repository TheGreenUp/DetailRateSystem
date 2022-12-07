package com.bsuir.green.common.command.getCommands;

import com.bsuir.green.common.command.Command;
import com.bsuir.green.common.command.CommandDto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class GetDetailedResolutionBetweenDatesCommand extends CommandDto {
    java.sql.Date startDate;
    java.sql.Date endDate;
    public GetDetailedResolutionBetweenDatesCommand(LocalDate startDate, LocalDate endDate) {
        this.command = Command.GET_DETAILED_RESOLUTIONS_BETWEEN_DATES;
        this.startDate = java.sql.Date.valueOf(startDate);
        this.endDate = java.sql.Date.valueOf(endDate);;
    }

}
