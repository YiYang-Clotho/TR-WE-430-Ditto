package com.ditto.cookiez.utils;

public enum ResponseMsg {

    SUCCEED_TO_DELETE("Succeed to delete"),
    SUCCEED_TO_CREATE("Succeed to create"),
    SUCCEED_TO_UPDATE("Succeed to update"),
    FAILED_TO_DELETE("Failed to delete"),
    FAILED_TO_UPDATE("Failed to update"),
    FAILED_TO_CREATE("Failed to create"),
    SUCCEED_TO_GET("Succeed to query"),
    FAILED_TO_GET("Failed to query"),
    SUCCEED_TO_LOGIN("Succeed to login"),
    ;

    private String value;

    ResponseMsg(String s) {
    this.value=s;
    }
    public String v() {
        return this.value;
    }
}
