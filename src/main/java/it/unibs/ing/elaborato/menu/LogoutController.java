package it.unibs.ing.elaborato.menu;

import it.unibs.ing.elaborato.exception.LogoutException;

public class LogoutController implements Executable {

    @Override
    public void execute() throws LogoutException {
        throw new LogoutException();
    }
}
