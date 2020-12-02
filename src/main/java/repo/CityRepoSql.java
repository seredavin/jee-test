package repo;

import dto.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class CityRepoSql implements CityRepo{

    @PersistenceContext(unitName = "item-manager-pu", type= PersistenceContextType.EXTENDED)
    public EntityManager em;

    public City getById(long id) {
        return em.find(City.class, id);
    }

    public List<City> findAll() {
        TypedQuery<City> namedQuery = em.createNamedQuery("City.getAll", City.class);
        return namedQuery.getResultList();
    }

    @Transactional
    public void add(City city) {
        em.merge(city);
    }
}
