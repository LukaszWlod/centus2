package centus;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBManager {

        private  Properties props;
        private Connection connection;
        private Statement statement;

        public  DBManager()throws  IOException, SQLException{
            this.readDataBaseProperties();
            this.connect();
        }

    private void  readDataBaseProperties ()throws  IOException {
        props = new Properties();
        FileInputStream in = new FileInputStream("db.properties");
        props.load(in);
        in.close();

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
    }
    public Connection connect ()throws SQLException{
            String url = props.getProperty("jdbc.url");
            String userName = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

        System.out.println("Connected to database "+ url);

            return DriverManager.getConnection(url,userName,password);
        }

        public void createTable (String sqlQuery){
            try{

                connection =connect();
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                statement.executeUpdate(sqlQuery);
                connection.commit();
            }catch (SQLException ex){
                try {
                    connection.rollback();
                } catch (SQLException ex1){
                    System.out.println("Bład podczas tworzenia tabel\n" + ex);
                }
            }

        }
        public void useDataBase() throws SQLException {
            statement.executeUpdate("USE wspa");
        }

        public ResultSet doQuery (String query) throws SQLException{
           this.useDataBase();
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            return  statement.executeQuery(query);
        }



}

