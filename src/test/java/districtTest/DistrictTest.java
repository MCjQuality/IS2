package districtTest;

import it.unibs.ing.elaborato.model.district.District;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DistrictTest {

    private District district;

    @Before
    public void setUp() {
        // Inizializza un'istanza di District prima di ogni test
        district = new District("TestDistrict");
    }

    @Test
    public void testConstructor() {
        // Verifica che il nome sia impostato correttamente
        assertEquals("TestDistrict", district.getName());

        // Verifica che la lista dei comuni sia inizializzata vuota
        assertNotNull(district.getMunicipalities());
        assertTrue(district.getMunicipalities().isEmpty());
    }

    @Test
    public void testGetName() {
        // Verifica che il metodo getName() restituisca il nome corretto
        assertEquals("TestDistrict", district.getName());
    }

    @Test
    public void testSetName() {
        // Modifica il nome del distretto e verifica che il cambiamento sia stato effettuato
        district.setName("NewDistrictName");
        assertEquals("NewDistrictName", district.getName());
    }

    @Test
    public void testGetMunicipalities() {
        // Verifica che la lista dei comuni sia inizialmente vuota
        assertNotNull(district.getMunicipalities());
        assertTrue(district.getMunicipalities().isEmpty());

        // Aggiungi un comune e verifica che sia presente nella lista
        district.add("Municipality1");
        List<String> municipalities = district.getMunicipalities();
        assertEquals(1, municipalities.size());
        assertEquals("Municipality1", municipalities.getFirst());
    }

    @Test
    public void testSetMunicipalities() {
        // Crea una nuova lista di comuni e impostala sul distretto
        List<String> newMunicipalities = Arrays.asList("Municipality1", "Municipality2");
        district.setMunicipalities(newMunicipalities);

        // Verifica che la lista sia stata aggiornata correttamente
        List<String> municipalities = district.getMunicipalities();
        assertEquals(2, municipalities.size());
        assertEquals("Municipality1", municipalities.get(0));
        assertEquals("Municipality2", municipalities.get(1));
    }

    @Test
    public void testAddMunicipality() {
        // Aggiungi un comune e verifica che la lista si aggiorni correttamente
        district.add("Municipality1");
        assertEquals(1, district.getMunicipalities().size());
        assertTrue(district.getMunicipalities().contains("Municipality1"));

        // Aggiungi un altro comune e verifica nuovamente
        district.add("Municipality2");
        assertEquals(2, district.getMunicipalities().size());
        assertTrue(district.getMunicipalities().contains("Municipality2"));
    }
}