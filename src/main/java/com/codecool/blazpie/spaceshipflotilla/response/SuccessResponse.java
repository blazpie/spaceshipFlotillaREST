package com.codecool.blazpie.spaceshipflotilla.response;

public class SuccessResponse implements Response {

    String status;

    public SuccessResponse(String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
