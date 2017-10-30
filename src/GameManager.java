public class GameManager{

    private final int GAME_START_LEVEL = 1;
    private MapManager mapManager;
    private ScreenManager screenManager;
    private FileManager gameManagerFileManager;
    private InputController inputController;
    private int highestScore;
    private Player[] players;
    private int[] currentScores;
    private boolean gameRunning;
    private boolean gamePaused;
    private int currentGameLevel;
    private boolean gameFinished;
    private int gameCompletedLevels;

    GameManager(){}

    GameManager(int player_count){}
    private boolean isGameOver(){}
    private void updateCurrentScore(int player_id){}
    private void updateHighestScore(int highestScore){}
    private void startGame(){}
    private void finishGame(){}
    private boolean isGameRunning(){}
    private boolean isGamePaused(){}
    private void pauseGame(){}
    private void continueGame(){}
    private void quitGame(){}
    private SoundManager getSetting(){}
    private void saveHighestScore(int scoregame){ if(scoregame>highestScore)
                                                    { highestScore = scoregame; }}
    private int getHighestScore(){ return highestScore; }
    private boolean isGameFinished(){ return !gameRunning; }
    private void startLevel(MapManager mapManager ,int nextLevel){}
    private void finishLevel(MapManager mapmManager){}

}