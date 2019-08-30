package az.itcity.parser.jaxb;

import az.itcity.domain.Isciler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParserMain {

    public static void main(String[] args) {

        File xml = new File("employees-new.xml");

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Isciler.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Isciler isciler = (Isciler) unmarshaller.unmarshal(xml);
            isciler.getIsci().forEach(System.out::println);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
