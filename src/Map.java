import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Map {

    // maybe we will need a bool for GUI dominance -
    // for grass and (tanks and bullets)
    private final int TILES = 20;
    private int playerCount;
    private int level;
    private int height;
    private int width;
    private int remainingBots;
    private int botCount;
    private GameObject[][] gameObjects;
    private int[][] obstaclesMap;
    private double elapsedTime;
    private TilePane tilePane;
    private Pane mapPane;
    private ImageView temp;
    private int tileX, tileY;
    private ArrayList<Image> images;
    private Player players[];
    private ArrayList<Bullet> bullets;
    Scene mapScene;
    Stage mapStage;

    public Map( ){

    }
    /* GameObject File Decode
    * 0 = Brick, 1 = Wall, 2 = Bush, 3 = Water
    * 4 = Player, 5 = Bot
    * */
    public Map(int playerCount, int level, int[][] obstaclesMap){
        mapPane = new Pane();
        this.obstaclesMap = obstaclesMap;
        gameObjects = new GameObject[TILES][TILES];
        this.playerCount = playerCount;
        players = new Player[playerCount];
        tilePane = new TilePane();
        tilePane.setPrefColumns(20);
        for(int i = 0; i < playerCount; i++){
            players[i] = new Player(i, i);
        }
        this.level = level;
        botCount = 10 + 2 * level; // WOW lol
        remainingBots = botCount;
        tileX = (int) tilePane.getTileWidth();
        tileY = (int) tilePane.getTileHeight();
        setWidth((int)tilePane.getWidth());
        setHeight((int)tilePane.getHeight());
        mapPane.getChildren().addAll(tilePane);
        bullets = new ArrayList<>();
    }

    public void initPlayers(){
        for( Player player : players){
            player.setImage(images.get(5));
            player.setLeftImage(player.getImage());
            player.setDownImage( images.get(7));
            player.setUpImage(images.get(8));
            player.setRightImage(images.get(9));
            player.setView( new ImageView(player.getImage()));
            player.setxLoc((int)tilePane.getTileWidth() * 2);
            player.setyLoc((int) (2* tilePane.getTileHeight()));
            player.getView().setTranslateX(player.getxLoc());
            player.getView().setTranslateY(player.getyLoc());
            mapPane.getChildren().addAll(player.getView());
        }
    }

    public void updatePlayers(){
        for( Player player : players){
            player.getView().setTranslateX(player.getxLoc());
            player.getView().setTranslateY(player.getyLoc());
        }
    }

    public Player getPlayer( int index){
        try {
            return players[index];
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public Pane getMapPane() {
        return mapPane;
    }

    public void showMap(){
        mapScene = new Scene( mapPane);
        mapStage = new Stage();
        mapStage.setScene(mapScene);
        mapStage.show();
    }

    public void intToObject(){
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
                    else if (obstaclesMap[i][j] == 5) {
                    gameObjects[i][j] = new Water(i * tileX, j * tileY);
                    gameObjects[i][j].setImage(images.get(5));
                }
                    gameObjects[i][j].setView( new ImageView( gameObjects[i][j].getImage()));
                    tilePane.getChildren().addAll(gameObjects[i][j].getView());
                }
            }
        }

    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void fire(Tank tank){
       Bullet fired = tank.fire();
       if(fired != null)
           System.out.print(tank.getDir() + " " + fired.getDir());
       mapPane.getChildren().addAll(fired.getView());
       System.out.print("Bullet Created");
       bullets.add(fired);
    }

    public void updateBullets(){
        for( Bullet bullet : bullets){
            bullet.move();
            bullet.getView().setTranslateX(bullet.getxLoc());
            bullet.getView().setTranslateY(bullet.getyLoc());
        }
    }

    public void createObjects(GameObject[][] gameObjects){

    }

    public void addObjects(GameObject[][] gameObjects){
        this.gameObjects = gameObjects;
    }
    public void updateObjects(){
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).move();
        }
    }

    public boolean isDestructed(GameObject gameObject){
        return true;
    }

    public void deleteObject(GameObject gameObject){
        if( gameObject instanceof Tank)
            if( !((Tank) gameObject).isAlive())
                gameObject = null;
        else if( gameObject instanceof Destructible)
            if( ((Destructible) gameObject).isDestructed())
                gameObject = null;
    }

    public boolean isBotsDead(){
        return botCount == 0;
    }
    public void finishMap(){

    }

    public boolean isPassableTile(int x, int y){
        return true;
    }

    public boolean isMoveableLoc( int x, int y){
        for( GameObject[] gameObject1: gameObjects){
            for( GameObject gameObject: gameObject1 ){
                System.out.print("Checking");
                if( gameObject.getView().contains(getPlayer(0).getView().getTranslateX(), getPlayer(0).getView().getTranslateY()))
                    return false;
            }
        }
        return true;
    }

    // getters and setters
    public Player[] getPlayers(){
        return players;
    }

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

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }
}
