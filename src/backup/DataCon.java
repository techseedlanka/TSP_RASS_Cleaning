package backup;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataCon {

    public static final String DB = "techseed_payroll_rass_target";  
    public static final String USER = "root";
    public static final String PASS = "123";
    public static final String SERVER = "localhost";
    private static Connection dataCon;

    public static void ConnectionDB() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        setDataCon(DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DB, USER, PASS));
    }

    public static Connection getDataCon() {
        return dataCon;
    }

    public static void setDataCon(Connection aDataCon) {
        dataCon = aDataCon;

    }
}
