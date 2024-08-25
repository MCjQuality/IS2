package it.unibs.ing.elaborato.model.district;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.util.List;

public class DistrictHandler
{
    private final Districts districts;
    private final DistrictRepository repo;

    public DistrictHandler(DistrictRepository repo) throws FileReaderException
    {
        this.repo = repo;
        this.districts = repo.read();
    }

    public District createDistrict(String name, List<String> municipalities)
    {
        return districts.createDistrict(name, municipalities);
    }

    public void addDistrict(String name, List<String> municipalities)
    {
        districts.addDistrict(createDistrict(name, municipalities));
    }

    public boolean isDistrictExists(String name)
    {
        return districts.isDistrictExists(name);
    }

    public boolean isMunicipalityPresent(String municipality)
    {
        return districts.isMunicipalityPresent(municipality);
    }

    public List<District> getDistricts()
    {
        return districts.getDistricts();
    }

    public void save() throws FileWriterException
    {
        repo.write(districts);
    }

}
