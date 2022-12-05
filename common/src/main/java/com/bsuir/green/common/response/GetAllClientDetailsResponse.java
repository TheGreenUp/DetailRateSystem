package com.bsuir.green.common.response;

import com.bsuir.green.common.model.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetAllClientDetailsResponse implements Response {
    ArrayList<Detail> details;
}
