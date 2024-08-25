package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;

import java.util.Scanner;

public class ShowClosedSetsController implements Executable {

    private final ClosedSetHandler closedSets;
    private final Scanner scanner;

    public ShowClosedSetsController(ClosedSetHandler closedSets, Scanner scanner) {
        this.closedSets = closedSets;
        this.scanner = scanner;
    }

    private void showClosedSets(ClosedSetHandler closedSets)
    {
        System.out.println();
        System.out.println(Printer.align(Constants.SHOW_CLOSED_SETS, Constants.MENU_LINE_SIZE));
        System.out.println();

        if(!closedSets.getClosedSets().isEmpty())
            System.out.print(Printer.printClosedSets(closedSets.getClosedSets()));
        else
        {
            System.out.println(Constants.NO_CLOSED_SETS);
            System.out.println();
        }

        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }

    @Override
    public void execute() {
        showClosedSets(closedSets);
    }
}
