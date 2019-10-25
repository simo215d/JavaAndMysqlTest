import java.sql.*;
import java.util.Scanner;

public class SimpleJdbc {
    public static void main(String[] args) throws SQLException {

        System.out.print("----- \nProgram til hentning af landedata fra world.sql databasen. \n ");

        // #1. Connect to the database
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?serverTimezone=UTC",
                "root",
                "qtj38gve");
        System.out.println("\nDatabase connected.");

        while(true) {
            System.out.println("enter sql:");
            Scanner scanner = new Scanner(System.in);

            // #2. Create a statement
            String mitQuery = scanner.nextLine();
            if (mitQuery.equals("exit")) break;
            int collNum = scanner.nextInt();
            Statement statement = connection.createStatement();

            // #3. Execute the statement and send the SQL-query to the SQL-server
            ResultSet resultSet = statement.executeQuery(mitQuery);
            System.out.println("Følgende query er sendt til MySQL-serveren: " + mitQuery);
            System.out.println("Svar fra databasen: " + "\n");


            // #4. Iterate through the result and print the results
            // (vi har måske flere resultater end 1, derfor vil en løkke læse alle rækker ud fra resultSettet)
            while (resultSet.next()) {
                for (int x = 1; x <= collNum; x++) {
                    System.out.print(resultSet.getString(x) + ", ");
                }
                System.out.println();
            }
        }
        // #5. Close the connection (always!)
        connection.close();
        System.out.println("BYE");
    }
}