package dto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table
@NamedQuery(name = "Distance.findAllWhereCityIdAndNotInList", query = "SELECT d from Distance d " +
        "where (d.from = :id or d.to = :id)" +
        "and not d.from in :excludeList " +
        "and not d.to in :excludeList")
@XmlRootElement(name="distance")
@XmlAccessorType(XmlAccessType.FIELD)
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @XmlJavaTypeAdapter(CityAdapter.class)
    private City from;

    @ManyToOne
    @XmlJavaTypeAdapter(CityAdapter.class)
    private City to;

    private Integer distance;

    public Distance() {
    }

    public Distance(City from, City to, Integer distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Distance(long id, City from, City to, Integer distance) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public City getFrom() {
        return from;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
