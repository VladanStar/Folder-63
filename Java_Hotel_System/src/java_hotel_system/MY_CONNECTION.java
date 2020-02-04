package java_hotel_system;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MY_CONNECTION {
    // u ovoj klasi mi cemo napraviti konekciju sa databse MySQL.
    //1 - preuzecemo MySQL konektor -> https://dev.mysql.com/downloads/file/?id=489462
    //2- unzipovati konektor
    //3- dodati konektor u nas projekat
    //4 - otvorite xampp
    //5- start Apache i MySQL i pokrenuti admine da bi se otvorili u Chrome tj bilo kom brauseru
    // 6- otici na php my admin -> http://localhost/phpmyadmin/index.php?lang=en
    // 7 - napraviti bazu podataka 

    // kreirati funkciju za konekciju sa MySQL
    public Connection createConnection() {

        Connection connection = null;

        MysqlDataSource mds = new MysqlDataSource();
        mds.setServerName("localhost");
        mds.setPortNumber(3306);
        mds.setUser("root");
        mds.setPassword("");
        mds.setDatabaseName("java_hotel_db");
        try {
            connection = mds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MY_CONNECTION.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;

    }

}
