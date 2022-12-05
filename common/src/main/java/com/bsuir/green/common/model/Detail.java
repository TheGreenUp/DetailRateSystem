package com.bsuir.green.common.model;

import java.io.Serializable;

public class Detail implements Serializable {
    String detailType,detailName;
    int id;

    //region ГеттерыСеттеры
    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }
    //endregion

    public Detail(String detailType, String detailName) {
        this.detailName = detailName;
        this.detailType = detailType;
    }
    public Detail(String detailType, String detailName, int id) {
        this.detailType = detailType;
        this.detailName = detailName;
        this.id = id;
    }
    public Detail() {
    }
}
