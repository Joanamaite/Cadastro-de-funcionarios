package com.example.cadastrofun.Controller;

import com.example.cadastrofun.Model.ListarFuncionario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

public class AbrirDetalhesFuncionarioController {

    @FXML
    private Label labelNome;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelEndereco;

    @FXML
    private Label labelCargo;

    private ListarFuncionario funcionario;


    @FXML
    private TableView<ListarFuncionario> tabelaFuncionarios;

    // Adicione este método para configurar a referência da TableView
    public void setTabelaFuncionarios(TableView<ListarFuncionario> tabelaFuncionarios) {
        this.tabelaFuncionarios = tabelaFuncionarios;
    }

    public void setAbrirDetalhesFuncionario(ListarFuncionario funcionario) {
        this.funcionario = funcionario;
        exibirDetalhesFuncionario();
    }

    private void exibirDetalhesFuncionario() {
        if (funcionario != null) {
            labelNome.setText("Nome: " + funcionario.getNome());
            labelTelefone.setText("Telefone: " + funcionario.getTelefone());
            labelEndereco.setText("Endereço: " + funcionario.getEndereco());
            labelCargo.setText("Cargo: " + funcionario.getCargo());
        } else {
            // Lidar com o caso em que o funcionário é nulo
        }
    }

    @FXML
    private void editarFuncionario() {
        if (funcionario != null) {
            // Exemplo: Diálogo de entrada para editar informações
            TextInputDialog dialogNome = criarDialogo("Editar Nome", "Novo Nome:", funcionario.getNome());
            TextInputDialog dialogTelefone = criarDialogo("Editar Telefone", "Novo Telefone:", funcionario.getTelefone());
            TextInputDialog dialogEndereco = criarDialogo("Editar Endereço", "Novo Endereco:", funcionario.getEndereco());
            TextInputDialog dialogCargo = criarDialogo("Editar Cargo", "Novo Cargo:", funcionario.getCargo());

            Optional<String> resultNome = dialogNome.showAndWait();
            Optional<String> resultTelefone = dialogTelefone.showAndWait();
            Optional<String> resultEndereco = dialogEndereco.showAndWait();
            Optional<String> resultCargo = dialogCargo.showAndWait();

            if (resultNome.isPresent() && resultTelefone.isPresent() && resultEndereco.isPresent() && resultCargo.isPresent()) {
                String novoNome = resultNome.get().trim();
                String novoTelefone = resultTelefone.get().trim();
                String novoEndereco = resultEndereco.get().trim();
                String novoCargo = resultCargo.get().trim();

                if (!novoNome.isEmpty() && !novoTelefone.isEmpty() && !novoEndereco.isEmpty() && !novoCargo.isEmpty()) {
                    try {
                        // Atualizar informações do funcionário
                        funcionario.setNome(novoNome);
                        funcionario.setTelefone(novoTelefone);
                        funcionario.setEndereco(novoEndereco);
                        funcionario.setCargo(novoCargo);
                        funcionario.editar(); // Chamar o método editar do modelo
                        exibirDetalhesFuncionario(); // Atualizar a interface
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Lidar com erros ao editar
                        exibirErro("Erro ao editar funcionário", "Ocorreu um erro ao editar o funcionário.");
                    }
                } else {
                    exibirErro("Campos vazios", "Por favor, preencha todos os campos antes de editar.");
                }
            }
        }
    }


    private TextInputDialog criarDialogo(String titulo, String cabecalho, String valorPadrao) {
        TextInputDialog dialog = new TextInputDialog(valorPadrao);
        dialog.setTitle(titulo);
        dialog.setHeaderText(cabecalho);
        return dialog;
    }


    @FXML
    private void deletarFuncionario() {
        if (funcionario != null) {
            // Exemplo: Diálogo de confirmação para deletar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar Funcionário");
            alert.setHeaderText("Confirmar Exclusão");
            alert.setContentText("Tem certeza de que deseja excluir este funcionário?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Obtém o ID do funcionário antes de deletar
                    int idFuncionario = funcionario.getId();

                    // Chama o método deletar no objeto funcionario
                    funcionario.deletar();

                    // Remove o funcionário da lista
                    List<ListarFuncionario> funcionariosList = tabelaFuncionarios.getItems();
                    funcionariosList.remove(funcionario);

                    // Atualiza a tabela
                    tabelaFuncionarios.refresh();
                    // Lógica para retornar à página anterior ou realizar outras ações necessárias
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Lidar com erros ao deletar
                    exibirErro("Erro ao deletar funcionário", "Ocorreu um erro ao deletar o funcionário.");
                }
            }
        }
    }


    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
