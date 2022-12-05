package com.bsuir.green.common.command;

import java.io.Serializable;

public enum Command implements Serializable {
    LOGIN,
    REGISTER,
    REQUEST_LIST,
    STUFF_LIST,
    DELETE_STUFF,
    REQUEST_LIST_FOR_STUFF,
    QUESTION_LIST,
    ADD_STUFF,
    UPDATE_USER,
    CREATE_RESOLUTION,
    GET_CLIENT_ID_BY_REQUEST,
    UPDATE_REQUEST_STATUS,
    CREATE_DETAIL,
    GET_DETAIL,
    CREATE_REQUEST,
}
