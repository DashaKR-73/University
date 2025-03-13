package org.example.error;

public class ExceptionWithSubErrorCode
        extends Exception {

    private String subErrorCode;

    public ExceptionWithSubErrorCode() {
    }

    public ExceptionWithSubErrorCode(String message) {
        super(message);
    }

    public ExceptionWithSubErrorCode(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionWithSubErrorCode(Throwable cause) {
        super(cause);
    }

    public ExceptionWithSubErrorCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getSubErrorCode() {
        return subErrorCode;
    }

    public ExceptionWithSubErrorCode setSubErrorCode(String subErrorCode) {
        this.subErrorCode = subErrorCode;
        return this;
    }

}
