package com.cb.exception;

public class ValuteParsingException extends RuntimeException {
    public ValuteParsingException(Exception ex) {
        super(ex);
    }
}
