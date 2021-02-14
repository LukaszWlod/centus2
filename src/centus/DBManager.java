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
            connection= connect();
            statement = connection.createStatement();
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
            return DriverManager.getConnection(url,userName,password);
        }

        public void useDataBase() throws SQLException {
            statement.executeUpdate("USE wspa");
        }

        public ResultSet executeSelectQuery(String query) throws SQLException{
           this.useDataBase();
            return  statement.executeQuery(query);
        }
        public void executeQuery(String query){
            try {
                this.useDataBase();
                statement.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }




}

