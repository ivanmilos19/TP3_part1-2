import java.sql.Date;

public class Employee {
    private int empno;
    private String ename;
    private String job;
    private Employee manager;
    private Date hireDate;
    private double salary;
    private double commission;
    private Department department;


    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public Employee getManager() {
        return manager;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public double getCommission() {
        return commission;
    }

    public Department getDepartment() {
        return department;
    }
}
