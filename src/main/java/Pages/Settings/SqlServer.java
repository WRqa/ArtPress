package Pages.Settings;

import java.sql.*;

public class SqlServer {

    public void connect() throws SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:sqlserver://10.47.100.56";

        //Database Username
        String username = "sa";

        //Database Password
        String password = "R0dth88!";

        //Query to Execute
        //String query = "select *  from CrowdFunding.dbo.AspNetUsers where Email = 'workrocksqa@gmail.com'";
        String query = "select * from CrowdFunding.dbo.AspNetUsers";

        //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl,username,password);

        //Create Statement Object
        Statement stmt = con.createStatement();

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()){
            String myName = rs.getString(1);
            String myAge = rs.getString(2);
            String m3 = rs.getString(3);
            System. out.println(myName+"  "+myAge+" " + m3);
        }
        // closing DB Connection
        con.close();
    }

}
