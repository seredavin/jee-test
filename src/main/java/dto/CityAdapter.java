package dto;

import repo.CityRepoSql;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CityAdapter extends XmlAdapter<Long, City> {
    @Inject
    CityRepoSql cityRepo;

    @Override
    public City unmarshal(Long id) throws Exception {
        return new CityRepoSql().getById(id);
    }

    @Override
    public Long marshal(City city) throws Exception {
        return city.getId();
    }
}
