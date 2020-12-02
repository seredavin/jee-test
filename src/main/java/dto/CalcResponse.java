package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcResponse {
    private String matrix;
    private String crowflight;

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getCrowflight() {
        return crowflight;
    }

    public void setCrowflight(String crowflight) {
        this.crowflight = crowflight;
    }
}
