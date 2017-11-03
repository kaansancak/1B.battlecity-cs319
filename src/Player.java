import javafx.stage.Stage;

import java.util.ArrayList;

/*import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class Player extends Tank {

    // variables
    private int score;
    private int controllerId;
    private ArrayList<String> controller;

    public Player( int id, int controllerId){
        super.setId(id);
        this.controllerId = controllerId;
    }

    private void incrementScore() {
        score++;
    }

    private ArrayList<String> getController() {
        return controller;
    }

    private void setController( ArrayList<String> controller) {
        this.controller = controller;
    }

}
//