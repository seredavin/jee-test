package repo;

import dto.City;

import javax.enterprise.inject.Alternative;
import java.util.Arrays;
import java.util.List;

@Alternative
public class CityRepoFake implements CityRepo{
    public City getById(long id) {
        return new City("Moscow", 55.7522200, 37.6155600);
    }

    public List<City> findAll() {
        return Arrays.asList(
                new City(1, "Moscow", 55.7522200, 37.6155600),
                new City(2, "Samara", 53.2000700, 50.1500000),
                new City(3, "St. Petersburg", 59.9386300, 30.3141300)
        );
    }

    public void add(City city) {

    }


}
