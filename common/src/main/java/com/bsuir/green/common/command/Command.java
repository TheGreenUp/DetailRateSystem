package com.bsuir.green.common.command;

import java.io.Serializable;

public enum Command implements Serializable {
    LOGIN,
    REGISTER,
    REQUEST_LIST,
    STUFF_LIST,
    DELETE_STUFF,
    REQUEST_LIST_FOR_STUFF,
    ADD_STUFF,
    UPDATE_USER,
    CREATE_REQUEST,
}
