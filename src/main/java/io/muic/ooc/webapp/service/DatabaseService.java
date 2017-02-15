package io.muic.ooc.webapp.service;

import java.sql.*;

/**
 * Created by Sea on 2/14/17.
 */
public class DatabaseService {
    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DatabaseService(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }


    public Connection getConnection(){
        try{
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
        } catch (Exception e){
            System.out.println("Unable to connect to database.");
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet select(String sqlCommand){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCommand);

        } catch (Exception e) {
            System.out.println("Cannot query");
        }

        return resultSet;
    }

    public void insert(String sqlCommand){
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(sqlCommand);
            preparedStmt.execute();
        } catch (Exception e){
            System.out.println("cannot insert into database.");
        }
    }

    public void delete(String sqlCommand){
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(sqlCommand);
            preparedStmt.execute();
        } catch (Exception e){
            System.out.println("cannot delete from database.");
        }
    }

    public void update(String sqlCommand){
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(sqlCommand);
            preparedStmt.execute();
        } catch (Exception e){
            System.out.println("cannot update database.");
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
}
