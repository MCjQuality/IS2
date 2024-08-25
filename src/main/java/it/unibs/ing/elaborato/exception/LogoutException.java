package it.unibs.ing.elaborato.exception;

import it.unibs.ing.elaborato.util.Constants;

public class LogoutException extends Exception
{
    @Override
    public String getMessage()
    {
        return Constants.LOGOUT_MESSAGE;
    }
}
