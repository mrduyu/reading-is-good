package com.getir.readingisgood.readingisgood.authenticationservice.responses;

public class BaseResponse {
    private String returnCode;
    private String errorMessage;

    public BaseResponse(String returnCode, String errorMessage) {
        this.returnCode = returnCode;
        this.errorMessage = errorMessage;
    }
    public BaseResponse() {
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
