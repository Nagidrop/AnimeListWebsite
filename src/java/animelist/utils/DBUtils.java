/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */

package animelist.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/* Establish a connection to database */
public class DBUtils implements Serializable {

    /**
     * Try to get connection to database
     * @return database connection if successful, null if failed
     */
    public static Connection makeConnection() {
        try {
            Context context = new InitialContext();
            Context tomcatCtx = (Context) context.lookup("java:comp/env");

            DataSource ds = (DataSource) tomcatCtx.lookup("AnimeListDataSource");

            Connection conn = ds.getConnection();

            return conn;

        } catch (NamingException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) { 
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}

