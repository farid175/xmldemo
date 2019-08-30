package az.itcity.parser.stax;

import az.itcity.domain.Isci;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StaxParserMain {

    public static void main(String[] args) {

        try {

            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("employees-new.xml"));

            List<Isci> isciler = new ArrayList<>();
            boolean isEmployee = false;
            boolean isId = false;
            boolean isFirstName = false;
            boolean isLastName = false;
            boolean isEmail = false;
            boolean isJobId = false;
            boolean isSalary = false;
            boolean isDepartmentId = false;
            Isci temp = null;

            int counter = 1;
            int dovr = 1;
            while (reader.hasNext() && counter <= 2) {
                int event = reader.next();
                if (event == XMLEvent.START_DOCUMENT) {
                    System.out.println("document basladi");
                } else if (event == XMLEvent.START_ELEMENT) {
                    String name = reader.getLocalName();

                    System.out.println("start element " + reader.getName().toString());
                    System.out.println("attribut sayi = " + reader.getAttributeCount());

                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        String attName = reader.getAttributeName(i).toString();
                        String attValue = reader.getAttributeValue(i);
                        System.out.println(attName + " = " + attValue);
                    }
                    System.out.println("");

                    if(name.equals("employee")) {
                        isEmployee = true;
                        temp = new Isci();
                        String attribute = reader.getAttributeValue("", "id");
                        if(attribute != null) {
                            temp.setId(Long.parseLong(attribute));
                        }
                    } else if(name.equals("first_name")) {
                        isFirstName = true;
                    } else if(name.equals("last_name")) {
                        isLastName = true;
                    } else if(name.equals("email")) {
                        isEmail = true;
                    } else if(name.equals("job_id")) {
                        isJobId = true;
                    } else if(name.equals("salary")) {
                        isSalary = true;
                    } else if(name.equals("department_id")) {
                        isDepartmentId = true;
                    }

                } else if (event == XMLEvent.CHARACTERS) {
                    String data = reader.getText();
                    System.out.println("element content = " + data);

                    if(isFirstName) {
                        temp.setFirstName(data);
                    } else if(isLastName) {
                        temp.setLastName(data);
                    } else if(isEmail) {
                        temp.setEmail(data);
                    } else if(isJobId) {
                        temp.setJobId(data);
                    } else if(isSalary) {
                        temp.setSalary(new BigDecimal(data));
                    } else if(isDepartmentId) {
                        temp.setDepartmentId(Long.parseLong(data));
                    }
                } else if (event == XMLEvent.END_ELEMENT) {
                    String name = reader.getLocalName();
                    System.out.println("end element " + name);

                    if(name.equals("employee")) {
                        isEmployee = false;
                        isciler.add(temp);
                        temp = null;
                        counter++;
                        dovr++;
                    } else if(name.equals("first_name")) {
                        isFirstName = false;
                    } else if(name.equals("last_name")) {
                        isLastName = false;
                    } else if(name.equals("email")) {
                        isEmail = false;
                    } else if(name.equals("job_id")) {
                        isJobId = false;
                    } else if(name.equals("salary")) {
                        isSalary = false;
                    } else if(name.equals("department_id")) {
                        isDepartmentId = false;
                    }

                } else if (event == XMLEvent.END_DOCUMENT) {
                    System.out.println("document bitdi");
                }

            }

            System.out.println("stax xml.parser " + dovr + " defe isledi");


            System.out.println("isciler");
            isciler.forEach(System.out::println);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
