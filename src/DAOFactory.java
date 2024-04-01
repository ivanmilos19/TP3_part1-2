import java.sql.Connection;

public class DAOFactory {

    private static Connection connection;

    private DAOFactory() {}

    public static void setConnection(Connection connection) {
        DAOFactory.connection = connection;
    }

    public static DeptDAO getDeptDAO() {
        return new DeptDAO(connection);
    }

    public static EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAO(connection);
    }
}
