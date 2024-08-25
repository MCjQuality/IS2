package conversionElementTest;

import static org.junit.Assert.*;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import org.junit.Before;
import org.junit.Test;

public class ConversionElementTest {

    private ConversionElement conversionElement;
    private Couple couple;
    private LeafCategory leaf1;
    private LeafCategory leaf2;

    @Before
    public void setUp() {
        // Inizializzazione di oggetti di prova
        leaf1 = new LeafCategory("Leaf1", "Domain1", "Description1");
        leaf2 = new LeafCategory("Leaf2", "Domain2", "Description2");
        couple = new Couple(leaf1, leaf2);
        conversionElement = new ConversionElement(couple, 1.5);
    }

    @Test
    public void testConstructor() {
        // Verifica che il costruttore assegni correttamente i valori
        assertEquals(couple, conversionElement.getCouple());
        assertEquals(1.5, conversionElement.getConversionFactor(), 0.001);
    }

    @Test
    public void testGetCouple() {
        // Verifica che il getter di couple funzioni correttamente
        assertEquals(couple, conversionElement.getCouple());
    }

    @Test
    public void testSetCouple() {
        // Crea una nuova coppia e assegna tramite il setter
        LeafCategory newLeaf1 = new LeafCategory("NewLeaf1", "NewDomain1", "NewDescription1");
        LeafCategory newLeaf2 = new LeafCategory("NewLeaf2", "NewDomain2", "NewDescription2");
        Couple newCouple = new Couple(newLeaf1, newLeaf2);

        conversionElement.setCouple(newCouple);
        assertEquals(newCouple, conversionElement.getCouple());
    }

    @Test
    public void testGetConversionFactor() {
        // Verifica che il getter di conversionFactor funzioni correttamente
        assertEquals(1.5, conversionElement.getConversionFactor(), 0.001);
    }

    @Test
    public void testSetConversionFactor() {
        // Modifica il fattore di conversione e verifica l'assegnazione
        conversionElement.setConversionFactor(0.8);
        assertEquals(0.8, conversionElement.getConversionFactor(), 0.001);
    }
}