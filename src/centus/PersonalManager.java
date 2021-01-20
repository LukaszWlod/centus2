package centus;

import java.io.IOException;
import java.sql.SQLException;

public class PersonalManager extends DBManager{
    private String crateUser = "CREATE TABLE if not exists person ( " + "id  INT NOT NULL auto_increment, "
            + "first_name VARCHAR(45)," + "last_name VARCHAR(45)," + "email VARCHAR(30), "
            + "admin BOOL DEFAULT false, "
            + "password VARCHAR(20) NOT NULL, " + "PRIMARY KEY (id))";
    public PersonalManager() throws IOException, SQLException{
        super();
        createTable(crateUser);
        System.out.println("Tabela person została utworzona. ");
    }
}