package dto;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "CityDistanceXml")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityDistanceXml {
    @XmlElement(name = "city")
    private List<City> cityList = null;

    @XmlElement(name = "distance")
    private List<DistanceXml> distanceXmlList = null;


    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<DistanceXml> getDistanceXmlList() {
        return distanceXmlList;
    }

    public void setDistanceXmlList(List<DistanceXml> distanceXmlList) {
        this.distanceXmlList = distanceXmlList;
    }
}
