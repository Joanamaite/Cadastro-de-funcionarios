package com.example.cadastrofun.Controller;

import com.example.cadastrofun.Model.ListarFuncionario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetalhesFuncionarioController {

    @FXML
    private Label labelNome;

    @FXML
    private Label labelTelefone;

    @FXML
    private Label labelEndereco;

    @FXML
    private Label labelCargo;

    public void setDetalhesFuncionario(ListarFuncionario funcionario) {
        labelNome.setText("Nome: " + funcionario.getNome());
        labelTelefone.setText("Telefone: " + funcionario.getTelefone());
        labelEndereco.setText("Endereço: " + funcionario.getEndereco());
        labelCargo.setText("Cargo: " + funcionario.getCargo());
    }
}
