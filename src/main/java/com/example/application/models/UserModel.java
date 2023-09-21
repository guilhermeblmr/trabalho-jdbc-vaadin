package com.example.application.models;

import java.sql.*;

public class UserModel {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:meu_banco.sqlite";
        try(Connection c = DriverManager.getConnection(url)) {
            String sql = """
                    create table if not exists user (
												id_user integer primary key autoincrement,
												nome varchar(250),
												sobrenome varchar(250),
                                                senha varchar()
											) values (?,?,?,?,?)
                    """;
            try(PreparedStatement p = c.prepareStatement(sql)) {
                p.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }
}