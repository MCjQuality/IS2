package it.unibs.ing.elaborato.controller;

import it.unibs.ing.elaborato.util.Constants;

public class TerminationController implements Executable {

    @Override
    public void execute() {
        System.out.println(Constants.TERMINATION_MENU_MESSAGE);
    }
}
