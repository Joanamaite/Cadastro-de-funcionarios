package com.example.cadastrofun.Controller;

import com.example.cadastrofun.Model.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.sql.SQLException;

public class FuncionarioController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField cargoField;

    @FXML
    private Button cadastrarButton;

    @FXML
    private void initialize() {
        telefoneField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
    }

    @FXML
    private void cadastrarFuncionario() {
        // Verifica se todos os campos estão preenchidos
        if (camposPreenchidos()) {
            try {
                // Obter dados do formulário
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();
                String cargo = cargoField.getText();

                // Criar objeto Funcionario
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setTelefone(telefone);
                funcionario.setEndereco(endereco);
                funcionario.setCargo(cargo);

                // Chamar método de cadastro no banco de dados
                Funcionario.cadastrarFuncionario(funcionario);

                // Limpar campos após o cadastro
                limparCampos();

                // Adicionar lógica adicional conforme necessário
                System.out.println("Adicionou funcionário");

                // Voltar para a tabela de listagem de funcionários
                voltarParaTelaCadastro();
            } catch (SQLException e) {
                e.printStackTrace();
                // Lidar com erros, se necessário
            }
        } else {
            exibirMensagemErro("Campos obrigatórios", "Por favor, preencha todos os campos.");
        }
    }

    @FXML
    private void voltarParaTelaCadastro() {
        try {
            // Carregar o FXML da tabela de listagem de funcionários
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/cadastrofun/ListarFuncionario.fxml"));

            // Obter a cena atual
            Scene cenaAtual = cadastrarButton.getScene();

            // Configurar a nova cena, substituindo o conteúdo pelo FXML da tabela de listagem de funcionários
            cenaAtual.setRoot(root);

            // Opcionalmente, obter o palco e exibir a cena
            Stage stage = (Stage) cenaAtual.getWindow();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com erros ao carregar o FXML, se necessário
        }
    }

    private void limparCampos() {
        nomeField.clear();
        telefoneField.clear();
        enderecoField.clear();
        cargoField.clear();
    }

    private boolean camposPreenchidos() {
        return !nomeField.getText().isEmpty() &&
                !telefoneField.getText().isEmpty() &&
                !enderecoField.getText().isEmpty() &&
                !cargoField.getText().isEmpty();
    }

    private void exibirMensagemErro(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}
