import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Main {
    /* Load JDBC Driver. */
    public static void main(String[] args) {
        /* Load JDBC Driver. */
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String pass = "jUgd2yzZ2*9!";
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Database Connected");
//            displayTable(connection, "emp");


            String preparedSql = "SELECT * FROM emp WHERE efirst = ? AND ename = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(preparedSql)) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter first name: ");
                String firstName = sc.next();
                preparedStatement.setString(1, firstName);

                System.out.print("Enter last name: ");
                String lastName = sc.next();
                preparedStatement.setString(2, lastName);

                ResultSet results = preparedStatement.executeQuery();

                while (results.next()) {
                    String efirst = results.getString("efirst");
                    String ename = results.getString("ename");

                    System.out.println("Employee First Name: " + efirst + ", Last Name: " + ename);
                }
                results.close();
                sc.close();
            }

//            DeptDAO departmentDao = new DeptDAO(connection);
//            Department dept20 = departmentDao.find(20);
//            if (dept20 != null) {
//                System.out.println(dept20); // Ensure Department has a toString method
//            } else {
//                System.out.println("Department with ID 20 not found.");
//            }
            DAOFactory.setConnection(connection);

            EmployeeDAO employeeDao = DAOFactory.getEmployeeDAO();
            Employee employee = employeeDao.find(7369);

            if (employee != null) {
                System.out.println(employee);
            } else {
                System.out.println("Employee with the specified ID not found.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayDepartment(Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        ResultSet resultat = statement.
                executeQuery( "SELECT deptno, dname FROM dept" );

        while ( resultat.next() ) {
            int deptno = resultat.getInt( "deptno");
            String dname = resultat.getString( "dname" );

            System.out.println("Department " + deptno + " is for "
                    + dname + " and located in ? ");
        }
        resultat.close();
    }

//    public static void moveDepartment(Connection connection, int empno, int newDeptno) {
//        String sql = "UPDATE employee SET deptno = ? WHERE empno = ?";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, newDeptno);
//            pstmt.setInt(2, empno);
//
//            int affectedRows = pstmt.executeUpdate();
//            if (affectedRows > 0) {
//                System.out.println("Employee " + empno + " moved to department " + newDeptno);
//            } else {
//                System.out.println("Employee with number " + empno + " not found.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//     }

    public static void moveDepartment(Connection connection, int empno, int newDeptno) {
        String sql = "UPDATE employee SET deptno = ? WHERE empno = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            pstmt.setInt(1, newDeptno);
            pstmt.setInt(2, empno);

            // Execute the update
            int affectedRows = pstmt.executeUpdate();

            // Check the update result
            if (affectedRows > 0) {
                System.out.println("Employee " + empno + " moved to department " + newDeptno);
            } else {
                System.out.println("Employee with number " + empno + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void displayTable(Connection connection, String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metadata.getColumnName(i) + " | ");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    value = value != null ? value : "null";
                    System.out.print(value + " | ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error displaying table " + tableName);
        }
    }
}