package legoset;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Weight {

    @XmlValue
    private Double value;

    @XmlAttribute
    private String unit;

    public Weight() {
    }

    public Weight(Double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

}