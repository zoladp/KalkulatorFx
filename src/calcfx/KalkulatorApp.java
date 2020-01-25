package calcfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class KalkulatorApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = new Pane();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KalkulatorApp.class.getResource("KalkulatorOkno.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 300, 470);
            scene.getStylesheets().add(getClass().getResource("KalkulatorStyl.css").toExternalForm());
            primaryStage.setOnCloseRequest((e) -> {KalkulatorControler.zamknijKalkulator();});
            //primaryStage.getIcons().add(new Image("kalkIcon.png"));
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kalkulator");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}