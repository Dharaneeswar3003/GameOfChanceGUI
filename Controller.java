import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    private int points = 100; //Initial points that the user has
    @FXML private Label pointsLabel;
    @FXML private Label resultLabel;
    @FXML private Label diceresultLabel;
    @FXML private Button rollButton;
    @FXML private Button higherButton;
    @FXML private Button lowerButton;
    @FXML private Button quitButton;
    @FXML private Button rulesButton;
    @FXML private Label welcomeLabel;

    private int firstdieRoll; // store the first die roll

    @FXML
    public void initialize() {
        higherButton.setDisable(true);
        lowerButton.setDisable(true);
        welcomeLabel.setText("Welcome to the Game of Chance!!");
        resultLabel.setText("Your result will be displayed here");
        diceresultLabel.setText("Dice output");
        checkPoints(); // Check points when the game starts
    }

    @FXML
    private void rollDice(ActionEvent event) {
        firstdieRoll = (int)(Math.random() * 6) + 1; // roll the first die when rollButton is clicked

        points -= 5; // Each roll costs 5 points
        pointsLabel.setText("Points: " + points);

        diceresultLabel.setText("First Dice: " + firstdieRoll);
        higherButton.setDisable(false);
        lowerButton.setDisable(false);
        rollButton.setDisable(true);

        checkPoints(); // Check points after rolling the dice
    }

    @FXML
    private void guessHigher(ActionEvent event) {
        int seconddieRoll = (int)(Math.random() * 6) + 1; // second dice gets automatically rolled when higher button is clicked.
        diceresultLabel.setText("Second dice: " + seconddieRoll);

        if (seconddieRoll > firstdieRoll) {
            points += 10; // Correct guess rewards 10 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("You guessed higher and won! Roll again");
        } else if (seconddieRoll < firstdieRoll) {
            points -= 10; // Incorrect guess deducts 10 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("You guessed higher and lost! Roll again");
        } else {
            points -= 5; // Tie results in a loss of 5 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("It's a tie! You lose 5 points. Roll again");
        }
        higherButton.setDisable(true);
        lowerButton.setDisable(true);
        rollButton.setDisable(false);

        checkPoints(); // Check points after guessing higher
    }

    @FXML
    private void guessLower(ActionEvent event) {
        int seconddieRoll = (int)(Math.random() * 6) + 1; // second dice gets automatically rolled when lower button is clicked.
        diceresultLabel.setText("Second Dice: " + seconddieRoll);
        if (seconddieRoll < firstdieRoll) {
            points += 10; // Correct guess rewards 10 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("You guessed lower and won! Roll again");
        } else if (seconddieRoll > firstdieRoll) {
            points -= 10; // Incorrect guess deducts 10 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("You guessed lower and lost! Roll again");
        } else {
            points -= 5; // Tie results in a loss of 5 points
            pointsLabel.setText("Points: " + points);
            resultLabel.setText("It's a tie! You lose 5 points. Roll again");
        }
        higherButton.setDisable(true);
        lowerButton.setDisable(true);
        rollButton.setDisable(false);

        checkPoints(); // Check points after guessing lower
    }

    @FXML
    private void quit(ActionEvent event) {
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

    @FXML
    //create a separarte dialog to show the rules of the game 
    private void showRules(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules");
        alert.setHeaderText(null);
        alert.setContentText("Guess if the second dice roll will be higher or lower than the first dice roll. Roll the first dice and then take your guess by clicking the buttons below. This will automatically roll dice 2 and give you the output. If you win, then u get 10 points. If you lose, 10 points will be deducted. If it's a tie then u lose 5 points. Game resets when you lose all points.");
        alert.getDialogPane().setExpandableContent(new TextArea("Rules: Guess if the second dice roll will be higher or lower than the first dice roll."));
        alert.showAndWait();
    }

    //Create a separate method to check points instead of writing multiple times...

    private void checkPoints() {
        if (points <= 0) {
            pointsLabel.setText("Points: " + points);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("You have lost all your points. Do you want to restart the game?");
            ButtonType restartYesButton = new ButtonType("Yes");
            ButtonType restartNoButton = new ButtonType("No");

            alert.getButtonTypes().setAll(restartYesButton, restartNoButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == restartYesButton) {
                    // Reset the game to its initial state
                    points = 100;
                    pointsLabel.setText("Points: " + points);
                    resultLabel.setText("Your wins/loses will be shown here");
                    diceresultLabel.setText("Dice output");
                    higherButton.setDisable(true);
                    lowerButton.setDisable(true);
                    rollButton.setDisable(false);
                } else {
                    System.err.println("Exitted program due to insufficient points");
                    // Quit the program
                    System.exit(0);
                }
            });
        }
    }
}
