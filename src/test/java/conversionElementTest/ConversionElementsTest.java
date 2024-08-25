package conversionElementTest;

import static org.junit.Assert.*;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElements;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConversionElementsTest {

    private ConversionElements conversionElements;
    private LeafCategory leaf1;
    private LeafCategory leaf2;
    private LeafCategory leaf3;
    private Couple couple1;
    private Couple couple2;
    private Couple couple3;
    private Couple couple4;
    private ConversionElement conversionElement1;
    private ConversionElement conversionElement2;


    @Before
    public void setUp() {
        // Inizializzazione degli oggetti di prova
        conversionElements = new ConversionElements();

        leaf1 = new LeafCategory("Leaf1", "Domain1", "Description1");
        leaf2 = new LeafCategory("Leaf2", "Domain2", "Description2");
        leaf3 = new LeafCategory("Leaf3", "Domain3", "Description3");

        couple1 = new Couple(leaf1, leaf2);
        couple2 = new Couple(leaf2, leaf3);
        couple3 = new Couple(leaf3, leaf1);
        couple4 = new Couple(leaf2, leaf1);

        conversionElement1 = new ConversionElement(couple1, 1.);
        conversionElement2 = new ConversionElement(couple2, 0.);

        conversionElements.addFactConv(conversionElement1);
    }

    @Test
    public void testAddFactConv() {
        // Verifica che l'elemento di conversione sia aggiunto correttamente
        assertEquals(1, conversionElements.size());
        conversionElements.addFactConv(conversionElement2);
        assertEquals(2, conversionElements.size());
    }

    @Test
    public void testIsCouplePresent() {
        // Verifica che la coppia sia presente nella lista
        assertTrue(conversionElements.isCouplePresent(couple1));
        assertFalse(conversionElements.isCouplePresent(couple2));
    }

    @Test
    public void testIsFactConvPresent() {
        // Verifica che il fattore di conversione sia presente nella lista
        assertTrue(conversionElements.isFactConvPresent(1.));
        assertFalse(conversionElements.isFactConvPresent(2.));
    }

    @Test
    public void testReplace() {
        // Verifica che il fattore di conversione venga correttamente sostituito
        conversionElements.replace(couple1, 1.5);
        assertEquals(1.5, conversionElement1.getConversionFactor(), 0.001);
    }

    @Test
    public void testGetRemainingConversionElements() {
        // Verifica che gli elementi con conversionFactor = 0 siano restituiti correttamente
        ConversionElement conversionElement3 = new ConversionElement(couple3, 0.);
        conversionElements.addFactConv(conversionElement3);

        List<ConversionElement> remaining = conversionElements.getRemainingConversionElements();
        assertEquals(1, remaining.size());
        assertEquals(conversionElement3, remaining.getFirst());
    }

    @Test
    public void testGetConversionFactorRange() {
        // Verifica il calcolo del range del fattore di conversione
        conversionElements.getConversionElements().clear();
        ConversionElement conversionElement3 = new ConversionElement(couple1, 1.5);
        ConversionElement conversionElement4 = new ConversionElement(couple4, 0.66);

        conversionElements.addFactConv(conversionElement3);
        conversionElements.addFactConv(conversionElement4);

        double[] range = conversionElements.getConversionFactorRange(couple2);
        assertEquals(0.5, range[0], 0.001);
        assertEquals(1.33, range[1], 0.001);
    }

    @Test
    public void testGetCompatibleElements() {
        // Verifica che vengano restituiti gli elementi compatibili
        conversionElements.addFactConv(conversionElement2);
        List<ConversionElement> compatibles = conversionElements.getConversionElementsCompatible(couple2);

        assertEquals(1, compatibles.size());
        assertEquals(conversionElement1, compatibles.getFirst());
    }

    @Test
    public void testAutomaticConvFactCalculate() {
        // Testare il metodo di calcolo automatico
        ConversionElement elem = new ConversionElement(new Couple(leaf1, leaf3), 0.0);
        conversionElements.addFactConv(elem);

        conversionElements.automaticConvFactCalculate();

        assertNotEquals(2.0, elem.getConversionFactor(), 0.001);
    }

    @Test
    public void testInitialize() {
        // Verifica l'inizializzazione con una lista di foglie
        List<LeafCategory> leaves = new ArrayList<>();
        leaves.add(leaf1);
        leaves.add(leaf2);
        leaves.add(leaf3);

        conversionElements.initialize(leaves);
        assertEquals(6, conversionElements.size());
    }
}