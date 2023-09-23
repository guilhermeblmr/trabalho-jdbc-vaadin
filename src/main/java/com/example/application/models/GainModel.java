package com.example.application.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.application.controllers.GainController;

public class GainModel {
    private static final String url = "jdbc:sqlite:database.sqlite";

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(url)) {
            String createTableSql = """
                    CREATE TABLE IF NOT EXISTS gain (
                        id_gain INTEGER PRIMARY KEY AUTOINCREMENT,
                        tipo VARCHAR(250),
                        data DATE,
                        valor DOUBLE
                    )
                    """;
            try (PreparedStatement createTableStatement = c.prepareStatement(createTableSql)) {
                createTableStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String tipo, Date data, Double valor) {
        try (Connection c = DriverManager.getConnection(url)) {
            String insertSql = "INSERT INTO gain (tipo, data, valor) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = c.prepareStatement(insertSql)) {
                insertStatement.setString(1, tipo);
                // Converte java.util.Date para java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(data.getTime());
                insertStatement.setDate(2, sqlDate);
                insertStatement.setDouble(3, valor);
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<GainController> getAll() {
        List<GainController> ganhos = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(url)) {
            String selectSql = "SELECT * FROM gain";
            try (PreparedStatement selectStatement = c.prepareStatement(selectSql)) {
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    String tipo = resultSet.getString("tipo");
                    Date data = resultSet.getDate("data");
                    double valor = resultSet.getDouble("valor");
                    ganhos.add(new GainController(tipo, data, valor));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ganhos;
    }

    public static void update(int id, GainController novoGanho) {
        try (Connection c = DriverManager.getConnection(url)) {
            String updateSql = "UPDATE gain SET tipo=?, data=?, valor=? WHERE id_gain=?";
            try (PreparedStatement updateStatement = c.prepareStatement(updateSql)) {
                updateStatement.setString(1, novoGanho.getTipo());
                updateStatement.setDate(2, new java.sql.Date(novoGanho.getData().getTime()));
                updateStatement.setDouble(3, novoGanho.getValor());
                updateStatement.setInt(4, id);
                updateStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try (Connection c = DriverManager.getConnection(url)) {
            String deleteSql = "DELETE FROM gain WHERE id_gain=?";
            try (PreparedStatement deleteStatement = c.prepareStatement(deleteSql)) {
                deleteStatement.setInt(1, id);
                deleteStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
