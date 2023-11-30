package com.example.cadastrofun.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Usuario {

    private int idUsuario;
    private String nomeEmpresa;
    private String email;
    private String senha;

    // Getters e setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static boolean verificarCredenciais(String email, String senha) {
        try (Connection connection = Db.obterConexao()) {
            String sql = "SELECT * FROM Login WHERE email = ? AND senha = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, senha);

                try (ResultSet resultSet = statement.executeQuery()) {
                    // Se houver uma linha no resultado, as credenciais são válidas
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void cadastrarEmpresa(String nomeEmpresa, String email, String senha) throws SQLException {
        try (Connection connection = Db.obterConexao()) {
            // Inserir na tabela Empresa
            String sqlEmpresa = "INSERT INTO Empresa (nomeEmpresa, emailEmpresa, senhaEmpresa) VALUES (?, ?, ?)";
            try (PreparedStatement statementEmpresa = connection.prepareStatement(sqlEmpresa, Statement.RETURN_GENERATED_KEYS)) {
                statementEmpresa.setString(1, nomeEmpresa);
                statementEmpresa.setString(2, email);
                statementEmpresa.setString(3, senha);

                // Executa a inserção na tabela Empresa
                statementEmpresa.executeUpdate();

                // Obtém o idEmpresa gerado
                try (ResultSet generatedKeys = statementEmpresa.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idEmpresa = generatedKeys.getInt(1);

                        // Agora, inserir na tabela Login
                        String sqlLogin = "INSERT INTO Login (idEmpresa, email, senha) VALUES (?, ?, ?)";
                        try (PreparedStatement statementLogin = connection.prepareStatement(sqlLogin)) {
                            statementLogin.setInt(1, idEmpresa);
                            statementLogin.setString(2, email);
                            statementLogin.setString(3, senha);

                            // Executa a inserção na tabela Login
                            statementLogin.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Falha ao obter o idEmpresa ao cadastrar empresa.");
                    }
                }
            }
        }
    }
}