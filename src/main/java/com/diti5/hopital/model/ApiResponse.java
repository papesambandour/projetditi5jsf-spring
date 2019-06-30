package com.diti5.hopital.model;

public class ApiResponse<T> {

    private int status;
    private String message;
    private Object data;
    private Object token;

    public ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int status, String message, Object result,Object data) {
        this.status = status;
        this.message = message;
        this.data = result;
        this.token = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object result) {
        this.data = result;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object data) {
        this.token = data;
    }
}
