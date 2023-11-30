
package com.example.cadastrofun;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListarFuncionarioApplication extends Application  {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(InsereFuncionariosApplication .class.getResource("ListarFuncionario.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 773, 521);
            stage.setTitle("ListarFuncionario");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}