package az.itcity.parser.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParserMain {

    public static void main(String[] args) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            IsciSaxOxuyanSinif employeeHandler = new IsciSaxOxuyanSinif();
            parser.parse("employees.xml", employeeHandler);

            System.out.println("isciler parse olundu");
            employeeHandler.getIsciler().forEach(System.out::println);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
