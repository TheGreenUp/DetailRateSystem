package com.bsuir.green.common.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class RequestExtended implements Serializable {
    String detailName, detailType, clientFname, clientLname, requestStatus;
    int id, client_id, detail_id,stuff_id;

    public RequestExtended(String detailName, String detailType, int stuff_id) {
        this.detailName = detailName;
        this.detailType = detailType;
        this.stuff_id = stuff_id;
    }

    //region Конструкторы
    public RequestExtended(int requestId, int client_id, int detail_id, String clientFname, String clientLname, String detailName, String detailType, String requestStatus) {
        this.id = requestId;
        this.client_id = client_id;
        this.detail_id = detail_id;
        this.clientFname = clientFname;
        this.clientLname = clientLname;
        this.detailName = detailName;
        this.detailType = detailType;
        this.requestStatus = requestStatus;
    }
    public RequestExtended(int requestId, String clientFname, String clientLname, String detailName, String detailType, String requestStatus) {
        this.id = requestId;
        this.clientFname = clientFname;
        this.clientLname = clientLname;
        this.detailName = detailName;
        this.detailType = detailType;
        this.requestStatus = requestStatus;
    }
    public RequestExtended(String requestStatus, int id, int client_id, int detail_id, int stuff_id) {
        this.requestStatus = requestStatus;
        this.id = id;
        this.client_id = client_id;
        this.detail_id = detail_id;
        this.stuff_id = stuff_id;
    }
    //endregion


}
