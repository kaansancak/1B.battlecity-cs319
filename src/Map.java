import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Map {

    // maybe we will need a bool for GUI dominance -
    // for grass and (tanks and bullets)
    private final int TILES = 20;
    private Scene mapScene;
    private Stage mapStage;
    private int playerCount;
    private int level;
    private int remainingBots;
    private int botCount;
    private GameObject[][] gameObjects;
    private int[][] obstaclesMap;
    private Pane mapPane;
    private Player players[];
    private ArrayList<Bullet> bullets;
    private ArrayList<Tank> tanks;
    private ArrayList<Bot> bots;
    private ArrayList<Bonus> bonuses;
    private ArrayList<GameObject> tilesMap;
    private ArrayList<Destructible> destructibles;
    private int lifeBonusCount;
    private int speedBonusCount;
    public Map( ){

    }
    /* GameObject File Decode
    * 0 = Brick, 1 = Wall, 2 = Bush, 3 = Water
    * 4 = Player, 5 = Bot
    * */
    public Map(int playerCount, int level, int[][] obstaclesMap){
        createObjectArrays();
        this.obstaclesMap = obstaclesMap;
        this.playerCount = playerCount;
        this.level = level;
        initMapObjects();
        intToObject();
        addObjects();
        initPlayers();
        lifeBonusCount = 0;
        speedBonusCount = 0;
    }

    //Init all objects
    private void initMapObjects(){
        mapPane = new Pane();
        gameObjects = new GameObject[TILES][TILES];
        players = new Player[playerCount];
        mapPane.setPrefWidth(640);
        mapPane.setPrefHeight(640);
        botCount = 10 + 2 * level; // WOW lol
        remainingBots = botCount;
    }

    //Create map holder arrays
    private void createObjectArrays(){
        bullets = new ArrayList<>();
        bots = new ArrayList<>();
        bonuses = new ArrayList();
        tilesMap = new ArrayList<GameObject>();
        tanks = new ArrayList<Tank>();
        destructibles = new ArrayList<Destructible>();
    }

    //Decide how to spawn a bot
    public void spawnBot(){
        if( botCount > 0){
            Bot bot = new Bot( 10, 5);
            mapPane.getChildren().addAll(bot.getView());
            bots.add(bot);
            botCount--;
        }
    }

    public void newBonus( int type) {
        if( type == 0 && lifeBonusCount <= 2) {
            Bonus lifeBonus = new LifeBonus((int)(Math.random()*30) + 1, (int)(Math.random()*30) + 1);
            lifeBonus.setReleased(true);
            mapPane.getChildren().addAll(lifeBonus.getView());
            lifeBonusCount++;
            bonuses.add(lifeBonus);
        }
        else if( type == 1 && speedBonusCount <= 2) {
            Bonus speedBonus = new SpeedBonus((int)(Math.random()*30) + 1, (int)(Math.random()*30) + 1);
            speedBonus.setReleased(true);
            mapPane.getChildren().addAll(speedBonus.getView());
            speedBonusCount++;
            bonuses.add(speedBonus);
        }
    }


    private void initPlayers(){
        for(int i = 0; i < playerCount; i++){
            players[i] = new Player(2, 2);
        }
        for( Player player : players){
            tanks.add(player);
            mapPane.getChildren().addAll(player.getView());
        }
    }

    //Update Methods
    //Update of Tanks
    public void updateTanks(){
        updatePlayer();
        updateBots();
    }

    private void updatePlayer(){
        for ( Player player : players){
            if ( player.getHealth() >= 0)
                player.draw();
            else{
                mapPane.getChildren().remove(player.getView());
            }
        }
    }

    private void updateBots() {
    for ( Bot bot : bots){
        if ( bot.getHealth() >= 0)
            bot.draw();
        else{
            mapPane.getChildren().remove(bot.getView());
        }
    }
    }

    //Update of Bullets
    public void updateBullets(){
        for( Bullet bullet : bullets) {
            if (bullet.isCrushed()) {
                mapPane.getChildren().remove(bullet.getView());
            } else {
                bullet.move();
            }
        }
    }

    //Update Methods
    public void updateDestructibles() {
        for( Destructible destructible: destructibles){
            if( destructible.isDestructed())
                mapPane.getChildren().remove( destructible.getView());
            else
                destructible.draw();
        }
    }

    public void updateBonuses() {

        for( Bonus bonus : bonuses) {
            if( bonus.isTaken()) {
                mapPane.getChildren().remove(bonus.getView());
                //bonus.getView().setVisible(false);
            }
            else
                bonus.draw();
        }
        bonuses.removeIf(Bonus::isTaken);
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

    private void intToObject(){
        for(int i = 0; i < TILES; i++){
            for(int j = 0; j < TILES; j++) {
                Tile tile = new Tile(i ,j);
                tile.draw();
                mapPane.getChildren().add( tile.getView());
                if(obstaclesMap[i][j] == 0){
                    continue;
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

    public void fire(Tank tank){
       Bullet fired = tank.fire();
       mapPane.getChildren().addAll(fired.getView());
       bullets.add(fired);
    }

    public void addObjects(GameObject[][] gameObjects){
        this.gameObjects = gameObjects;
    }
    public void updateObjects(){
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).move();
        }
    }

    public void finishMap(){

    }

    public boolean tryNextMove(Tank tank, int dir){
        ImageView tankView = tank.getView();

        for ( GameObject gameObject : tilesMap){
            if ( gameObject.getView().getBoundsInParent().intersects( tankView.getBoundsInParent()))
                tankView.setVisible(false);
            else
                tankView.setVisible(true);
            if( gameObject.getView().getBoundsInParent().intersects(getMockUp(tank,dir).getBoundsInParent())
                    && !(gameObject instanceof Tile)) {

               if(gameObject instanceof Bonus) {
                    ((Bonus) gameObject).setTaken(true);
                    return true;
                }

                return (gameObject instanceof Bush);
            }

        }
       /* for ( GameObject gameObject : bonuses) {
            if( gameObject.getView().getBoundsInParent().intersects( getMockUp(tank, dir).getBoundsInParent()) && !(gameObject instanceof Tile)) {
                gameObject.getView().setVisible(false);
                return (gameObject instanceof Bonus);
            }
        }*/
        return true;
    }

    private ImageView getMockUp(Tank tank, int dir) {
        double newX = tank.getxLoc();
        double newY = tank.getyLoc();
        switch ( dir){
            case 0: newX += tank.getVelocity().getX();
            case 1: newX -= tank.getVelocity().getX();
            case 2: newY += tank.getVelocity().getY();
            case 3: newY -= tank.getVelocity().getY();
        }
        ImageView mockUp = new ImageView( tank.getImage());
        mockUp.setVisible(false);
        mockUp.setTranslateX( newX*23);
        mockUp.setTranslateY( newY*23);
        mapPane.getChildren().addAll(mockUp);
        return mockUp;
    }

    public boolean bonusTaken( Bonus bonus, Tank tank, int dir) {
        ImageView tankView = tank.getView();
        ImageView bonusView = bonus.getView();

        for( GameObject gameObject : tilesMap) {
            if( tankView.getBoundsInParent().intersects( bonusView.getBoundsInParent())) {
                bonusView.setVisible(false);
                bonus.setTaken(true);
            }
        }
        return true;
    }

    // getters and setters

    public int getRemainingBots() {
        return remainingBots;
    }


    public int getAliveBots(){ return bots.size(); }


    public ArrayList<GameObject> getGameObjects() {
        return tilesMap;
    }


    public ArrayList<Tank> getTanks() {
        return tanks;
    }


    public GameObject[][] getGameObjectsArray(){
        return gameObjects;
    }

    public ArrayList<Bot> getBots() {
        return bots;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    private void setLifeBonusCount(int newCount) {
        lifeBonusCount = newCount;
    }

    private int getLifeBonusCount() {
        return lifeBonusCount;
    }

    private void setSpeedBonusCount(int newCount) {
        speedBonusCount = newCount;
    }

    private int getSpeedBonusCount() {
        return speedBonusCount;
    }


}