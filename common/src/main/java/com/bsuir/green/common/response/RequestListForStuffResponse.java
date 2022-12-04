package com.bsuir.green.common.response;

import com.bsuir.green.common.model.RequestForStuff;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestListForStuffResponse implements Response {
    List<RequestForStuff> requests;
}
