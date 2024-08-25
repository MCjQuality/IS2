package it.unibs.ing.elaborato.model.district;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Districts implements Serializable {

    private final List<District> districts;

    public Districts()
    {
        this.districts = new ArrayList<>();
    }

    public District createDistrict(String name, List<String> municipalities)
    {
        District district = new District(name);
        for(String municipality : municipalities)
            district.add(municipality);
        return district;
    }

    public void addDistrict(District district)
    {
        districts.add(district);
    }

    public boolean isDistrictExists(String name)
    {
        return findDistrictByName(name).isPresent();
    }

    public boolean isMunicipalityPresent(String municipality)
    {
        return findDistrictByMunicipality(municipality).isPresent();
    }

    public Optional<District> findDistrictByName(String name)
    {
        return districts.stream().filter(district -> district.getName().equals(name)).findFirst();
    }

    public Optional<District> findDistrictByMunicipality(String municipality)
    {
        return districts.stream().filter(district -> district.getMunicipalities().contains(municipality)).findFirst();
    }

    public List<District> getDistricts()
    {
        return districts;
    }
}
