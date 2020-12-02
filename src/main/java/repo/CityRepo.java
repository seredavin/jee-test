package repo;

import dto.City;

import java.util.List;

public interface CityRepo {
    City getById(long id);
    List<City> findAll();
    void add(City city);
}
