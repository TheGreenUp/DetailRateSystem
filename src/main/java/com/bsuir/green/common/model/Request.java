package com.bsuir.green.common.model;

import java.io.Serializable;

public class Request implements Serializable {
    String detailName, detailType, specLName, specFName, requestStatus;
    int id, client_id, detail_id;

    //region Геттеры Сеттеры

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getSpecLName() {
        return specLName;
    }

    public void setSpecLName(String specLName) {
        this.specLName = specLName;
    }

    public String getSpecFName() {
        return specFName;
    }

    public void setSpecFName(String specFName) {
        this.specFName = specFName;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion

    //region Конструкторы
    public Request(String detailName, String detailType, String specLName, String specFName, String requestStatus, int id, int client_id) {
        this.detailName = detailName;
        this.detailType = detailType;
        this.specLName = specLName;
        this.specFName = specFName;
        this.requestStatus = requestStatus;
        this.id = id;
        this.client_id = client_id;
    }

    public Request(String detailName, String detailType, String specLName, String specFName, String requestStatus, int id, int client_id, int detail_id) {
        this.detailName = detailName;
        this.detailType = detailType;
        this.specLName = specLName;
        this.specFName = specFName;
        this.requestStatus = requestStatus;
        this.id = id;
        this.client_id = client_id;
        this.detail_id = detail_id;
    }

    public Request(String detailName, String detailType, String specLName, String specFName, String requestStatus, int id) {
        this.detailName = detailName;
        this.detailType = detailType;
        this.specLName = specLName;
        this.specFName = specFName;
        this.requestStatus = requestStatus;
        this.id = id;
    }

    public Request() {
    }
    //endregion

    @Override
    public String toString() {
        return "Запрос номер № " + id + '\n' +
                "Название детали: " + detailName + '\n' +
                "Тип детали: " + detailType + '\n' +
                "Специалист: " + specLName + " " + specFName + '\n' +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + '\n' +
                "Статус: " + requestStatus + '\n' +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-" + '\n';
    }
}
