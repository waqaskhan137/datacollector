package com.commandcenter.datacollector.plugins.outputs.postgres;

import com.commandcenter.datacollector.plugins.inputs.email.message.Message;
import com.commandcenter.datacollector.plugins.outputs.Output;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Postgres implements Output {

    @Getter
    @Setter
    Connection connection;

    @Override
    public void connect() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
            setConnection(conn);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Message data) {

        String SQL = "INSERT INTO message(body,subject,from,to,messageDate) " + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(SQL);
            pstmt.setString(1, data.body());
            pstmt.setString(2, data.subject());
            pstmt.setString(2, String.valueOf(data.date()));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
