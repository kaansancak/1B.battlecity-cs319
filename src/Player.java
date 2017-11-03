import javafx.stage.Stage;

import java.util.ArrayList;

/*import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class Player extends Tank {
    private int score;
    private int controllerId;
    private ArrayList<String> controller;

    public Player( int id, int controllerId){
        super.setId(id);
        this.controllerId = controllerId;
    }

    public void setController( int controllerId){
        //This method must be modofied after writing input controller
        //change the return type to ArrayList<String>

    }

    public void setController( ArrayList<String> keyList){
        //Change the input controller of given player
    }
    public void start(Stage primaryStage){

    }
}
