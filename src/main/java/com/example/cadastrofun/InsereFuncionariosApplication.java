package com.example.cadastrofun;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InsereFuncionariosApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(InsereFuncionariosApplication .class.getResource("FuncionarioInserir.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 773, 521);
            stage.setTitle("CadastroFuncionarios");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
