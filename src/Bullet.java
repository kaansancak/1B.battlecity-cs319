import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bullet extends GameObject {

    //Variables
    //direction of the bullet
    private int dir;
    private int id;
    private boolean isCrushed;

    //Constructor
    public Bullet( int id, int xLoc, int yLoc, int dir) {
        super.setxLoc(xLoc);
        super.setyLoc(yLoc);
        this.id = id;
        this.dir = dir;
        isCrushed = false;
    }

    public void start(Stage primaryStage) {

    }

    //methods
    /*Destruct method destroys the current bullet
    if any collision occurs
    returns whether destruction is successful.
     */
    public boolean destruct(int xLoc, int yLoc) {
        //This method will be filled
        return true;
    }

    //This method moves the bullet through the map
    //in given direction
    public void move() {
        if (!isCrushed()) {
            if (dir == 1) {
                super.setxLoc(super.getxLoc() + 1);
            } else {
                super.setyLoc(super.getyLoc() + 1);
            }
        }
    }

    //Setters and Getters
    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCrushed() {
        return isCrushed;
    }
    public void setCrushed(boolean crushed) {
        isCrushed = crushed;
    }
}
