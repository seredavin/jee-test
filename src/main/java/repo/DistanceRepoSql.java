package repo;

import dto.City;
import dto.Distance;
import dto.ToDistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistanceRepoSql implements DistanceRepo {
    @PersistenceContext(unitName = "item-manager-pu", type= PersistenceContextType.EXTENDED)
    public EntityManager em;

    @Override
    public List<ToDistance> findAllWhereCityAndNotInList(City city, List<City> excludeList) {
        List<Distance> toDistanceList = em.createQuery("SELECT dis FROM Distance dis WHERE " +
                                                                "dis.to NOT IN (:excludeList)" +
                                                                "AND dis.from = :city",
                                                        Distance.class)
                                        .setParameter("city", city)
                                        .setParameter("excludeList", excludeList)
                                        .getResultList();

        List<ToDistance> toList = new ArrayList<>();
        for (Distance distance : toDistanceList) {
            toList.add(new ToDistance(distance.getTo(), distance.getDistance()));
        }

        List<Distance> fromDistanceList = em.createQuery("SELECT dis FROM Distance dis WHERE " +
                        "dis.from NOT IN (:excludeList)" +
                        "AND dis.to = :city",
                Distance.class)
                .setParameter("city", city)
                .setParameter("excludeList", excludeList)
                .getResultList();

        List<ToDistance> fromList = new ArrayList<>();
        for (Distance distance : fromDistanceList) {
            fromList.add(new ToDistance(distance.getFrom(), distance.getDistance()));
        }
        toList.addAll(fromList);
        Collections.sort(toList);
        return toList;
    }

    @Transactional
    public void add(Distance distance) {
        em.merge(distance);
    }
}
