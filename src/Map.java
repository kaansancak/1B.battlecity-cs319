import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Map {

    // maybe we will need a bool for GUI dominance -
    // for grass and (tanks and bullets)
    private final int TILES = 20;
    private int level;
    private int height;
    private int width;
    private int remainingBots;
    private int botCount;
    private GameObject[][] gameObjects;
    private int[][] obstaclesMap;
    private double elapsedTime;
    private TilePane tilePane;
    private ImageView temp;
    private int tileX, tileY;
    private ArrayList<Image> images;



    public Map( ){



    }
    /* GameObject File Decode
    * 0 = null , 1 = Brick 2 = Bush 3 = IronWall 4 = Water
    * */
    public Map(int level, int[][] obstaclesMap){
        this.level = level;
        botCount = 10 + 2 * level; // WOW lol
        remainingBots = botCount;
        this.obstaclesMap = obstaclesMap;
        tileX = (int) tilePane.getTileWidth();
        tileY = (int) tilePane.getTileHeight();
        setWidth((int)tilePane.getWidth());
        setHeight((int)tilePane.getHeight());
    }

    private void intToObject(){
        for(int i = 0; i < TILES; i++){
            for(int j = 0; j < TILES; j++) {
                if(obstaclesMap[i][j] == 0){
                    gameObjects[i][j] = null;// ground
                }
                else {
                    if (obstaclesMap[i][j] == 1) {
                        gameObjects[i][j] = new Brick(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(1));
                    } else if (obstaclesMap[i][j] == 2) {
                        gameObjects[i][j] = new Bush(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(2));
                    } else if (obstaclesMap[i][j] == 3) {
                        gameObjects[i][j] = new IronWall(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(3));
                    } else if (obstaclesMap[i][j] == 4) {
                        gameObjects[i][j] = new Water(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(4));
                    }

                    temp = new ImageView(gameObjects[i][j].getImage());
                    temp.relocate(i * tileY, j * tileY);
                    tilePane.getChildren().addAll(temp);
                }
            }
        }

    }


    public void createObjects(GameObject[][] gameObjects){

    }
    public void addObjects(GameObject[][] gameObjects){
        this.gameObjects = gameObjects;
    }
    public void updateObjects(){

    }

    public boolean isDestructed(GameObject gameObject){
        return true;
    }

    public void deleteObject(GameObject gameObject){

    }

    public boolean isBotsDead(){

    }
    public void finishMap(){

    }

    public boolean isPassableTile(int x, int y){
      if( gameObjects[x][y].isPassable()){

      }
      return false;
    }

    public void drawMap(int height, int width){

    }

    // getters and setters

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRemainingBots() {
        return remainingBots;
    }

    public void setRemainingBots(int remainingBots) {
        this.remainingBots = remainingBots;
    }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

    public GameObject[][] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObject[][] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

}
