import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class ViewFrame {

    private boolean returnCall = false;

    public ViewFrame( String title, String message){
        Stage viewFrame = new Stage();
        viewFrame.initModality(Modality.APPLICATION_MODAL);
        viewFrame.setTitle(title);
        viewFrame.setMinHeight(1000);
        viewFrame.setMinWidth(1000);
        Label frameLabel = new Label(message);
        Button returnButton = new Button();
        returnButton.setText("Back");

        returnButton.setOnAction( event -> {
            returnCall = true;
            viewFrame.close();
        });

        VBox answerBoxes = new VBox(10);
        answerBoxes.getChildren().addAll(frameLabel, returnButton);
        answerBoxes.setAlignment(Pos.CENTER);
        Scene frameScene = new Scene(answerBoxes);
        viewFrame.setScene(frameScene);
        viewFrame.showAndWait();
    }

    public boolean isReturnCall() {
        return returnCall;
    }

    public void setReturnCall(boolean returnCall) {
        this.returnCall = returnCall;
    }
}
