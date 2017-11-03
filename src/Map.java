

public class Map {

    // maybe we will need a bool for GUI dominance -
    // for grass and (tanks and bullets)
    private int level;
    private int height;
    private int width;
    private int remainingBots;
    private int botCount;
    private GameObject[][] gameObjects;
    private double elapsedTime;

    public Map( ){



    }
    /* GameObject File Decode
    * 0 = Brick, 1 = Wall, 2 = Bush, 3 = Water
    * 4 = Player, 5 = Bot
    * */
    public Map(int level){
        this.level = level;
        botCount = 10 + 2 * level; // WOW lol
        remainingBots = botCount;
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
    if( gameObjects[x][y].isPassable())
    }

    public void drawMap(int height, int width){

    }

    // getters and setters
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
