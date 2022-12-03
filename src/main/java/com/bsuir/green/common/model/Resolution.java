package com.bsuir.green.common.model;

import java.sql.Date;

public class Resolution {
    int id, request_id, request_detail_id, request_client_id;
    String comment;
    Date date;

    public Resolution(int request_id, int request_detail_id, int request_client_id, String comment, Date date) {
        this.request_id = request_id;
        this.request_detail_id = request_detail_id;
        this.request_client_id = request_client_id;
        this.comment = comment;
        this.date = date;
    }

    public Resolution(int id, int request_id, int request_detail_id, int request_client_id, String comment, Date date) {
        this.id = id;
        this.request_id = request_id;
        this.request_detail_id = request_detail_id;
        this.request_client_id = request_client_id;
        this.comment = comment;
        this.date = date;
    }

    public Resolution(int id, int request_id, int request_detail_id, int request_client_id, String comment) {

        this.id = id;
        this.request_id = request_id;
        this.request_detail_id = request_detail_id;
        this.request_client_id = request_client_id;
        this.comment = comment;
    }

    public Resolution() {
    }

    //region ГеттерыСеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getRequest_detail_id() {
        return request_detail_id;
    }

    public void setRequest_detail_id(int request_detail_id) {
        this.request_detail_id = request_detail_id;
    }

    public int getRequest_client_id() {
        return request_client_id;
    }

    public void setRequest_client_id(int request_client_id) {
        this.request_client_id = request_client_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //endregion
}
