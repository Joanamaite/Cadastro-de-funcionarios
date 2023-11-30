package com.example.cadastrofun.Controller;

import com.example.cadastrofun.Model.ListarFuncionario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListarFuncionariosController {

    @FXML
    private TableView<ListarFuncionario> tabelaFuncionarios;

    @FXML
    private TableColumn<ListarFuncionario, String> colunaNome;

    @FXML
    private TableColumn<ListarFuncionario, String> colunaTelefone;

    @FXML
    private TableColumn<ListarFuncionario, String> colunaEndereco;

    @FXML
    private TableColumn<ListarFuncionario, String> colunaCargo;

    private AbrirDetalhesFuncionarioController detalhesController;

    public void setDetalhesController(AbrirDetalhesFuncionarioController detalhesController) {
        this.detalhesController = detalhesController;
    }

    @FXML
    private void initialize() {
        configurarTabela();
        carregarFuncionarios();
    }

    private void configurarTabela() {
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
        colunaEndereco.setCellValueFactory(cellData -> cellData.getValue().enderecoProperty());
        colunaCargo.setCellValueFactory(cellData -> cellData.getValue().cargoProperty());
    }

    private void carregarFuncionarios() {
        try {
            List<ListarFuncionario> funcionarios = ListarFuncionario.obterFuncionariosDoBancoDeDados();
            tabelaFuncionarios.getItems().addAll(funcionarios);
        } catch (SQLException e) {
            e.printStackTrace();
            // Lidar com erros, se necessário
        }
    }

    public void abrirDetalhesFuncionario(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cadastrofun/DetalhesFuncionario.fxml"));
            Parent root = loader.load();

            detalhesController = loader.getController();
            detalhesController.setTabelaFuncionarios(tabelaFuncionarios);

            // Obtenha o funcionário selecionado da tabela ou de onde estiver armazenado
            ListarFuncionario funcionarioSelecionado = tabelaFuncionarios.getSelectionModel().getSelectedItem();

            // Chame o método diretamente no controlador
            detalhesController.setAbrirDetalhesFuncionario(funcionarioSelecionado);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalhes do Funcionário");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void voltarParaTelaCadastro() {
        try {
            // Carregando o arquivo FXML da tela de cadastro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cadastrofun/FuncionarioInserir.fxml"));
            Parent root = loader.load();

            // Obtendo o palco (stage) atual
            Stage palcoAtual = (Stage) tabelaFuncionarios.getScene().getWindow();

            // Setando a nova cena no palco
            palcoAtual.setScene(new Scene(root));

            // Exibindo o palco
            palcoAtual.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
