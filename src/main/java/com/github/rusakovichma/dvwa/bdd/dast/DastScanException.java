package com.github.rusakovichma.dvwa.bdd.dast;

public class DastScanException extends Exception{

    public DastScanException() {
    }

    public DastScanException(String message) {
        super(message);
    }

    public DastScanException(String message, Throwable cause) {
        super(message, cause);
    }

    public DastScanException(Throwable cause) {
        super(cause);
    }

    public DastScanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
