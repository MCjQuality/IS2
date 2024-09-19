package it.unibs.ing.elaborato.view;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;

import java.util.Scanner;

public class ClosedSetView {

    private final Scanner scanner;

    public ClosedSetView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showClosedSets(ClosedSetHandler closedSets) {
        System.out.println();
        System.out.println(Printer.align(Constants.SHOW_CLOSED_SETS, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (!closedSets.getClosedSets().isEmpty()) {
            System.out.print(Printer.printClosedSets(closedSets.getClosedSets()));
        } else {
            System.out.println(Constants.NO_CLOSED_SETS);
            System.out.println();
        }

        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();  // Attende l'input per il ritorno al menu
    }
}