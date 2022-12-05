package com.bsuir.green.common.model;

import lombok.Getter;

import java.io.Serializable;
@Getter
public class Request implements Serializable {
    String requestStatus;
    int id, client_id, detail_id,stuff_id;

    public Request(String requestStatus, int id, int client_id, int detail_id, int stuff_id) {
        this.requestStatus = requestStatus;
        this.id = id;
        this.client_id = client_id;
        this.detail_id = detail_id;
        this.stuff_id = stuff_id;
    }
    public Request(int client_id, int detail_id, int stuff_id) {
        this.requestStatus = "В процессе";
        this.client_id = client_id;
        this.detail_id = detail_id;
        this.stuff_id = stuff_id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestStatus='" + requestStatus + '\'' +
                ", id=" + id +
                ", client_id=" + client_id +
                ", detail_id=" + detail_id +
                ", stuff_id=" + stuff_id +
                '}';
    }
}
