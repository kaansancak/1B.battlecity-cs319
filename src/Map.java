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

    private ArrayList<Bot> bots;
    private ArrayList<GameObject> allObjects;
    Scene mapScene;
    Stage mapStage;

    public Map( ){

    }
    /* GameObject File Decode
    * 0 = Brick, 1 = Wall, 2 = Bush, 3 = Water
    * 4 = Player, 5 = Bot
    * */
    public Map(int playerCount, int level, int[][] obstaclesMap){
        allObjects = new ArrayList<GameObject>();
        mapPane = new Pane();
        this.obstaclesMap = obstaclesMap;
        gameObjects = new GameObject[TILES][TILES];
        this.playerCount = playerCount;
        players = new Player[playerCount];
        for(int i = 0; i < playerCount; i++){
            players[i] = new Player(i, i);
        }
        mapPane.setPrefWidth(640);
        mapPane.setPrefHeight(640);

        tileX = (int)mapPane.getPrefWidth() / TILES;
        tileY = (int)mapPane.getPrefHeight() / TILES;

        this.level = level;
        botCount = 10 + 2 * level; // WOW lol
        remainingBots = botCount;
        bullets = new ArrayList<>();
        bots = new ArrayList<>();
    }

    public void initBot(Bot b){
        b.setImage(images.get(5));
        b.setLeftImage(b.getImage());
        b.setDownImage( images.get(7));
        b.setUpImage(images.get(9));
        b.setRightImage(images.get(8));
        b.setView( new ImageView(b.getImage()));
        b.getView().setTranslateX(b.getxLoc());
        b.getView().setTranslateY(b.getyLoc());
        mapPane.getChildren().addAll(b.getView());
        remainingBots--;
        bots.add(b);
    }

    public void initPlayers(){
        for( Player player : players){
            player.setImage(images.get(5));
            player.setLeftImage(player.getImage());
            player.setDownImage( images.get(7));
            player.setUpImage(images.get(8));
            player.setRightImage(images.get(9));
            player.setView( new ImageView(player.getImage()));
            player.setxLoc( tileX * 2);
            player.setyLoc((2 * tileY));
            player.getView().setTranslateX(player.getxLoc());
            player.getView().setTranslateY(player.getyLoc());
            player.getView().setFitHeight(30);
            player.getView().setFitWidth(30);
            mapPane.getChildren().addAll(player.getView());
        }
    }

    public void updateBots(){
        for(Bot bot: bots){
            bot.getView().setTranslateX(bot.getxLoc());
            bot.getView().setTranslateY(bot.getyLoc());
        }
    }

    public void updatePlayers(){
        for( Player player : players){
            player.getView().setTranslateX(player.getxLoc());
            player.getView().setTranslateY(player.getyLoc());
         //   System.out.print( player.getView().getBoundsInParent() + " " + player.getView().getBoundsInParent());
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
                    gameObjects[i][j].setView(new ImageView(gameObjects[i][j].getImage()));
                    gameObjects[i][j].getView().setTranslateX(gameObjects[i][j].getxLoc());
                    gameObjects[i][j].getView().setTranslateY(gameObjects[i][j].getyLoc());
                    mapPane.getChildren().addAll(gameObjects[i][j].getView());
                    allObjects.add(gameObjects[i][j]);
                }
            }
        }

    }


    public ArrayList<Bot> getBots() {
        return bots;
    }

    public void setBots(ArrayList<Bot> bots) {
        this.bots = bots;
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

    public void printObjects(){
        Player player = getPlayer(0);
        System.out.print( player.getView().getBoundsInLocal());
    }

    public boolean isPassableTile(int x, int y){
        return true;
    }

    public boolean tryNextMove( int x, int y, int dir){
        int a = x/tileX;
        int b = y/tileY;

        if(dir == 0){
            if(!(gameObjects[a][b] == null )){
                if(!(gameObjects[a][b] instanceof Bush))
                    return false;
            }
            if(!(gameObjects[a+1][b] == null )){
                if(!(gameObjects[a+1][b] instanceof Bush))
                    return false;
            }
        }
        else if(dir == 1){
            if(!(gameObjects[a-1][b] == null )){
                if(!(gameObjects[a-1][b] instanceof Bush))
                    return false;
            }
            if(!(gameObjects[a][b] == null )){
                if(!(gameObjects[a][b] instanceof Bush))
                    return false;
            }
        }
        else if(dir == 2){
            if(!(gameObjects[a][b] == null )){
                if(!(gameObjects[a][b] instanceof Bush))
                    return false;
            }
            if(!(gameObjects[a][b+1] == null )){
                if(!(gameObjects[a][b+1] instanceof Bush))
                    return false;
            }
        }
        else if(dir == 3){
            if(!(gameObjects[a][b] == null )){
                if(!(gameObjects[a][b] instanceof Bush))
                    return false;
            }
            if(!(gameObjects[a][b-1] == null )){
                if(!(gameObjects[a][b-1] instanceof Bush))
                    return false;
            }
        }
        return true;
    }



        /* for( GameObject[] gameObject1: gameObjects){
            for( GameObject gameObject: gameObject1 ){
                System.out.print( gameObject.getClass().toString());
                if( gameObject.getView().intersects(getPlayer(0).getView().getBoundsInParent())){
                    System.out.print( gameObject.getClass().toString());
                    if( gameObject instanceof Bush)
                        return true;
                    return false;
                }

            }
        }
        return true;*/


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
    public int getAliveBots(){ return bots.size(); }

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
