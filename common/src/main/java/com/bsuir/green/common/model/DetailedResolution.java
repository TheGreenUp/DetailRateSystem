package com.bsuir.green.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
public class DetailedResolution implements Serializable {
    String detailType, detailName;
    String stuffFname, stuffLname, stuffEmail;
    String clientFname, clientLname, clientEmail;
    String result;
    Date date;
}
