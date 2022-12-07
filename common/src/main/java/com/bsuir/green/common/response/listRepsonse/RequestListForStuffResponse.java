package com.bsuir.green.common.response.listRepsonse;

import com.bsuir.green.common.model.RequestExtended;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestListForStuffResponse implements Response {
    List<RequestExtended> requests;
}
