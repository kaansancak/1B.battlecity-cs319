package UserInterface.Frame;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class ViewFrame {
    private boolean returnCall = false;
    private static final int padding = 5;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGTH = 5;
    private static final int PANE_WIDTH = 600;
    private static final int PANE_HEIGHT = 600;

    public ViewFrame( String title, ArrayList<String> message){
        Stage viewFrame = new Stage();
        viewFrame.setResizable(false);
        StackPane pane = new StackPane();
        viewFrame.initModality(Modality.APPLICATION_MODAL);
        viewFrame.setTitle(title);
        Label[] labels = new Label[7];

        for( int i = 0; i < message.size(); i++) {
            labels[i] = new Label(message.get(i));
            labels[i].setId("actiontarget1");
            labels[i].setPadding(new Insets(padding, padding, padding, padding));
        }

        Button returnButton = new Button();
        styleButton(returnButton);
        returnButton.setText("Back");

        returnButton.setOnAction( event -> {
            returnCall = true;
            viewFrame.close();
        });
        Image im = new Image(Paths.get("."+"/MediaFiles/metal.png").toUri().toString(), true);
        pane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        VBox answerBoxes = new VBox(10);
        answerBoxes.getChildren().addAll(labels[0],labels[1],labels[2],labels[3],labels[4],labels[5],labels[6], returnButton);
        answerBoxes.setAlignment(Pos.CENTER);
        pane.getChildren().add( answerBoxes);
        Scene frameScene = new Scene(pane, PANE_WIDTH, PANE_HEIGHT);
        String  style = getClass().getResource("../../style.css").toExternalForm();
        frameScene.getStylesheets().add(style);
        viewFrame.setScene(frameScene);
        viewFrame.showAndWait();
    }

    private void styleButton( Button returnButton) {
        returnButton.setId("glass-grey");
        returnButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGTH);


        returnButton.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                returnButton.setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        returnButton.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                returnButton.setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
    }
    public boolean isReturnCall() {
        return returnCall;
    }
    public void setReturnCall(boolean returnCall) {
        this.returnCall = returnCall;
    }
}