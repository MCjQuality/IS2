package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertNewDistrictController implements Executable {

    private final DistrictHandler service;
    private final Scanner scanner;

    public InsertNewDistrictController(DistrictHandler service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void insertNewDistrict() throws FileWriterException {
        displayInsertDistrictMessage();

        boolean fail;

        do {
            fail = false;
            String name = Utility.checkCondition(
                    Constants.INSERT_DISTRICT_NAME_MESSAGE,
                    Constants.INVALID_INPUT_MESSAGE,
                    String::isBlank,
                    scanner);
            if(service.isDistrictExists(name)) {
                fail = true;
                System.out.println(Constants.DISTRICT_ALREADY_INSERTED);
            }

            List<String> municipalities = new ArrayList<>();
            addMunicipalities(municipalities);
            saveDistrictChanges(name, municipalities);
        } while(fail);
    }

    private void displayInsertDistrictMessage() {
        System.out.println();
        System.out.println(Printer.align(Constants.INSERT_NEW_DISTRICT_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();
    }

    private void addMunicipalities(List<String> municipalities) {
        System.out.println(Constants.INSERT_MUNICIPALITY_BELONG_DISTRICT_MESSAGE);
        String candidate;

        do {
            System.out.print(Constants.SEPARATOR);
            candidate = scanner.nextLine();
            processMunicipalityInput(candidate, municipalities);
        } while (!candidate.equalsIgnoreCase(Constants.Q_MESSAGE) || municipalities.isEmpty());
    }

    private void processMunicipalityInput(String candidate, List<String> municipalities) {
        if (candidate.equalsIgnoreCase(Constants.Q_MESSAGE)) {
            System.out.println();
        } else if (isMunicipalityValid(candidate, municipalities)) {
            municipalities.add(candidate);
        } else {
            displayMunicipalityError(candidate, municipalities);
        }
    }

    private boolean isMunicipalityValid(String municipality, List<String> municipalities) {
        return Utility.isPresent(Constants.ITALIAN_MUNICIPALITIES_FILEPATH, municipality) &&
                !service.isMunicipalityPresent(municipality) &&
                !municipalities.contains(municipality);
    }

    private void displayMunicipalityError(String municipality, List<String> municipalities) {
        if (service.isMunicipalityPresent(municipality) || municipalities.contains(municipality)) {
            System.out.println(Constants.DUPLICATED_MUNICIPALITY_MESSAGE);
        } else {
            System.out.println(Constants.MUNICIPALITY_NONEXISTENT);
        }
    }

    private void saveDistrictChanges(String district, List<String> municipalities) throws FileWriterException {
        System.out.print(Constants.SAVE_THE_CHANGES_MESSAGE);
        boolean fail;
        do {
            fail = false;
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase(Constants.YES_MESSAGE)) {
                service.addDistrict(district, municipalities);
                service.save();
                System.out.println(Constants.DISTRICT_VALID_MESSAGE);
            } else if (response.equalsIgnoreCase(Constants.NO_MESSAGE)) {
                System.out.println(Constants.DISTRICT_NOT_SAVED_MESSAGE);
            } else {
                System.out.print(Constants.INVALID_INPUT_MESSAGE);
                fail = true;
            }
        } while(fail);
    }

    @Override
    public void execute() throws FileWriterException {
        insertNewDistrict();
    }
}