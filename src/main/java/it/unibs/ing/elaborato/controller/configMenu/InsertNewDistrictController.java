package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Utility;
import it.unibs.ing.elaborato.view.DistrictView;

import java.util.ArrayList;
import java.util.List;

public class InsertNewDistrictController implements Executable {

    private final DistrictHandler service;
    private final DistrictView view;

    public InsertNewDistrictController(DistrictHandler service, DistrictView view) {
        this.service = service;
        this.view = view;
    }

    public void insertNewDistrict() throws FileWriterException {
        boolean fail;
        do {
            fail = false;
            String name = view.getDistrictName();
            if (service.isDistrictExists(name)) {
                fail = true;
                view.showMessage(Constants.DISTRICT_ALREADY_INSERTED);
            }

            List<String> municipalities = new ArrayList<>();
            addMunicipalities(municipalities);
            saveDistrictChanges(name, municipalities);
        } while(fail);
    }

    private void addMunicipalities(List<String> municipalities) {
        view.showMessage(Constants.INSERT_MUNICIPALITY_BELONG_DISTRICT_MESSAGE);
        String candidate;

        do {
            candidate = view.getMunicipalityInput();
            if (candidate.equalsIgnoreCase(Constants.Q_MESSAGE) && !municipalities.isEmpty()) {
                break;
            }
            processMunicipalityInput(candidate, municipalities);
        } while (!candidate.equalsIgnoreCase(Constants.Q_MESSAGE));
    }

    private void processMunicipalityInput(String candidate, List<String> municipalities) {
        if (candidate.equalsIgnoreCase(Constants.Q_MESSAGE)) {
            return;
        } else if (isMunicipalityValid(candidate, municipalities)) {
            municipalities.add(candidate);
        } else {
            if (service.isMunicipalityPresent(candidate) || municipalities.contains(candidate)) {
                view.showMessage(Constants.DUPLICATED_MUNICIPALITY_MESSAGE);
            } else {
                view.showMessage(Constants.MUNICIPALITY_NONEXISTENT);
            }
        }
    }

    private boolean isMunicipalityValid(String municipality, List<String> municipalities) {
        return Utility.isPresent(Constants.ITALIAN_MUNICIPALITIES_FILEPATH, municipality) &&
                !service.isMunicipalityPresent(municipality) &&
                !municipalities.contains(municipality);
    }

    private void saveDistrictChanges(String district, List<String> municipalities) throws FileWriterException {
        boolean fail;
        do {
            fail = false;
            String response = view.confirmSaveChanges();
            if (response.equalsIgnoreCase(Constants.YES_MESSAGE)) {
                service.addDistrict(district, municipalities);
                service.save();
                view.showMessage(Constants.DISTRICT_VALID_MESSAGE);
            } else if (response.equalsIgnoreCase(Constants.NO_MESSAGE)) {
                view.showMessage(Constants.DISTRICT_NOT_SAVED_MESSAGE);
            } else {
                view.showMessage(Constants.INVALID_INPUT_MESSAGE);
                fail = true;
            }
        } while(fail);
    }

    @Override
    public void execute() throws FileWriterException {
        insertNewDistrict();
    }
}