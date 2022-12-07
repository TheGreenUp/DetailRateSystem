package com.bsuir.green.common.response.listRepsonse;

import com.bsuir.green.common.model.Request;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestListResponse implements Response {
    List<Request> requests;
}
