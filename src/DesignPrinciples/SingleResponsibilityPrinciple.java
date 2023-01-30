package DesignPrinciples;
//makes code readable & maintainable, and easier to extend

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SingleResponsibilityPrinciple {
/*
"A class should have 1 responsibility and reason to change"
    so divide operations across multiple classes, not just 1 monster class
    DAO classes for specific methods for an overarching entity to double specific logic for an object
        for DB connections, have 1 class create the connection (allowing a variety of mechanisms across different DBMSs or users)
each class should be centered around 1 cohesive concept
If a class has several responsibilities, it should be split into a separate class
    e.g. if it has multiple clusters of methods referring to their own data

reduces coupling, ensures code cohesion within classes
    Coupling = relationship between modules. You want minimal coupling, done mainly via reducing code duplication
        2 classes work together; via method calls, dependencies, holding associated/aggregated fields, holding common functionality
        shared implementation coupling is insidious -> changing in 1 class means must change in another, but no compiler error thrown so only see at runtime
            e.g. formatting of print statements; could abstract it into its own method so changes propagate everywhere
        changes in 1 class may cause compiler alerts, or only be apparent at runtime
    If a singe change must be implemented in many places; it may mean classes are too coupled - > abstract out that info to 1 place so only change once
 */
    @Data
    private static class Pekingese {
        String name;
        int age;
        double weight;
    }

    private static class ConnectionDAO {
        private Connection connection;
        //user, password, dbms, dbName, server, port
        public ConnectionDAO(String[] connectionDetails) {}
        public Connection createConnection() throws SQLException {
            //creates connection and returns it based on given properties
            return DriverManager.getConnection("dummy");
        }
    }

    private static class PekingeseDAO {
        public void persist(Pekingese pekingese) throws SQLException {
            //use the specific ConnectionDAO
            Connection connection = new ConnectionDAO(new String[10]).createConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Pekingese (Name, Age, Weight) VALUES (" + pekingese.getName() + "," + pekingese.getAge() + ","
                    + pekingese.getWeight() +")";
            statement.executeUpdate(sql);
        }
    }

}




