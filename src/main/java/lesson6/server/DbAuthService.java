package lesson6.server;

import java.sql.*;

public class DbAuthService {

    private static Connection connection;
    private static Statement statement;

    public static void dbConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userDB.db");
            statement = connection.createStatement();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void dbDisconnect() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getNicknameByLoginAndPassword(String login, String password) {
        try {
            ResultSet rs = statement.executeQuery("SELECT nickname, password FROM users WHERE login = '" + login + "'");
            if (rs.next()) {
                String userName = rs.getString("nickname");
                if(password.equals(rs.getString("password"))){
                    return userName;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changeNickName(String newNick, String oldNick) {
        try {
            statement.execute("UPDATE users set nickname = '" + newNick + "' WHERE nickname = '" + oldNick + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}