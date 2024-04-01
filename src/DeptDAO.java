import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO<Department> {

    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Department find(int id) {
        Department department = null;
        String sql = "SELECT * FROM dept WHERE deptno = ?";

        try (PreparedStatement pstmt = this.connect.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                department = new Department();
                department.setDeptno(rs.getInt("deptno"));
                department.setDname(rs.getString("dname"));
                department.setLoc(rs.getString("loc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public boolean create(Department object) {
        return false;
    }

    @Override
    public boolean update(Department object) {
        return false;
    }

    @Override
    public boolean delete(Department object) {
        return false;
    }
}
