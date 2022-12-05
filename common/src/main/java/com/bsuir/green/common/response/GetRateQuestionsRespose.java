package com.bsuir.green.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetRateQuestionsRespose implements Response {
    ArrayList<String> questions;
}
