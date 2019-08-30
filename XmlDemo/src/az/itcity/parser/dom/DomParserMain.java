package az.itcity.parser.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import az.itcity.domain.Isci;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomParserMain {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("employees-new.xml");

            document.normalizeDocument();

            Element root = document.getDocumentElement();
            List<Isci> isciler = new ArrayList<>();

            NodeList nodeList = root.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node employeeNode = nodeList.item(i);
                if(employeeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element employeeElement = (Element) employeeNode;
                    String id = employeeElement.getAttribute("id");
                    Isci isci = new Isci();
                    isci.setId(Long.parseLong(id));

                    String firstName = employeeElement.getElementsByTagName("first_name").item(0).getTextContent();
                    isci.setFirstName(firstName);

                    String lastName = employeeElement.getElementsByTagName("last_name").item(0).getTextContent();
                    isci.setLastName(lastName);

                    String email = employeeElement.getElementsByTagName("email").item(0).getTextContent();
                    isci.setEmail(email);

                    String jobId = employeeElement.getElementsByTagName("job_id").item(0).getTextContent();
                    isci.setJobId(jobId);

                    String salary = employeeElement.getElementsByTagName("salary").item(0).getTextContent();
                    isci.setSalary(new BigDecimal(salary));


                    NodeList nodeList1 = employeeElement.getElementsByTagName("department_id");

                    if(nodeList1 != null && nodeList.getLength() > 0) {
                        String departmentId = employeeElement.getElementsByTagName("department_id").item(0).getTextContent();
                        isci.setDepartmentId(Long.parseLong(departmentId));
                    }

                    isciler.add(isci);
                }
            }

            System.out.println("isciler");
            isciler.forEach(System.out::println);

            System.out.println("iscilerin adlari");
            getXmlElements(root, "first_name")
                    .forEach(System.out::println);

            System.out.println("iscilerin maaslari");
            getXmlElements(root, "salary")
                    .forEach(System.out::println);

            System.out.println("iscilerin departamentleri");
            getXmlElements(root, "department_id")
                    .forEach(System.out::println);


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getXmlElements(Element root, String name) {
        List<String> list = new ArrayList<>();
        NodeList adlar = root.getElementsByTagName(name);
        for (int i = 0; i < adlar.getLength(); i++) {
            Node adNode = adlar.item(i);
            if(adNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) adNode;
                list.add(element.getTextContent());
            }
        }

        return list;
    }
}
