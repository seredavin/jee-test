package repo;

import dto.City;
import dto.Distance;
import dto.ToDistance;

import java.util.List;

public interface DistanceRepo {
    List<ToDistance> findAllWhereCityAndNotInList(City city, List<City> excludeList);
    void add(Distance distance);
}
