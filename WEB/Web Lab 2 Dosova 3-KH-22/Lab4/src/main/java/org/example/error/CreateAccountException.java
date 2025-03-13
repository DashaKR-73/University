package org.example.error;

public class CreateAccountException
        extends ExceptionWithSubErrorCode {

    public static final String EMPTY_LOGIN = "EMPTY_LOGIN";
    public static final String TOO_SHORT_PASS = "TOO_SHORT_PASS";
    public static final String LOGIN_IS_ALREADY = "LOGIN_IS_ALREADY";

    public CreateAccountException(String message, String subCode) {
        super(message);
        setSubErrorCode(subCode);
    }

}
