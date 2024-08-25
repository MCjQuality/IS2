package closedSetTest;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.closedSet.ClosedSetRepository;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ClosedSetTest {

    private ClosedSetHandler handler;

    @Mock
    private ClosedSetRepository repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        handler = new ClosedSetHandler(repo);
    }

    @Test
    public void testAdd() {
        // Crea una proposta di scambio da aggiungere
        ExchangeProposals closedSet = new ExchangeProposals();

        // Chiama il metodo add e verifica che il metodo add del repo sia stato chiamato con l'argomento corretto
        handler.add(closedSet);
        verify(repo).add(closedSet);
    }

    @Test
    public void testGetClosedSets() {
        // Crea una lista di proposte di scambio chiuse
        List<ExchangeProposals> closedSets = Arrays.asList(new ExchangeProposals(), new ExchangeProposals());

        // Configura il mock per restituire questa lista quando getClosedSets() è chiamato
        when(repo.getClosedSets()).thenReturn(closedSets);

        // Chiama il metodo getClosedSets e verifica il risultato
        List<ExchangeProposals> result = handler.getClosedSets();
        assertEquals(closedSets, result);

        // Verifica che il metodo getClosedSets del repo sia stato chiamato
        verify(repo).getClosedSets();
    }

    @Test
    public void testWrite() throws FileWriterException {
        // Chiama il metodo write del handler
        handler.write();

        // Verifica che il metodo write del repo sia stato chiamato
        verify(repo).write();
    }

    @Test(expected = FileWriterException.class)
    public void testWriteThrowsException() throws FileWriterException {
        // Configura il mock per lanciare una FileWriterException quando write() è chiamato
        doThrow(new FileWriterException()).when(repo).write();

        // Chiama il metodo write e verifica che l'eccezione sia propagata
        handler.write();
    }
}