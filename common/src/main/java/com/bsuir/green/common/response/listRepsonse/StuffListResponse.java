package com.bsuir.green.common.response.listRepsonse;


import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class StuffListResponse implements Response {
    ArrayList<Stuff> stuff;
}
