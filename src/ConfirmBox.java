import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/29/2017.
 *
 */
public class ConfirmBox{

    private static boolean answer;

    public static boolean display( String title, String message){
        Stage answerWindow = new Stage();
        answerWindow.initModality(Modality.APPLICATION_MODAL);
        answerWindow.setTitle(title);
        answerWindow.setMinHeight(200);
        answerWindow.setMinWidth(250);
        Label answerLabel = new Label(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction( event -> {
            answer = true;
            answerWindow.close();
        });

        noButton.setOnAction(event -> {
            answer = false;
            answerWindow.close();
        });
        VBox answerBoxes = new VBox(10);
        answerBoxes.getChildren().addAll(answerLabel, yesButton, noButton);
        answerBoxes.setAlignment(Pos.CENTER);
        Scene answerScene = new Scene(answerBoxes);
        answerWindow.setScene(answerScene);
        answerWindow.showAndWait();
        return answer;
    }

}
