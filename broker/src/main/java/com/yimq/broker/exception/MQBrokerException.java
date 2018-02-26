package com.yimq.broker.exception;

public class MQBrokerException extends Exception {
    private int responseCode;
    private String errorMessage;

    public MQBrokerException(String message) {
        super(message);
    }

    public MQBrokerException(int responseCode, String errorMessage) {
        super("CODE: " + responseCode + ",MESSAGE: " + errorMessage);
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
