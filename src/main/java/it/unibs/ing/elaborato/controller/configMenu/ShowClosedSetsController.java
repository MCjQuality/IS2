package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.view.ClosedSetView;

public class ShowClosedSetsController implements Executable {

    private final ClosedSetHandler closedSets;
    private final ClosedSetView view;

    public ShowClosedSetsController(ClosedSetHandler closedSets, ClosedSetView view) {
        this.closedSets = closedSets;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showClosedSets(closedSets);  // Delego la presentazione alla view
    }
}