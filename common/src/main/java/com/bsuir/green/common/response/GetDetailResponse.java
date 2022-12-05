package com.bsuir.green.common.response;

import com.bsuir.green.common.model.Detail;
import lombok.Data;

@Data
public class GetDetailResponse implements Response{
    Detail detail;
    public GetDetailResponse(Detail detail){
        this.detail = detail;
    }
}
