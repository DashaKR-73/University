package org.example.error;

public class LoginException
        extends ExceptionWithSubErrorCode {

    public static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    public static final String INCORRECT_PASS = "INCORRECT_PASS";

    public LoginException(String message, String subErrorCode) {
        super(message);
        setSubErrorCode(subErrorCode);
    }

}
