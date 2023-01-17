import java.sql.SQLException;

public class Task {

    public static void t1() throws SQLException {
        System.out.println("Постройте график по среднему количеству рабочих мест для каждого фискального года");
        var statement = App.connection.createStatement();
        var resultSet = statement.executeQuery("select Grant.fiscalyear ,avg(Company.number_of_jobs) from Company, Grant where Grant.company_id = Company.id group by Grant.fiscalyear");

        while (resultSet.next())
            System.out.printf("%s %s%n", resultSet.getString(1), resultSet.getString(2));

        statement.close();
    }

    public static void t2() throws SQLException {
        System.out.println("Выведите в консоль средний размер гранта для бизнеса типа \"Salon/Barbershop\" ");
        var statement = App.connection.createStatement();
        var resultSet = statement.executeQuery("select avg(amount) from Company, Grant where Grant.company_id = Company.id and Company.business_type = 'Salon/Barbershop'");
        System.out.println(resultSet.getString(1));
        statement.close();
    }

    public static void t3() throws SQLException {
        System.out.println("Выведите в консоль тип бизнеса, предоставивший наибольшее количество рабочих мест, где размер гранта не превышает $55,000.00 ");
        var statement = App.connection.createStatement();
        var resultSet = statement.executeQuery("select business_type from Company, Grant where Grant.company_id = Company.id and Grant.amount<=55000 order by Company.number_of_jobs DESC");
        System.out.println(resultSet.getString(1));
        statement.close();
    }

}
