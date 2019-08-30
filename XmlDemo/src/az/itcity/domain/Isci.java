package az.itcity.domain;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.StringJoiner;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Isci {

    @XmlAttribute(name = "id")
    private long id;

    @XmlElement(name = "first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    private String lastName;
    private String email;

    @XmlElement(name = "job_id")
    private String jobId;
    private BigDecimal salary;

    @XmlElement(name = "department_id")
    private long departmentId;

    public Isci() {
        this(0, "", "", "", "", BigDecimal.ZERO, 0l);
    }

    public Isci(long id, String firstName, String lastName, String email, String jobId, BigDecimal salary, long departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobId = jobId;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Isci.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("email='" + email + "'")
                .add("jobId='" + jobId + "'")
                .add("salary=" + salary)
                .add("departmentId=" + departmentId)
                .toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
