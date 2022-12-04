package com.bsuir.green.common.response;


import com.bsuir.green.common.model.Stuff;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StuffListResponse implements Response {
    List<Stuff> stuff;
}
