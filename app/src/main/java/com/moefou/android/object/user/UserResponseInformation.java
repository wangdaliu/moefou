package com.moefou.android.object.user;

public class UserResponseInformation {

    private UserParameters parameters;

    private String[] msg;

    private boolean has_error;

    private int error;

    private String request;

    public UserParameters getParameters() {
        return parameters;
    }

    public void setParameters(UserParameters parameters) {
        this.parameters = parameters;
    }

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public boolean isHas_error() {
        return has_error;
    }

    public void setHas_error(boolean has_error) {
        this.has_error = has_error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
