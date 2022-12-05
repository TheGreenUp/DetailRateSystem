package com.bsuir.green.common.command;

import com.bsuir.green.common.model.Detail;
import lombok.Data;

@Data
public class GetRateQuestionsCommand extends  CommandDto{
    Detail detail;
    public GetRateQuestionsCommand(Detail detail){
        this.command = Command.QUESTION_LIST;
        this.detail = detail;
    }
}
