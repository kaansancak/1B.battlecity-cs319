package Management;

import javafx.scene.media.Media;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager  {
    private final int TILES = 20;
    private final int NUMBER_IMAGES = 10;
    private final int NUMBER_AUDIOS = 2;
    private File curFil;
    private ArrayList<Media> scannedAudios;
    private Scanner scan;
    private int[][] scannedMap;

    // initialize file manager
    public FileManager(){
        scannedAudios = new ArrayList<>();
        scannedMap = new int[TILES][TILES];
    }

    // level names will be "level" + levelCount + ".txt" -> level3.txt e.g
    public int[][] getMapLevelData(int level) throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/level"+level+".txt");
        scan = new Scanner(curFil);
        for(int i = 0; i < TILES && scan.hasNextLine(); i++){
            for(int j = 0; j < TILES && scan.hasNext(); j++){
                scannedMap[i][j] = scan.nextInt();
            }
        }
        return scannedMap;

    }
    // howToPlay.txt
    public ArrayList<String> getHowToPlayDoc() throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/howToPlay.txt");
        scan = new Scanner(curFil);
        ArrayList<String> s = new ArrayList<>();
        String line = "";
        while(scan.hasNext()){
            line = scan.nextLine();
            s.add(line);
        }
        return s;
    }
    // credits.txt
    public ArrayList<String> getCreditsDoc() throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/credits.txt");
        scan = new Scanner(curFil);
        ArrayList<String> s = new ArrayList<>();
        String line = "";
        while(scan.hasNext()){
            line = scan.nextLine();
            s.add(line);
        }
        return s;
    }

    public ArrayList<Media> getScannedAudios() {
        String audio = "";
        for(int i = 0; i < NUMBER_AUDIOS; i++){

            scannedAudios.add(new Media(Paths.get("MediaFiles/audio" + i + ".wav").toUri().toString())); // audioooo
        }
        return scannedAudios;
    }

    public Media getOpeningSong() {
        Media openingSong = new Media(Paths.get("MediaFiles/opening.mp3").toUri().toString());
        return openingSong;
    }
}

