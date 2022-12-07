package com.bsuir.green.common.response.getResponse;

import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.response.Response;
import lombok.Data;

@Data
public class GetDetailResponse implements Response {
    Detail detail;
    public GetDetailResponse(Detail detail){
        this.detail = detail;
    }
}
