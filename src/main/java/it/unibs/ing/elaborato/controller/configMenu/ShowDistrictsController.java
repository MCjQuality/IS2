package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.view.DistrictView;

public class ShowDistrictsController implements Executable {

    private final DistrictHandler districts;
    private final DistrictView view;

    public ShowDistrictsController(DistrictHandler districts, DistrictView view) {
        this.districts = districts;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showDistricts(districts);  // Delega la visualizzazione alla View
    }
}