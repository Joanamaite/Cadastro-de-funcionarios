package com.example.cadastrofun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DetalhesFuncionarioApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DetalhesFuncionarioApplication.class.getResource("DetalhesFuncionario.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            stage.setTitle("DetalhesFuncionario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
