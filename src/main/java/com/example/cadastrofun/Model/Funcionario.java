package com.example.cadastrofun.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String telefone;
    private String endereco;
    private String cargo;

    // Getters e setters

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public static void cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        try (Connection connection = Db.obterConexao()) {
            String sql = "INSERT INTO Funcionario (nomeFuncionario, telefone, endereco, cargo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, funcionario.getNome());
                statement.setString(2, funcionario.getTelefone());
                statement.setString(3, funcionario.getEndereco());
                statement.setString(4, funcionario.getCargo());

                // Executa a inserção no banco de dados
                statement.executeUpdate();
            }
        }
    }
}

