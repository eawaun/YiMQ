package com.yimq.client.exception;

public class MQClientException extends Exception {
    private int responseCode;
    private String errorMessage;

    public MQClientException(int responseCode, String errorMessage) {
        super("CODE: " + responseCode + "MESSAGE: " + errorMessage);
        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
