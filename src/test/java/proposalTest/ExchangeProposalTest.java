package proposalTest;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.State;
import it.unibs.ing.elaborato.model.user.Consumer;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ExchangeProposalTest {

    private Couple couple = new Couple(new LeafCategory("leaf1", "domain1", ""), new LeafCategory("leaf2", "domain2", ""));
    private ConversionElement conversionElement = new ConversionElement(couple, 1.2);
    private Consumer consumer = new Consumer("Consumer1", "psw1", "District1", "Email1");

    private ExchangeProposal proposal;

    @Before
    public void setUp() {
        // Crea una lista di ConversionElement con un solo elemento mock
        List<ConversionElement> conversionElements = new ArrayList<>();
        conversionElements.add(conversionElement);

        // Crea una nuova istanza di ExchangeProposal
        proposal = new ExchangeProposal(couple, 10, consumer, conversionElements);
    }

    @Test
    public void testConstructor() {
        // Verifica che i campi siano impostati correttamente dal costruttore
        assertEquals(couple, proposal.getCouple());
        assertEquals(10, proposal.getHoursRequest());
        assertEquals(12, proposal.getHoursOffered()); // 10 * 1.2 = 12
        assertEquals(consumer, proposal.getOwner());
        assertEquals(State.OPEN, proposal.getState());
    }

    @Test
    public void testAddTransition() {
        // Verifica che lo stato corrente venga aggiunto alla lista degli stati passati
        proposal.addTransition(State.CLOSED);
        assertTrue(proposal.getPastStates().contains(State.CLOSED));
    }

    @Test
    public void testChangeStatusToWithdrawn() {
        // Verifica che lo stato corrente venga cambiato e aggiunto alla lista degli stati passati
        proposal.changeStatusToWithdrawn();
        assertEquals(State.WITHDRAWN, proposal.getState());
        assertTrue(proposal.getPastStates().contains(State.OPEN));
    }

    @Test
    public void testChangeStatusToClosed() {
        // Verifica che lo stato corrente venga cambiato e aggiunto alla lista degli stati passati
        proposal.changeStatusToClosed();
        assertEquals(State.CLOSED, proposal.getState());
        assertTrue(proposal.getPastStates().contains(State.OPEN));
    }

    @Test
    public void testEquals() {
        // Verifica che due istanze di ExchangeProposal con gli stessi attributi siano considerate uguali
        ExchangeProposal sameProposal = new ExchangeProposal(couple, 10, consumer, List.of(conversionElement));
        assertEquals(proposal, sameProposal);

        // Verifica che due istanze di ExchangeProposal con attributi diversi non siano considerate uguali
        ExchangeProposal differentProposal = new ExchangeProposal(couple, 20, consumer, List.of(conversionElement));
        assertNotEquals(proposal, differentProposal);
    }

    @Test
    public void testHashCode() {
        // Verifica che due istanze di ExchangeProposal con gli stessi attributi abbiano lo stesso hashCode
        ExchangeProposal sameProposal = new ExchangeProposal(couple, 10, consumer, List.of(conversionElement));
        assertEquals(proposal.hashCode(), sameProposal.hashCode());

        // Verifica che due istanze di ExchangeProposal con attributi diversi abbiano hashCode diversi
        ExchangeProposal differentProposal = new ExchangeProposal(couple, 20, consumer, List.of(conversionElement));
        assertNotEquals(proposal.hashCode(), differentProposal.hashCode());
    }
}