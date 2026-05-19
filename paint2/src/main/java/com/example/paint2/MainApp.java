package com.example.paint2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
public class MainApp extends Application {
    @Override
    public void start(Stage escenarioPrincipal) throws Exception {

        URL urlFxml = getClass().getResource("/com/example/paint2/hello-view.fxml");
        FXMLLoader cargador = new FXMLLoader(urlFxml);
        Scene escena = new Scene(cargador.load());
        escenarioPrincipal.setTitle("Paint App");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.setResizable(true);
        escenarioPrincipal.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}