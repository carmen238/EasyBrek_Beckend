package com.tesi.Entity;

public class Response {
    String error;
    String response;
    Object data;
    int params;

    public Response(String error, String response) {
        this.error = error;
        this.response = response;
    }
    public Response(String response) {
        this.response = response;
    }
    public Response(Object data, int params) {
        this.data = data;
        this.params = params;
    }

    public Response(Object data) {
        this.data = data;
    }


    public int getParams() {
        return params;
    }

    public void setParams(int params) {
        this.params = params;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
