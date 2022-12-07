package com.bsuir.green.common.response.getResponse;

import com.bsuir.green.common.model.Detail;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class GetDetailByResolutionResponse implements Response {
    ArrayList<Detail> detail;
}
