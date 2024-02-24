
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        window = primaryStage;
        //To prevent user from accidentally exitting the program...
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        window.setTitle("Dice Game");
        window.setScene(new Scene(root, 600, 400));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    //Method to prevent user from accidentally exitting the program...
    private void closeProgram() {
        //Opens a confirmation alert box...
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Quit Game");
        alert.setContentText("Are you sure you want to quit?");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.out.println("Exitted the program successfully!");
                //if user clicks on yes button, the program will close.
                System.exit(0);
            }
            //If user clicks on no button then program will pick up where it left off.
        });
    }
}