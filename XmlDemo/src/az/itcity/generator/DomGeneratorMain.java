package az.itcity.generator;

import az.itcity.domain.Isci;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomGeneratorMain {

    public static void main(String[] args) {

       EmployeeRepository repository = new EmployeeRepository();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element employees = document.createElement("employees");

            repository.getIsciList().forEach(isci -> {

                Element employeeElement = document.createElement("employee");
                employeeElement.setAttribute("id", String.valueOf(isci.getId()));

                Element firstName = document.createElement("first_name");
                firstName.setTextContent(isci.getFirstName());
                employeeElement.appendChild(firstName);

                Element lastName = document.createElement("last_name");
                lastName.setTextContent(isci.getLastName());
                employeeElement.appendChild(lastName);

                Element email = document.createElement("email");
                email.setTextContent(isci.getEmail());
                employeeElement.appendChild(email);

                Element jobId = document.createElement("job_id");
                jobId.setTextContent(isci.getJobId());
                employeeElement.appendChild(jobId);

                Element salary = document.createElement("salary");
                salary.setTextContent(isci.getSalary().toString());
                employeeElement.appendChild(salary);

                Element department = document.createElement("department_id");
                department.setTextContent(String.valueOf(isci.getDepartmentId()));
                employeeElement.appendChild(department);

                employees.appendChild(employeeElement);
            });
            

            document.appendChild(employees);

            document.normalize();


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            File file = new File("employees-generated.xml");
            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(file));


        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
