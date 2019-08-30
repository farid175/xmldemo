package az.itcity.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import az.itcity.domain.Isci;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IsciSaxOxuyanSinif extends DefaultHandler {

    public List<Isci> getIsciler() {
        return isciler;
    }

    private List<Isci> isciler;

    private boolean isEmployee = false;
    private boolean isId = false;
    private boolean isFirstName = false;
    private boolean isLastName = false;
    private boolean isEmail = false;
    private boolean isJobId = false;
    private boolean isSalary = false;
    private boolean isDepartmentId = false;
    private Isci temp = null;
    int counter = 1;

    private void printFlags() {

        String flags = "\nemployee: " + (isEmployee ? "+" : "-") + " , ";
        flags += "id: " + (isId ? "+" : "-") + " , ";
        flags += "fn: " + (isFirstName ? "+" : "-") + " , ";
        flags += "ln: " + (isLastName ? "+" : "-") + " , ";
        flags += "email: " + (isEmail ? "+" : "-") + " , ";
        flags += "job: " + (isJobId ? "+" : "-") + " , ";
        flags += "salary: " + (isSalary ? "+" : "-") + " , ";
        flags += "dep: " + (isDepartmentId ? "+" : "-") + " , ";
        flags += "\n";

        System.out.println(flags);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("xml document basladi");
        isciler = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + " ucun start element");

        if(qName.equals("employee")) {
            isEmployee = true;
            temp = new Isci();
        } else if(qName.equals("id")) {
            isId = true;
        } else if(qName.equals("first_name")) {
            isFirstName = true;
        }  else if(qName.equals("last_name")) {
            isLastName = true;
        } else if(qName.equals("email")) {
            isEmail = true;
        } else if(qName.equals("job_id")) {
            isJobId = true;
        } else if(qName.equals("salary")) {
            isSalary = true;
        } else if(qName.equals("department_id")) {
            isDepartmentId = true;
        }

        System.out.println("temp isci = " + temp);

        printFlags();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        System.out.println("elementin datasi = " + data);

        if(isId) {
            temp.setId(Long.parseLong(data));
        } else if(isFirstName) {
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + " ucun end element");

        if(qName.equals("employee")) {
            if(counter <= 1) {
                System.out.println("bir isci tam oxundu bitdi, isci = " + temp);
                isciler.add(temp);
                temp = null;
                counter++;
            } else {
                System.out.println("iscini ignore ele");
            }

        } else if(qName.equals("id")) {
            isId = false;
        } else if(qName.equals("first_name")) {
            isFirstName = false;
        } else if(qName.equals("last_name")) {
            isLastName = false;
        } else if(qName.equals("email")) {
            isEmail = false;
        } else if(qName.equals("job_id")) {
            isJobId = false;
        } else if(qName.equals("salary")) {
            isSalary = false;
        } else if(qName.equals("department_id")) {
            isDepartmentId = false;
        }

        System.out.println("temp isci = " + temp);

        printFlags();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("xml document bitdi");
    }


}
