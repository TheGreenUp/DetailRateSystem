package com.bsuir.green.common.response.getResponse;

import com.bsuir.green.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetRateQuestionsRespose implements Response {
    ArrayList<String> questions;
}
