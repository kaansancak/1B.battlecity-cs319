public class GameManager{

    private final int GAME_START_LEVEL = 1;
    private final int GAME_FINAL_LEVEL = 2;
    private MapManager mapManager;
    private ScreenManager screenManager;
    private FileManager gameManagerFileManager;
    private Settings inputController;
    private int highestScore;
    private Player[] players;
    private int[] currentScores;
    private boolean gameRunning;
    private boolean gamePaused;
    private int currentGameLevel;
    private boolean gameFinished;
    private int gameCompletedLevels;

    GameManager(){}

    GameManager(int player_count){
        players = new Player[player_count];
        currentScores = new int[player_count];
        mapManager = new MapManager();
        gameManagerFileManager = new FileManager();
        gameRunning = true;
        gamePaused = false;
        currentGameLevel = GAME_START_LEVEL;
        gameFinished = false;
        gameCompletedLevels = 0;
    }
    private boolean isGameOver(){
        return gameCompletedLevels == GAME_FINAL_LEVEL;
    }
    private void updateCurrentScore(int player_id){}
    private void updateHighestScore(int highestScore){}
    private void startGame(){}
    private void finishGame(){}
    private boolean isGameRunning(){
        return gameRunning;
    }
    public boolean isGamePaused(){
        return gamePaused;
    }
    private void pauseGame(){
        gamePaused = true;
        gameRunning = false;
    }
    private void continueGame(){
        gamePaused = false;
        gameRunning = true;
    }


    private void quitGame(){}
    private void saveHighestScore(int scoregame){ if(scoregame>highestScore)
                                                    { highestScore = scoregame; }}
    private int getHighestScore(){ return highestScore; }
    private boolean isGameFinished(){ return !gameRunning; }
    private void startLevel(MapManager mapManager ,int nextLevel){}
    private void finishLevel(MapManager mapmManager){}

}