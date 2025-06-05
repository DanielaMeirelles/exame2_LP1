package fatec.sjc.sp.exame2_lp1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaInicialView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }
}