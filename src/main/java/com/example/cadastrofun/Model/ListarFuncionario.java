package com.example.cadastrofun.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ListarFuncionario {
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final StringProperty cargo = new SimpleStringProperty();

    private final IntegerProperty id = new SimpleIntegerProperty();



    public ListarFuncionario() {
        // Construtor vazio necessário para o JavaFX
    }

    public ListarFuncionario(String nome, String telefone, String endereco, String cargo) {
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.endereco.set(endereco);
        this.cargo.set(cargo);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public String getEndereco() {
        return endereco.get();
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public String getCargo() {
        return cargo.get();
    }

    public StringProperty cargoProperty() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public static List<ListarFuncionario> obterFuncionariosDoBancoDeDados() throws SQLException {
        List<ListarFuncionario> funcionarios = new ArrayList<>();
        try (Connection connection = Db.obterConexao()) {
            String sql = "SELECT * FROM Funcionario";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ListarFuncionario listarFuncionario = new ListarFuncionario(
                                resultSet.getString("nomeFuncionario"),
                                resultSet.getString("telefone"),
                                resultSet.getString("endereco"),
                                resultSet.getString("cargo")
                        );
                        listarFuncionario.setId(resultSet.getInt("idFuncionario"));
                        funcionarios.add(listarFuncionario);
                    }
                }
            }
        }
        return funcionarios;
    }
    // Método para editar o funcionário no banco de dados

    public void editar() throws SQLException {
        Connection connection = null;
        try {
            connection = Db.obterConexao();
            connection.setAutoCommit(false); // Desativa o autoCommit
            String sql = "UPDATE Funcionario SET nomeFuncionario = ?, telefone = ?, endereco = ?, cargo = ? WHERE idFuncionario = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, this.nome.get());
                statement.setString(2, this.telefone.get());
                statement.setString(3, this.endereco.get());
                statement.setString(4, this.cargo.get());
                statement.setInt(5, this.id.get());

                statement.executeUpdate();
            }
            connection.commit(); // Realiza o commit apenas se o autoCommit estiver desativado
        } catch (SQLException e) {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback(); // Faz o rollback apenas se o autoCommit estiver desativado
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true); // Garante que o autoCommit está ativado antes de fechar a conexão
                connection.close();
            }
        }
    }

    public void deletar() throws SQLException {
        Connection connection = null;
        try {
            connection = Db.obterConexao();
            connection.setAutoCommit(false); // Desativa o autoCommit
            String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";
            System.out.println("Consulta SQL: " + sql + ", ID: " + this.id.get());
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, this.id.get());

                statement.executeUpdate();
            }
            connection.commit(); // Realiza o commit apenas se o autoCommit estiver desativado
        } catch (SQLException e) {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback(); // Faz o rollback apenas se o autoCommit estiver desativado
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true); // Garante que o autoCommit está ativado antes de fechar a conexão
                connection.close();
            }
        }
    }
}

