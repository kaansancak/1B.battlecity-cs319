package UserInterface.Frame;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/29/2017.
 *
 */
public class ConfirmBox{

    private boolean answer;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 200;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 10;

    public boolean display( String title, String message, int width, int height){

        Stage answerWindow = new Stage();
        answerWindow.setResizable(false);
        answerWindow.initModality(Modality.APPLICATION_MODAL);
        answerWindow.setTitle(title);
        answerWindow.setMinHeight(width);
        answerWindow.setMinWidth(height);
        Label answerLabel = new Label(message);
        answerLabel.setId("confirm-text");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        styleButtons( yesButton, noButton);

        yesButton.setOnAction( event -> {
            answer = true;
            answerWindow.close();
        });

        noButton.setOnAction(event -> {
            answer = false;
            answerWindow.close();
        });
        StackPane pane = new StackPane();
        VBox answerBoxes = new VBox(10);
        answerBoxes.getChildren().addAll(answerLabel, yesButton, noButton);
        answerBoxes.setAlignment(Pos.CENTER);
        Image im = new Image(Paths.get("."+"/MediaFiles/metal.png").toUri().toString(), true);
        pane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        pane.getChildren().add( answerBoxes);
        Scene answerScene = new Scene( pane, FRAME_WIDTH, FRAME_HEIGHT);
        String  style = getClass().getResource("../../style.css").toExternalForm();
        answerScene.getStylesheets().add(style);

        answerWindow.setScene(answerScene);
        answerWindow.showAndWait();
        return answer;
    }

    public void styleButtons(Button yes, Button no) {
        yes.setId("glass-grey");
        yes.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        no.setId("glass-grey");
        no.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);


        yes.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
        @Override
        public void handle(MouseEvent t) {
            yes.setStyle("-fx-background-color:#c3c4c4;");
        }
        });
        yes.setOnMouseExited(new EventHandler<MouseEvent>
                () {

        @Override
        public void handle(MouseEvent t) {
            yes.setStyle("-fx-background-color:\n" +
                    "        #dae7f3,\n" +
                    "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
              }
        });

        no.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                no.setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        no.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                no.setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
    }
}
