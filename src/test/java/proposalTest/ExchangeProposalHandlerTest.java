package proposalTest;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalRepository;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ExchangeProposalHandlerTest {

    @Mock
    private ExchangeProposalRepository mockRepo;

    @Mock
    private ExchangeProposals mockProposals;

    @Mock
    private ExchangeProposal mockProposal;

    @Mock
    private Consumer mockConsumer;

    private ExchangeProposalHandler handler;

    @Before
    public void setUp() throws FileReaderException {
        MockitoAnnotations.initMocks(this);
        when(mockRepo.read()).thenReturn(mockProposals);

        // Crea una nuova istanza di ExchangeProposalHandler
        handler = new ExchangeProposalHandler(mockRepo);
    }

    @Test
    public void testGetExchangeProposals() {
        // Prepara i dati di test
        List<ExchangeProposal> expectedProposals = new ArrayList<>();
        expectedProposals.add(mockProposal);
        when(mockProposals.getExchangeProposals()).thenReturn(expectedProposals);

        // Esegui il test
        List<ExchangeProposal> actualProposals = handler.getExchangeProposals();
        assertEquals(expectedProposals, actualProposals);
    }

    @Test
    public void testAdd() {
        // Esegui l'operazione
        handler.add(mockProposal);

        // Verifica che l'aggiunta sia stata effettuata
        verify(mockProposals).add(mockProposal);
    }

    @Test
    public void testActiveProposalBelongOneConsumer() {
        // Prepara i dati di test
        List<ExchangeProposal> expectedProposals = new ArrayList<>();
        expectedProposals.add(mockProposal);
        when(mockProposals.activeProposalBelongOneConsumer(mockConsumer)).thenReturn(expectedProposals);

        // Esegui il test
        List<ExchangeProposal> actualProposals = handler.activeProposalBelongOneConsumer(mockConsumer);
        assertEquals(expectedProposals, actualProposals);
    }

    @Test
    public void testClosedProposalBelongOneConsumer() {
        // Prepara i dati di test
        List<ExchangeProposal> expectedProposals = new ArrayList<>();
        expectedProposals.add(mockProposal);
        when(mockProposals.closedProposalBelongOneConsumer(mockConsumer)).thenReturn(expectedProposals);

        // Esegui il test
        List<ExchangeProposal> actualProposals = handler.closedProposalBelongOneConsumer(mockConsumer);
        assertEquals(expectedProposals, actualProposals);
    }

    @Test
    public void testWithdrawnProposalBelongOneConsumer() {
        // Prepara i dati di test
        List<ExchangeProposal> expectedProposals = new ArrayList<>();
        expectedProposals.add(mockProposal);
        when(mockProposals.withdrawnProposalBelongOneConsumer(mockConsumer)).thenReturn(expectedProposals);

        // Esegui il test
        List<ExchangeProposal> actualProposals = handler.withdrawnProposalBelongOneConsumer(mockConsumer);
        assertEquals(expectedProposals, actualProposals);
    }

    @Test
    public void testContains() {
        when(mockProposals.contains(mockProposal)).thenReturn(true);

        // Esegui il test
        boolean result = handler.contains(mockProposal);
        assertTrue(result);
    }

    @Test
    public void testVerifyClosedProposals() {
        // Prepara i dati di test
        ExchangeProposals closedSet = mock(ExchangeProposals.class);
        when(mockProposals.verifyClosedProposals(mockProposal, mockProposal, closedSet)).thenReturn(true);

        // Esegui il test
        boolean result = handler.verifyClosedProposals(mockProposal, mockProposal, closedSet);
        assertTrue(result);
    }

    @Test
    public void testSave() throws FileWriterException {
        // Esegui l'operazione di salvataggio
        handler.save();

        // Verifica che il salvataggio sia stato effettuato
        verify(mockRepo).write(mockProposals);
    }
}