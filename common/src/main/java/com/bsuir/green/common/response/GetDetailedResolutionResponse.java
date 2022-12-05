package com.bsuir.green.common.response;

import com.bsuir.green.common.model.DetailedResolution;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetDetailedResolutionResponse implements Response{
    ArrayList<DetailedResolution> resolutions;
}
