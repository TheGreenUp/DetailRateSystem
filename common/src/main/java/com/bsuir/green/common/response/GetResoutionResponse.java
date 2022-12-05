package com.bsuir.green.common.response;

import com.bsuir.green.common.model.Resolution;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetResoutionResponse implements  Response{
    ArrayList<Resolution> resolutions;
}
