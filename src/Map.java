import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Map {

    // maybe we will need a bool for GUI dominance -
    // for grass and (tanks and bullets)
    private final int TILES = 20;
    Scene mapScene;
    Stage mapStage;
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
    private ArrayList<Tank> tanks;
    private ArrayList<Bot> bots;
    private ArrayList<GameObject> tilesMap;
    private ArrayList<Destructible> destructibles;

    private boolean answer = false;

    public Map( ){

    }
    /* GameObject File Decode
    * 0 = Brick, 1 = Wall, 2 = Bush, 3 = Water
    * 4 = Player, 5 = Bot
    * */
    public Map(int playerCount, int level, int[][] obstaclesMap){
        tilesMap = new ArrayList<GameObject>();
        tanks = new ArrayList<Tank>();
        destructibles = new ArrayList<Destructible>();
        mapPane = new Pane();
        this.obstaclesMap = obstaclesMap;
        gameObjects = new GameObject[TILES][TILES];
        this.playerCount = playerCount;
        players = new Player[playerCount];
        for(int i = 0; i < playerCount; i++){
            players[i] = new Player(2, 2);
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

/*
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
*/
    public void initPlayers(){
        for( Player player : players){
            player.setImage(images.get(5));
            player.setLeftImage(player.getImage());
            player.setDownImage( images.get(7));
            player.setUpImage(images.get(8));
            player.setRightImage(images.get(9));
            player.setView( new ImageView(player.getImage()));
            player.getView().setTranslateX(player.getxLoc());
            player.getView().setTranslateY(player.getyLoc());
            player.getView().setFitHeight(30);
            player.getView().setFitWidth(30);
            player.setHealth(200);
            tanks.add(player);
            mapPane.getChildren().addAll(player.getView());
        }
    }



    //Update Methods

    public void updatePlayer(){
        for ( Player player : players){
            if ( player.getHealth() >= 0)
                player.draw();
            else{
                mapPane.getChildren().remove(player.getView());
            }
        }
    }
    //Update of Tanks
    public void updateTanks(){
        for( Tank tank : tanks){
            if ( tank.getHealth() >= 0)
            tank.draw();
            else{
                mapPane.getChildren().remove(tank.getView());
            }
        }
    }

    //Update of Bullets
    public void updateBullets(){
        for( Bullet bullet : bullets) {
            if (bullet.isCrushed()) {
                mapPane.getChildren().remove(bullet.getView());
            } else {
                bullet.draw();
                bullet.move();
            }
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
    public void closeMap() {
        mapStage.close();
    }

    public void intToObject(){
        for(int i = 0; i < TILES; i++){
            for(int j = 0; j < TILES; j++) {
                Tile tile = new Tile(i ,j);
                tile.draw();
                mapPane.getChildren().add( tile.getView());
                if(obstaclesMap[i][j] == 0){
                    //tilesMap.add( new Tile(i,j));// ground
                }
                else {
                    if (obstaclesMap[i][j] == 1) {
                        Brick brick = new Brick(i,j);
                        tilesMap.add( brick);
                        destructibles.add( brick);
                    } else if (obstaclesMap[i][j] == 2) {
                        tilesMap.add( new Bush(i,j));
                    } else if (obstaclesMap[i][j] == 3) {
                        tilesMap.add( new IronWall(i,j));
                    } else if (obstaclesMap[i][j] == 4) {
                        tilesMap.add( new Water(i,j));
                    }
                }
            }
        }

    }

    public void addObjects(){
        for( GameObject gameObject: tilesMap){
            gameObject.draw();
            mapPane.getChildren().add(gameObject.getView());
        }
    }

    /*public void intToObject(){
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
                    tilesMap.add(gameObjects[i][j]);
                }
            }
        }

    }*/


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
       mapPane.getChildren().addAll(fired.getView());
       bullets.add(fired);
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
            if( gameObject.isDestructed())
                gameObject = null;
    }

    public boolean isBotsDead(){
        return botCount == 0;
    }
    public void finishMap(){

    }

    public void printObjects(){
        for( GameObject gameObject : tilesMap)
        System.out.println( gameObject.getView().getBoundsInParent());
    }

    public boolean isPassableTile(int x, int y){
        return true;
    }

    public boolean tryNextMove(double x, double y, ImageView playerView){
        for ( GameObject gameObject : tilesMap){
            if ( gameObject.getView().getBoundsInParent().intersects( playerView.getBoundsInParent()))
                playerView.setVisible(false);
            else
                playerView.setVisible(true);
            if( gameObject.getView().getBoundsInParent().intersects(getMockUp(x,y).getBoundsInParent())
                    && !(gameObject instanceof Tile)) {
                return (gameObject instanceof Bush);
            }

        }
        return true;
        /*
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
        */
    }

    private ImageView getMockUp(double x, double y) {
        ImageView mockUp = new ImageView( getPlayer(0).getImage());
        mockUp.setVisible(false);
        mockUp.setTranslateX( x*25);
        mockUp.setTranslateY( y*25);
        mapPane.getChildren().addAll(mockUp);

        return mockUp;
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

    public void setRemainingBots(int remainingBots) {
        this.remainingBots = remainingBots;
    }

    public int getAliveBots(){ return bots.size(); }

    public int getBotCount() {
        return botCount;
    }

    public void setBotCount(int botCount) {
        this.botCount = botCount;
    }

    public ArrayList<GameObject> getGameObjects() {
        return tilesMap;
    }

    public void setGameObjects(GameObject[][] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<GameObject> getTilesMap() {
        return tilesMap;
    }

    public GameObject[][] getGameObjectsArray(){
        return gameObjects;
    }

    public Scene getMapScene() {
        return mapScene;
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

    public ArrayList<Destructible> getDestructibles() {
        return destructibles;
    }

    public void updateDestructibles() {
        for( Destructible destructible: destructibles){
            if( destructible.isDestructed())
                mapPane.getChildren().remove( destructible.getView());
            else
                destructible.draw();
        }


    }
}
