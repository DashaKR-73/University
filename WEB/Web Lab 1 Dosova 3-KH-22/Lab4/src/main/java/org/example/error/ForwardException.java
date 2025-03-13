package org.example.error;

public class ForwardException
        extends Exception {

    private Class<? extends Throwable> errorClass;
    private String subErrorCode;

    public ForwardException(Throwable cause, String subErrorCode) {
        this.errorClass = cause.getClass();
        this.subErrorCode = subErrorCode;
        setStackTrace(new StackTraceElement[0]);
    }

    public Class<? extends Throwable> getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(Class<? extends Throwable> errorClass) {
        this.errorClass = errorClass;
    }

    public String getSubErrorCode() {
        return subErrorCode;
    }

    public void setSubErrorCode(String subErrorCode) {
        this.subErrorCode = subErrorCode;
    }

}
