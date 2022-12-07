package com.bsuir.green.common.response.getResponse;

import com.bsuir.green.common.model.DetailedResolution;
import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetClientDetailedResResponse implements Response {
    ArrayList<DetailedResolution> resolutions;
}