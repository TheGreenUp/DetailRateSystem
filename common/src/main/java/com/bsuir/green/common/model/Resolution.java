package com.bsuir.green.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@AllArgsConstructor
@Data
public class Resolution implements Serializable {
    int id, request_id, request_detail_id, request_client_id;
    String comment;
    Date date;

    public Resolution(int request_id, int request_detail_id, int request_client_id, String comment) {
        this.request_id = request_id;
        this.request_detail_id = request_detail_id;
        this.request_client_id = request_client_id;
        this.comment = comment;
        this.date = getCurrentDate();
    }

    public Resolution(int id, int request_id, int request_detail_id, int request_client_id, String comment) {
        this.id = id;
        this.request_id = request_id;
        this.request_detail_id = request_detail_id;
        this.request_client_id = request_client_id;
        this.comment = comment;
        this.date = getCurrentDate();
    }


    public Resolution() {
    }

    private Date getCurrentDate(){
        Date date = Date.valueOf(LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return  date;
    }
}
