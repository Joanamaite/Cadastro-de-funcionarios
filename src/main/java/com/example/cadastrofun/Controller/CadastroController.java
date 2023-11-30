package com.example.cadastrofun.Controller;

import com.example.cadastrofun.Model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroController {

    @FXML
    private TextField nomeEmpresaField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField senhaField;

    @FXML
    public void cadastrar() {
        String nomeEmpresa = nomeEmpresaField.getText();
        String email = emailField.getText();
        String senha = senhaField.getText();

        try {
            // Chama o método de cadastro no modelo
            Usuario.cadastrarEmpresa(nomeEmpresa, email, senha);
            System.out.println("Cadastro realizado com sucesso!");
            // Carregando o arquivo FXML da tela de FuncionarioInserir
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cadastrofun/FuncionarioInserir.fxml"));
                Parent root = loader.load();

                // Criando uma nova cena
                Scene cenaFuncionarioInserir = new Scene(root);

                // Obtendo o palco (stage) atual
                Stage palcoAtual = (Stage) emailField.getScene().getWindow(); // Assume-se que emailField está em sua cena atual

                // Setando a nova cena no palco
                palcoAtual.setScene(cenaFuncionarioInserir);

                // Exibindo o palco
                palcoAtual.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com exceções, se houver algum problema ao carregar a tela de FuncionarioInserir
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            // Adicione lógica para lidar com erros durante o cadastro
        }
    }
    public void irParaTelaLogin() {
        try {
            // Carregando o arquivo FXML da tela de cadastro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cadastrofun/hello-view.fxml"));
            Parent root = loader.load();

            // Criando uma nova cena
            Scene cenaLogin= new Scene(root);

            // Obtendo o palco (stage) atual
            Stage palcoAtual = (Stage) emailField.getScene().getWindow(); // Assume-se que emailField está em sua cena atual

            // Setando a nova cena no palco
            palcoAtual.setScene(cenaLogin);

            // Exibindo o palco
            palcoAtual.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com exceções, se houver algum problema ao carregar a tela de cadastro
        }
    }
}

