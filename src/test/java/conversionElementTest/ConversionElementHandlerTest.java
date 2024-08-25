package conversionElementTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementRepository;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElements;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ConversionElementHandlerTest {

    @Mock
    private ConversionElementRepository repo;

    private ConversionElementHandler handler;
    private ConversionElements conversionElements;

    private LeafCategory leaf1;
    private LeafCategory leaf2;
    private Couple couple;
    private ConversionElement conversionElement;

    @Before
    public void setUp() throws FileReaderException {
        MockitoAnnotations.initMocks(this);

        // Setup delle foglie e coppie di test
        leaf1 = new LeafCategory("Leaf1", "Domain1", "Description1");
        leaf2 = new LeafCategory("Leaf2", "Domain2", "Description2");
        couple = new Couple(leaf1, leaf2);

        conversionElement = new ConversionElement(couple, 1.5);
        conversionElements = new ConversionElements();
        conversionElements.addFactConv(conversionElement);

        // Configura il mock del repository
        when(repo.read()).thenReturn(conversionElements);

        // Inizializza l'handler con il mock repository
        handler = new ConversionElementHandler(repo);
    }

    @Test
    public void testSize() {
        // Verifica che la dimensione della lista sia corretta
        assertEquals(1, handler.size());
    }

    @Test
    public void testIsFactConvPresent() {
        // Verifica che il fattore di conversione sia presente
        assertTrue(handler.isFactConvPresent(1.5));
        assertFalse(handler.isFactConvPresent(0.5));
    }

    @Test
    public void testReplace() {
        // Verifica che il fattore di conversione sia sostituito correttamente
        handler.replace(couple, 1.0);
        assertEquals(1.0, conversionElement.getConversionFactor(), 0.001);
    }

    @Test
    public void testGetRemainingConversionElements() {
        // Verifica che gli elementi con conversionFactor = 0 siano restituiti correttamente
        ConversionElement zeroElement = new ConversionElement(new Couple(leaf2, leaf1), 0.0);
        conversionElements.addFactConv(zeroElement);

        List<ConversionElement> remaining = handler.getRemainingConversionElements();
        assertEquals(1, remaining.size());
        assertEquals(zeroElement, remaining.getFirst());
    }

    @Test
    public void testGetConversionFactorRange() {
        // Verifica che il range del fattore di conversione sia calcolato correttamente
        double[] range = handler.getConversionFactorRange(couple);
        assertEquals(0.5, range[0], 0.001);
        assertEquals(2.0, range[1], 0.001);
    }

    @Test
    public void testAutomaticConvFactCalculate() {
        // Verifica il calcolo automatico dei fattori di conversione
        ConversionElement zeroElement = new ConversionElement(new Couple(leaf2, leaf1), 0.0);
        conversionElements.addFactConv(zeroElement);

        handler.automaticConvFactCalculate();

        assertNotEquals(0.66, zeroElement.getConversionFactor(), 0.001);
    }

    @Test
    public void testInitialize() {
        // Verifica l'inizializzazione della lista di conversioni
        List<LeafCategory> leaves = new ArrayList<>();
        leaves.add(leaf1);
        leaves.add(leaf2);

        handler.initialize(leaves);
        assertEquals(2, handler.size());
    }

    @Test
    public void testSave() throws FileWriterException {
        // Verifica che la funzione save invochi correttamente il metodo write del repository
        handler.save();
        verify(repo).write(conversionElements);
    }
}