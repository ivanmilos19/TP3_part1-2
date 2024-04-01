import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeDAO extends DAO<Employee> {
    public EmployeeDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Employee find(int id) {
        Employee employee = null;
        String sql = "SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp WHERE empno = ?";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = new Employee();
                employee.setEmpno(rs.getInt("empno"));
                employee.setEname(rs.getString("ename"));
                employee.setJob(rs.getString("job"));

                int mgrId = rs.getInt("mgr");
                if (!rs.wasNull()) {
                    Employee manager = this.find(mgrId); // Recursive call to set the manager
                    employee.setManager(manager);
                }

                employee.setHireDate(rs.getDate("hiredate"));
                employee.setSalary(rs.getDouble("sal"));
                employee.setCommission(rs.getDouble("comm"));

                int deptNo = rs.getInt("deptno");
                if (!rs.wasNull()) {
                    DeptDAO deptDAO = new DeptDAO(this.connect);
                    Department department = deptDAO.find(deptNo); // Find and set the department
                    employee.setDepartment(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean create(Employee object) {
        return false;
    }

    @Override
    public boolean update(Employee object) {
        return false;
    }

    @Override
    public boolean delete(Employee object) {
        return false;
    }

}
