import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
// probably import java.io.Serializable


public class FileManager /* probably extends serializable*/ {
    private final int TILES = 20; // how many tiles ?
    private File curFil; // addition: File
    private ArrayList<Image> scannedImages;
    private ArrayList<String> scannedTexts;
    private ArrayList<Media> scannedAudios;
    private Scanner scan;
    private int[][] scannedMap;
    private final int NUMBER_IMAGES = 7;
    private final int NUMBER_AUDIOS = 2;

    // initialize file manager
    public FileManager(){
        scannedImages = new ArrayList<>();
        scannedTexts = new ArrayList<>();
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
    // highest score will be in file highS.txt
    public void saveHighestScore(int highestScore) throws java.io.IOException{
        curFil = new File("."+"/MediaFiles/highS.txt");
        scan = new Scanner(curFil);
        if(highestScore > scan.nextInt()) {
            FileWriter fWriter = new FileWriter(curFil);
            PrintWriter pWriter = new PrintWriter(curFil);
            pWriter.println (highestScore);
        }

    }
    public int getHighestScore() throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/highS.txt");
        scan = new Scanner(curFil);
        return scan.nextInt();
    }
    // howToPlay.txt
    public String getHowToPlayDoc() throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/howToPlay.txt");
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s += scan.nextLine();
        }
        return s;
    }
    // credits.txt
    public String getCreditsDoc() throws FileNotFoundException{
        curFil = new File("."+"/MediaFiles/credits.txt");
        scan = new Scanner(curFil);
        String s = "";
        while(scan.hasNext()){
            s += scan.nextLine();
        }
        return s;
    }
    // settings.txt each line will be new array element e.g WASD(space) would be first line
    public ArrayList<String> getSettingsDoc() throws FileNotFoundException{
        curFil = new File(this.getClass().getResource("."+"/MediaFiles/settings.txt").getFile());
        scan = new Scanner(curFil);
        ArrayList<String> setting = new ArrayList<>();
        while(scan.hasNext()){
            setting.add(scan.nextLine());
        }
        return setting;
    }
    //public void setSettings( int id, String setting[]){} // do we need id or we can know by number of lines ?


    public File getCurFil() {
        return curFil;
    }

    // images from file
    public ArrayList<Image> getScannedImages() throws FileNotFoundException {
        for(int i = 0; i < NUMBER_IMAGES; i++){ // assumed png
            scannedImages.add(new Image(Paths.get("."+"/MediaFiles/image" + i + ".png").toUri().toString()));
        }
        return scannedImages;
    }


    public ArrayList<String> getScannedTexts() {
        return scannedTexts;
    }

    public ArrayList<Media> getScannedAudios() {
        String audio = "";
        for(int i = 0; i < NUMBER_AUDIOS; i++){

            scannedAudios.add(new Media(Paths.get("MediaFiles/audio" + i + ".wav").toUri().toString())); // audioooo
        }
        return scannedAudios;
    }

    public int[][] getScannedMap() {
        return scannedMap;
    }



}

