import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public final static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/db/grants.sqlite3");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, CsvValidationException {
        initDB();

        Parser.run();

        Task.t1();
        Task.t2();
        Task.t3();

        connection.close();
    }

    public static void save(Grant grant) throws SQLException {

        var psCompany = connection.prepareStatement("INSERT INTO Company VALUES (?,?,?,?,?)");
        var company = grant.getCompany();
        psCompany.setLong(1, company.getId());
        psCompany.setString(2, company.getName());
        psCompany.setString(3, company.getStreet());
        psCompany.setString(4, company.getBusinessType());
        psCompany.setLong(5, company.getNumberOfJobs());
        psCompany.execute();

        var psGrant = connection.prepareStatement("INSERT INTO Grant VALUES  (?,?,?,?)");
        psGrant.setLong(1, grant.getId());
        psGrant.setDouble(2, grant.getAmount());
        psGrant.setInt(3, grant.getFiscalYear());
        psGrant.setLong(4, company.getId());
        psGrant.execute();
    }

    public static void initDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        var statement = connection.createStatement();
        statement.execute("DROP TABLE Company;");
        statement.execute("DROP TABLE Grant;");
        statement.execute("CREATE TABLE Company (id INTEGER PRIMARY KEY, name varchar, street varchar, business_type varchar, number_of_jobs int);");
        statement.execute("CREATE TABLE Grant (id INTEGER PRIMARY KEY, amount real, fiscalYear int,company_id int);");
        statement.close();
    }
}
