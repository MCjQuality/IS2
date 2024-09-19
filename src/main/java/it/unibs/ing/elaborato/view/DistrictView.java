package it.unibs.ing.elaborato.view;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import java.util.Scanner;

public class DistrictView {

    private final Scanner scanner;

    public DistrictView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getDistrictName() {
        System.out.println();
        System.out.println(Printer.align(Constants.INSERT_NEW_DISTRICT_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();
        System.out.print(Constants.INSERT_DISTRICT_NAME_MESSAGE);
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getMunicipalityInput() {
        System.out.print(Constants.SEPARATOR);
        return scanner.nextLine().trim();
    }

    public String confirmSaveChanges() {
        System.out.print(Constants.SAVE_THE_CHANGES_MESSAGE);
        return scanner.nextLine().trim();
    }

    public void showDistricts(DistrictHandler districts) {
        System.out.println();
        System.out.println(Printer.align(Constants.VIEW_ALL_DISTRICTS_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (districts.getDistricts().isEmpty()) {
            System.out.println(Constants.NO_DISTRICTS);
            System.out.println();
            waitForUser();
            return;
        }

        System.out.print(Printer.columnize(Printer.printDistricts(districts.getDistricts()), Constants.MENU_LINE_SIZE));
        System.out.println();
        waitForUser();
    }

    private void waitForUser() {
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }
}