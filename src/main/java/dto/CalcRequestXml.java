package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CalcRequestXml")
public class CalcRequestXml {
    private DistanceType distanceType;
    private Long to;
    private Long from;

    public DistanceType getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }
}
