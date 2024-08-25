package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;

import java.util.Scanner;

public class ShowDistrictsController implements Executable {
    private final DistrictHandler districts;
    private final Scanner scanner;

    public ShowDistrictsController(DistrictHandler districts, Scanner scanner) {
        this.districts = districts;
        this.scanner = scanner;
    }

    private void showDistricts() {
        System.out.println();
        System.out.println(Printer.align(Constants.VIEW_ALL_DISTRICTS_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (districts.getDistricts().isEmpty()) {
            System.out.println(Constants.NO_DISTRICTS);
            System.out.println();
            System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
            scanner.nextLine();
            return;
        }

        System.out.print(Printer.columnize(Printer.printDistricts(districts.getDistricts()), Constants.MENU_LINE_SIZE));
        System.out.println();
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }

    @Override
    public void execute()
    {
        showDistricts();
    }
}