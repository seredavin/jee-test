package dto;

public class ToDistance implements Comparable<ToDistance>{
    private City city;
    private Integer distance;

    public ToDistance(City from, Integer i) {
        this.city = from;
        this.distance = i;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(ToDistance o) {
        return this.distance.compareTo(o.getDistance());
    }

}
