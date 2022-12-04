package com.bsuir.green.common.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class RequestForStuff implements Serializable {
    String detailName, detailType, clientFname, clientLname, requestStatus;
    int id, client_id, detail_id,stuff_id;

    public RequestForStuff(String detailName, String detailType, int stuff_id) {
        this.detailName = detailName;
        this.detailType = detailType;
        this.stuff_id = stuff_id;
    }

    //region Конструкторы
    public RequestForStuff(int requestId,String clientFname,String clientLname,String detailName, String detailType, String requestStatus) {
        this.id = requestId;
        this.clientFname = clientFname;
        this.clientLname = clientLname;
        this.detailName = detailName;
        this.detailType = detailType;
        this.requestStatus = requestStatus;
    }
    public RequestForStuff(String requestStatus, int id, int client_id, int detail_id, int stuff_id) {
        this.requestStatus = requestStatus;
        this.id = id;
        this.client_id = client_id;
        this.detail_id = detail_id;
        this.stuff_id = stuff_id;
    }
    //endregion


}
