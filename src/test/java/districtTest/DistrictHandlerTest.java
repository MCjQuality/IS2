package districtTest;

import it.unibs.ing.elaborato.model.district.District;
import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.model.district.DistrictRepository;
import it.unibs.ing.elaborato.model.district.Districts;
import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DistrictHandlerTest {

    private DistrictRepository repo;
    private Districts districts;
    private DistrictHandler handler;

    @Before
    public void setUp() throws FileReaderException {
        repo = mock(DistrictRepository.class);
        districts = mock(Districts.class);

        when(repo.read()).thenReturn(districts);

        handler = new DistrictHandler(repo);
    }

    @Test
    public void testCreateDistrict() {
        String districtName = "District1";
        List<String> municipalities = Arrays.asList("Municipality1", "Municipality2");


        District district = new District(districtName);
        district.setMunicipalities(municipalities);
        when(districts.createDistrict(districtName, municipalities)).thenReturn(district);

        District createdDistrict = handler.createDistrict(districtName, municipalities);

        assertNotNull(createdDistrict);
        assertEquals(districtName, createdDistrict.getName());
        assertEquals(municipalities, createdDistrict.getMunicipalities());
    }

    @Test
    public void testAddDistrict() {
        String districtName = "District2";
        List<String> municipalities = Arrays.asList("Municipality1", "Municipality2");

        District district = new District(districtName);
        district.setMunicipalities(municipalities);
        when(districts.createDistrict(districtName, municipalities)).thenReturn(district);

        handler.addDistrict(districtName, municipalities);

        verify(districts).addDistrict(district);
    }

    @Test
    public void testIsDistrictExists() {
        String districtName = "ExistingDistrict";
        when(districts.isDistrictExists(districtName)).thenReturn(true);

        boolean exists = handler.isDistrictExists(districtName);

        assertTrue(exists);
        verify(districts).isDistrictExists(districtName);
    }

    @Test
    public void testIsMunicipalityPresent() {
        String municipality = "ExistingMunicipality";
        when(districts.isMunicipalityPresent(municipality)).thenReturn(true);

        boolean present = handler.isMunicipalityPresent(municipality);

        assertTrue(present);
        verify(districts).isMunicipalityPresent(municipality);
    }

    @Test
    public void testGetDistricts() {
        District district1 = new District("District1");
        district1.setMunicipalities(List.of("Municipality1"));
        List<District> districtList = Collections.singletonList(district1);
        when(districts.getDistricts()).thenReturn(districtList);

        List<District> result = handler.getDistricts();

        assertEquals(districtList, result);
        verify(districts).getDistricts();
    }

    @Test
    public void testSave() throws FileWriterException {
        doNothing().when(repo).write(districts);

        handler.save();

        verify(repo).write(districts);
    }

    @Test(expected = FileReaderException.class)
    public void testConstructorThrowsFileReaderException() throws FileReaderException {
        when(repo.read()).thenThrow(new FileReaderException());
        new DistrictHandler(repo);
    }

    @Test(expected = FileWriterException.class)
    public void testSaveThrowsFileWriterException() throws FileWriterException {
        doThrow(new FileWriterException()).when(repo).write(districts);

        handler.save();
    }
}