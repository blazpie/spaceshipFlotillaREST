package com.codecool.blazpie.spaceshipflotilla.response;

public class ErrorResponse implements Response {

    String status;
    String code;
    String message;


    public ErrorResponse(String error, String message) {
        this.code = error;
        this.message = message;
        setStatus("code");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
