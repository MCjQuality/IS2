package districtTest;

import it.unibs.ing.elaborato.model.district.District;
import it.unibs.ing.elaborato.model.district.Districts;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DistrictsTest {

    private Districts districts;

    @Before
    public void setUp() {
        // Inizializza un'istanza di Districts prima di ogni test
        districts = new Districts();
    }

    @Test
    public void testCreateDistrict() {
        // Crea un nuovo distretto
        String districtName = "District1";
        List<String> municipalities = Arrays.asList("Municipality1", "Municipality2");
        District district = districts.createDistrict(districtName, municipalities);

        // Verifica che il distretto sia stato creato correttamente
        assertNotNull(district);
        assertEquals(districtName, district.getName());
        assertEquals(municipalities, district.getMunicipalities());
    }

    @Test
    public void testAddDistrict() {
        // Crea un nuovo distretto e aggiungilo alla lista
        District district = new District("District2");
        district.add("Municipality1");
        districts.addDistrict(district);

        // Verifica che il distretto sia stato aggiunto alla lista
        List<District> districtList = districts.getDistricts();
        assertEquals(1, districtList.size());
        assertEquals("District2", districtList.getFirst().getName());
        assertTrue(districtList.getFirst().getMunicipalities().contains("Municipality1"));
    }

    @Test
    public void testIsDistrictExists() {
        // Aggiungi un distretto e verifica se esiste
        District district = new District("District3");
        districts.addDistrict(district);

        // Verifica che il distretto esista
        assertTrue(districts.isDistrictExists("District3"));

        // Verifica che un distretto non esistente non sia trovato
        assertFalse(districts.isDistrictExists("NonExistentDistrict"));
    }

    @Test
    public void testIsMunicipalityPresent() {
        // Aggiungi un distretto con un comune e verifica se il comune è presente
        District district = new District("District4");
        district.add("Municipality1");
        districts.addDistrict(district);

        // Verifica che il comune esista
        assertTrue(districts.isMunicipalityPresent("Municipality1"));

        // Verifica che un comune non presente non sia trovato
        assertFalse(districts.isMunicipalityPresent("NonExistentMunicipality"));
    }

    @Test
    public void testFindDistrictByName() {
        // Aggiungi un distretto e cerca per nome
        District district = new District("District5");
        districts.addDistrict(district);

        Optional<District> foundDistrict = districts.findDistrictByName("District5");

        // Verifica che il distretto sia stato trovato
        assertTrue(foundDistrict.isPresent());
        assertEquals("District5", foundDistrict.get().getName());

        // Verifica che un distretto non esistente non sia trovato
        assertFalse(districts.findDistrictByName("NonExistentDistrict").isPresent());
    }

    @Test
    public void testFindDistrictByMunicipality() {
        // Aggiungi un distretto con un comune e cerca per comune
        District district = new District("District6");
        district.add("Municipality1");
        districts.addDistrict(district);

        Optional<District> foundDistrict = districts.findDistrictByMunicipality("Municipality1");

        // Verifica che il distretto sia stato trovato
        assertTrue(foundDistrict.isPresent());
        assertEquals("District6", foundDistrict.get().getName());

        // Verifica che un comune non presente non sia trovato
        assertFalse(districts.findDistrictByMunicipality("NonExistentMunicipality").isPresent());
    }

    @Test
    public void testGetDistricts() {
        // Crea e aggiungi più distretti
        District district1 = new District("District7");
        district1.add("Municipality1");
        District district2 = new District("District8");
        district2.add("Municipality2");

        districts.addDistrict(district1);
        districts.addDistrict(district2);

        // Verifica che la lista dei distretti contenga entrambi i distretti aggiunti
        List<District> districtList = districts.getDistricts();
        assertEquals(2, districtList.size());
        assertTrue(districtList.stream().anyMatch(d -> d.getName().equals("District7")));
        assertTrue(districtList.stream().anyMatch(d -> d.getName().equals("District8")));
    }
}