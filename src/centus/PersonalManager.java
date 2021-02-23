package centus;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalManager extends DBManager{

    public PersonalManager() throws IOException, SQLException{
        super();
    }

    public ResultSet loadLoginFromDatabase() throws SQLException {
        String selectEmails ="SELECT email FROM person;";
        return  executeSelectQuery(selectEmails);
    }

    public ResultSet loadPasswordFromDatabase(String login ) throws SQLException {
        String selectPassword ="SELECT password FROM person WHERE email =  '"   + login + "';";
        return  executeSelectQuery(selectPassword);
    }

    public  void addNewUser (User newUser) throws  SQLException{
       String insertInto = " INSERT INTO person ( first_name , last_name , email , password)"+
              "  VALUES " +
        " ( '"  + newUser.getName()  + "'"+ " , '" + newUser.getLastName() + "' , "
               +" '" + newUser.getEmail() + "' ,'" + newUser.getPassword() + "' ); " ;
              executeQuery(insertInto);
    }
}
