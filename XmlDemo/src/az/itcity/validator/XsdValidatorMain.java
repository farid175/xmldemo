package az.itcity.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XsdValidatorMain {

    public static void main(String[] args) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema employeeSchema = factory.newSchema(new File("employees.xsd"));

            Validator validator = employeeSchema.newValidator();
            validator.validate(new StreamSource(new FileReader("employees-new.xml")));

            System.out.println("xml document is valid");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
