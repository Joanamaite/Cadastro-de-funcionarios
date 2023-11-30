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

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField senhaField;

    @FXML
    public void initialize() {
        // Defina o texto inicial para os campos se necessário
        emailField.setText("Email");
        senhaField.setText("Senha");
    }

    @FXML
    public void realizarLogin() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        // Lógica para verificar as credenciais usando método do modelo
        boolean credenciaisValidas = Usuario.verificarCredenciais(email, senha);

        if (credenciaisValidas) {
            System.out.println("Login bem-sucedido!");

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
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
            // Adicione lógica para exibir uma mensagem de erro ou realizar ações após um login falhado
        }
    }

    @FXML
    public void irParaTelaCadastro() {
        try {
            // Carregando o arquivo FXML da tela de cadastro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cadastrofun/usuarioInserir.fxml"));
            Parent root = loader.load();

            // Criando uma nova cena
            Scene cenaCadastro = new Scene(root);

            // Obtendo o palco (stage) atual
            Stage palcoAtual = (Stage) emailField.getScene().getWindow(); // Assume-se que emailField está em sua cena atual

            // Setando a nova cena no palco
            palcoAtual.setScene(cenaCadastro);

            // Exibindo o palco
            palcoAtual.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com exceções, se houver algum problema ao carregar a tela de cadastro
        }
    }
}
