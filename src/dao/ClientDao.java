package dao;
import com.mysql.cj.util.StringUtils;
import models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public Connection connect () {
        String dataBase = "java";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.cj.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + dataBase + "?useSSL=false";

        Connection connection = null;

        try {
            Class.forName(driver);
            connection =  DriverManager.getConnection(connectionUrl, user, password);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void addClient (Client client) {
        try {
            Connection connection = connect();
            String sql = "INSERT INTO `clientes` (`id`, `name`, `lastname`, `email`, `phone`) VALUES (NULL, '"
                    + client.getName() + "', '"
                    + client.getLastName() + "', '"
                    + client.getEmail() + "', '"
                    + client.getPhone() + "');";
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Client> makeAList () {

        List<Client> listDataBase = new ArrayList<>();

        try {
            Connection connection = connect();
            String sql = "SELECT * FROM `clientes`";
            Statement statement = connection.createStatement();
            ResultSet result =  statement.executeQuery(sql);

            while (result.next()){
                Client clientDataBase = new Client();
                clientDataBase.setId(result.getString("id"));
                clientDataBase.setName(result.getString("name"));
                clientDataBase.setLastName(result.getString("lastname"));
                clientDataBase.setEmail(result.getString("email"));
                clientDataBase.setPhone(result.getString("phone"));
                listDataBase.add(clientDataBase);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listDataBase;
    }

    public void editClient (Client client) {
        try {
            Connection connection = connect();
            String sql = "UPDATE `clientes` SET `name` = '"
                    + client.getName() +"', `lastname` = '"
                    + client.getLastName() + "', `email` = '"
                    + client.getEmail() +"', `phone` = '"
                    + client.getPhone() +"' WHERE `clientes`.`id` = "
                    + client.getId() +";";
            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveInfo(Client client){
        if(StringUtils.isEmptyOrWhitespaceOnly(client.getId())){
            addClient(client);
        } else {
            editClient(client);
        }
    }

    public void deleteClient (String id) {
        try {
            Connection connection = connect();
            String sql = "DELETE FROM clientes WHERE `clientes`.`id` = " + id;

            Statement statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
