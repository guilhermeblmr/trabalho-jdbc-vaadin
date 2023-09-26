package com.example.application.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.application.controllers.SpentController;

public class SpentModel {
    private static final String url = "jdbc:sqlite:database.sqlite";

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(url)) {
            String createTableSql = """
                    CREATE TABLE IF NOT EXISTS spent (
                        id_spent INTEGER PRIMARY KEY AUTOINCREMENT,
                        tipo VARCHAR(250),
                        data DATE,
                        valor DOUBLE,
                        formaPagamento VARCHAR(250),
                        id_user INTEGER,
                        FOREIGN KEY (id_user) REFERENCES user (id_user) 
                    );
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

    public static SpentController getSpentById(int id) {
        SpentController gasto = null;
        try (Connection c = DriverManager.getConnection(url)) {
            String selectSql = "SELECT * FROM spent WHERE id_spent=?";
            try (PreparedStatement selectStatement = c.prepareStatement(selectSql)) {
                selectStatement.setInt(1, id);
                ResultSet resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    Integer id_spent = resultSet.getInt("id_spent");
                    String tipo = resultSet.getString("tipo");
                    Date data = resultSet.getDate("data");
                    double valor = resultSet.getDouble("valor");
                    String formaPagamento = resultSet.getString("formaPagamento");
                    gasto = new SpentController(id_spent, tipo, data, valor, formaPagamento);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasto;
    }

    public static void insert(String tipo, Date data, Double valor, String formaPagamento) {
        try (Connection c = DriverManager.getConnection(url)) {
            String insertSql = "INSERT INTO spent (tipo, data, valor, formaPagamento) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = c.prepareStatement(insertSql)) {
                insertStatement.setString(1, tipo);
                insertStatement.setDate(2, new java.sql.Date(data.getTime()));
                insertStatement.setDouble(3, valor);
                insertStatement.setString(4, formaPagamento);
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<SpentController> getAll() {
        List<SpentController> gastos = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(url)) {
            String selectSql = "SELECT * FROM spent";
            try (PreparedStatement selectStatement = c.prepareStatement(selectSql)) {
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    Integer id_spent = resultSet.getInt("id_spent");
                    String tipo = resultSet.getString("tipo");
                    Date data = resultSet.getDate("data");
                    double valor = resultSet.getDouble("valor");
                    String formaPagamento = resultSet.getString("formaPagamento");
                    gastos.add(new SpentController(id_spent, tipo, data, valor, formaPagamento));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    public static void update(int id, SpentController novoGasto) {
        try (Connection c = DriverManager.getConnection(url)) {
            String updateSql = "UPDATE spent SET tipo=?, data=?, valor=?, formaPagamento=? WHERE id_spent=?";
            try (PreparedStatement updateStatement = c.prepareStatement(updateSql)) {
                updateStatement.setString(1, novoGasto.getTipo());
                updateStatement.setDate(2, new java.sql.Date(novoGasto.getData().getTime()));
                updateStatement.setDouble(3, novoGasto.getValor());
                updateStatement.setString(4, novoGasto.getFormaPagamento());
                updateStatement.setInt(5, id);
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
            String deleteSql = "DELETE FROM spent WHERE id_spent=?";
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
