package example;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/example/view/login.fxml"));
        primaryStage.setTitle("Barn Manager");
        primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);

        //stopDatabase();
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
