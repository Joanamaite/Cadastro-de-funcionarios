package com.example.cadastrofun;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UsuarioApplication.class.getResource("usuarioInserir.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 773, 521);
            stage.setTitle("Cadastro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}