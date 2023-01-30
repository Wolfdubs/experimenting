package JDBC;

/*
//DB stores data for systems
// RDBMS Relational DataBase Management System
    use Java to fire SQL queries to the DB
    create front-end UI to execute Java code that fires SQL queries sent to the DB
        JDBC = Java DataBase Connectivity
        Connect Java with DB using Driver (4 types) depends on DB type Oracle, postgresql, mySQL
            1. Import the package (java.sql.*)
            2. a) Load the driver - type depends on the type of DB you are using. For MySQL = com.mysql.jdbc.Driver -> Must download library as jar file from internet
               b) Register the Driver: use class.forName("com.mySQL.jdbc.Driver) to register the driver to the class
            3. Establish the connection: must instantiate the Connection interface
            4. Create the SQL statement object: 3 types
                    - Normal statement
                    - Prepared statement - for inbuilt predefined query but with different values
                    - Callable statement - to execute procedural language in SQL for stored procedures
            5. Execute the query; response format depends on type of SQL operation; select returns a table, insert returns count of rows effected
            6. Process results
            7. Close connection & statement objects
// Design Pattern: DB steps should always be written inside DAO Data Access Object layer so any problems only affect DAO layer and not other parts of the app
*/

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

//Uniform way for java to connect with the different DBs; adding the specific vendor's JDBC added to the build path
public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String driver = "com.mysql.cj.jdbc.Driver"; //register the Driver. my download jar containing this library
        String url = "jdbc:mysql://localhost:3306/family_schema";
        String username = "root";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter password for your database");
        String password = scanner.nextLine();
        String query1 = "select * from family_schema.family";
        Class.forName(driver); //forName is method to load driver, by calling the static block in the given driver class, which calls the register driver method
        Connection con = DriverManager.getConnection(url, username, password); //establish connection: Connection is interface so must instantiate using getConnection() method that returns object of Connection
        //getConnection takes 3 parameters; url, username, password
        Statement st = con.createStatement(); //creates Statement object from Statement interface

        //QUERY DB
        ResultSet resultSet = st.executeQuery(query1);   //execute the sql DQL (ie select) query. Select will return data as a table structure, can store in object of ResultSet
        //Process results
        while (resultSet.next())
             //resultSet pointer starts at the column headers, so must shift it to the 1st row, and keep going down all rows. next() both checks if there is a next row, and will shift the pointer too if true
        System.out.println(resultSet.getInt(1) + " : " + resultSet.getString(2) + " : " + resultSet.getDouble(3) + " : "
                        + resultSet.getString(4) + " : " + resultSet.getString(5) + " : " + resultSet.getBoolean(6) + " : "
                        + resultSet.getString(7));
                //System.out.println(resultSet.getInt(1) + " : " + resultSet.getString("name") + resultSet.getInt(3) + " : " + resultSet.getInt(4));        // get the values from specified columns column. DB indexing starts at 1
    }
}
/*
        //UPDATE DB with normal statement
        String val1 = "boo";
        int val2 = 4;
        String insertQuery = "insert into tablename values ('" + val1 + "'," + val2 + ")";
        int countEffectedRows = st.executeUpdate(insertQuery);   //executes DML query (ie insert/delete). returns int count of effected rows
        System.out.println(countEffectedRows);

        //UPDATE DB with prepared statement; if you have many values, simpler than manually passing them in
        //for fixed queries but changing values
        String preparedQuery = "insert into pekingese.pekingese_table values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(preparedQuery);
        ps.setInt(1, 3);   //takes which ? you refer to, and the value for it
        ps.setString(2, "dexter");
        ps.setInt(3, 15);
        ps.setInt(4, 2022);
        int count = ps.executeUpdate();
        System.out.println(count);
        st.close();    //if problems when running this, may need to make new st and con for each step
        ps.close();
        con.close();
    }
}
*/

/*
        //loading a class via Class class
        Class.forName("JDBC.ForNameDemo"); //loading the class will just execute its static block
        Class.forName("JDBC.ForNameDemo").getDeclaredConstructor().newInstance(); //creates an object so executes static and instance blocks
        ForNameDemo fnd = new ForNameDemo();   //this will execute both its static block and instance block
    }
}


class ForNameDemo{  //to call the static block without creating an object, you can load the class
    static{
        System.out.println("inside static block");  //when object is loaded, static block is calledx
    }
    {
        System.out.println("inside instance block");  //then when object is created, instance block is called
    }
}

*/

/*
DDL = data definition language for changing structure of the DB, e.g. creating table
DQL = data query language = to fetch data
DML = data modifying language
 */



class PostgressJDBC {
    public static void main(String[] args) {
        String url = "jsbc:postgresql://localhost/test";  //creating the connection; specify connection utl including port
        Connection connection = null;
        int pekingeseNo = 1;
        String name = "womble";
        int age = 13;
        String sql = "insert into pekingese(pekingeseNo, name, age) " + "values(" + pekingeseNo + "," + name + "," + age + ")";
        try {
            connection = DriverManager.getConnection(url, "username", "password");  //creates live connection to the db
            Statement statement = connection.createStatement();    //statement object created on this connection
            int response = statement.executeUpdate(sql);   //returns number of rows effected
            if (response==1) System.out.println("Inserted successfully " + sql);    //expecting 1 row to have been effected, as posting 1 new entry
            else System.out.println("Insertion failed");
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } finally {
            try {
                connection.close();
            } catch (SQLException sqle) {
                System.err.println(sqle);
            }
        }

    }
}








