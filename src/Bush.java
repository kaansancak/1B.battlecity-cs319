import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bush extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        Image image = new Image("forest.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        root.getChildren().add(box);

        primaryStage.setTitle("Bush");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

        //Constructor
       /* public Bush( int xLoc, int yLoc){
            super.setxLoc( xLoc);
            super.setyLoc( yLoc);
        }*/


}
