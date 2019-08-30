package az.itcity.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Isciler {

    @XmlElement(name = "employee")
    private List<Isci> isci;

    public List<Isci> getIsci() {
        return isci;
    }

    public void setIsci(List<Isci> isci) {
        this.isci = isci;
    }
}
