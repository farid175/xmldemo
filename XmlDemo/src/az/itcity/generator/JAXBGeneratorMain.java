package az.itcity.generator;

import az.itcity.domain.Isci;
import az.itcity.domain.Isciler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class JAXBGeneratorMain {

    public static void main(String[] args) {
        EmployeeRepository repository = new EmployeeRepository();

        List<Isci> isciList = repository.getIsciList();
        Isciler isciler = new Isciler();
        isciler.setIsci(isciList);


        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Isciler.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(isciler, new File("employees_jaxb.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
