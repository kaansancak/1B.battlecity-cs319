package Management;

import UserInterface.Frame.GameViewFrame;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


public class GameManager  {

    public static GameManager gameManagerInstance = null;
    private final int GAME_START_LEVEL = 1;
    private final int GAME_FINAL_LEVEL = 2;
    private MapManager mapManager;
    private GameViewFrame gameViewFrame;
    private int currentGameLevel;
    private int player_count;
    private AnimationTimer timer;

    private GameManager(int player_count){
        MediaPlayer player1 = new MediaPlayer(new Media(Paths.get("MediaFiles/newLevel.mp3").toUri().toString()));
        player1.play();
        this.player_count = player_count;
        currentGameLevel = GAME_START_LEVEL;
        startMapManager();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };

        timer.start();
    }

    public static GameManager getGameManagerInstance( int player_count){
        if ( gameManagerInstance == null){
            gameManagerInstance = new GameManager( player_count);
            return gameManagerInstance;
        }else
            return gameManagerInstance;
    }

    public static void endGameManagerInstance(){
        gameManagerInstance = null;
    }

    private void onUpdate() {
        if( currentGameLevel > GAME_FINAL_LEVEL){
            gameViewFrame = new GameViewFrame(this, 1);
            gameViewFrame.showGameView();
            //Show game finished view
        }
        else if( mapManager.getGameStatus() == GameStatus.GAME_OVER){
            gameViewFrame = new GameViewFrame(this, 0);
            MediaPlayer player = new MediaPlayer(new Media(Paths.get("MediaFiles/gameover.mp3").toUri().toString()));
            player.play();
            gameViewFrame.showGameView();
            //Show game over view
        }else if( mapManager.getGameStatus() == GameStatus.LEVEL_FINISHED){
            timer.stop();
            gameViewFrame = new GameViewFrame(this, 2);
            gameViewFrame.showGameView();
        }else if( mapManager.getGameStatus() == GameStatus.RETURN_MENU_PAUSE){
            timer.stop();
            MapManager.setEndMapManager();
            gameManagerInstance = null;
        }
    }

    public void endMapManager(){
        MapManager.setEndMapManager();
    }

    public void initiateNextLevel() {
            currentGameLevel++;
            try {
                mapManager = MapManager.getMapManagerInstance( player_count, currentGameLevel);
            }catch ( Exception e){
                e.printStackTrace();
            }
            timer.start();
    }
    public int getScores(int id) {
        return mapManager.getPlayerScore(id);
    }


    private void startMapManager() {
        try{mapManager = MapManager.getMapManagerInstance( player_count, currentGameLevel);} catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLevel(){
        return currentGameLevel;
    }

    public void closeActiveMapManager(){
        mapManager.getStage().close();
    }

    public void stopLoop(){
        timer.stop();
    }

}