import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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

    public void start (Stage stage) throws Exception{
        mapManager.start(stage);
        stage.getScene().setOnKeyPressed( (KeyEvent e) ->{
            if(e.getCode() == KeyCode.PAUSE){
                // stage.setScene(); // pause game screen
            }

        });
    }

    GameManager(){}

    GameManager(int player_count){
        players = new Player[player_count];
        currentScores = new int[player_count];
        gameManagerFileManager = new FileManager();
        gameRunning = true;
        gamePaused = false;
        currentGameLevel = GAME_START_LEVEL;
        try{mapManager = new MapManager(player_count, currentGameLevel);} catch (Exception e) {
            e.printStackTrace();
        }
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