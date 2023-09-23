package com.example.application.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.sqlite";
        try (Connection c = DriverManager.getConnection(url)) {
            // Cria a tabela se ela não existir
            String createTableSql = """
                    CREATE TABLE IF NOT EXISTS note (
                        id_user INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo VARCHAR(250),
                        descricao VARCHAR(250)
                    )
                    """;
            try (PreparedStatement createTableStatement = c.prepareStatement(createTableSql)) {
                createTableStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Insere valores na tabela
            String insertSql = "INSERT INTO note (titulo, descricao) VALUES (?, ?)";
            try (PreparedStatement insertStatement = c.prepareStatement(insertSql)) {
                insertStatement.setString(1, "Exemplo de Título");
                insertStatement.setString(2, "Exemplo de Descrição");
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}