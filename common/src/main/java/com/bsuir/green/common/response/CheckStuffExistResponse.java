package com.bsuir.green.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckStuffExistResponse implements Response{
    boolean isExist;
}
