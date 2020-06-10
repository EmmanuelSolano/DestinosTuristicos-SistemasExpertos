package cr.ucr.ac.destinosturisticos;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public ConnectionClass(){


    }

    public Connection createConnection(String user, String password, String database, String server) throws ClassNotFoundException, SQLException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build(); //política de conección

        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionUrl = null;

        //Class.forName("net.sourceforge.jtds.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance ();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //connectionUrl = "jdbc:jtds:sqlserver:3306//"+ server+";"+database+";user="+user+
            //    ";password="+password+";";

        connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + "3306" + "/" + database, user, password);

        //connection= DriverManager.getConnection(connectionUrl);

        return connection;
    }
}
